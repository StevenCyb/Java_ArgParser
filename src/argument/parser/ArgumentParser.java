package argument.parser;

import java.util.ArrayList;
import java.util.List;

import argument.definition.ArgumentDefinition;
import argument.definition.HelpArgumentDefinition;
import argument.definition.doubIe.DoubleArgumentDefinition;
import argument.definition.flag.FlagArgumentDefinition;
import argument.definition.integer.IntegerArgumentDefinition;
import argument.definition.string.StringArgumentDefinition;
import argument.exception.MissingArgumentException;
import argument.exception.NonUniquePrefixException;
import argument.exception.RuleNotObservedException;
import argument.exception.UnknownArgumentException;
import argument.exception.ValueParseException;

/**
 * This class is the main controller of the library.
 * It holds the argument definitions and parses the entered arguments.
 *
 * @author Steven Cybinski
 * @version 0.1
 */
public class ArgumentParser {
	private List<ArgumentDefinition> args;
	private Boolean missingRequiredException = true;
	private Boolean unknownArgumentException = true;
	private Boolean terminateOnHelpArgument = true;
	private HelpArgumentDefinition helpArgument = null;
	private Boolean isHelpCalled = false;

	/**
	 * Constructor of the Class.
	 * 
	 * @param terminateOnHelpArgument	Define whether the program should be terminated if the user use the help argument. 
	 * @param missingRequiredException	Define whether an error message should be thrown if a required argument is not set.
	 * @param unknownArgumentException	Define if an error message should be thrown at an unknown argument or if it should be ignored.
	 */
	public ArgumentParser(Boolean terminateOnHelpArgument, Boolean missingRequiredException, Boolean unknownArgumentException) {
		args = new ArrayList<ArgumentDefinition>();
		this.missingRequiredException = missingRequiredException;
		this.unknownArgumentException = unknownArgumentException;
		this.terminateOnHelpArgument = terminateOnHelpArgument;
	}

	/**
	 * Adds an argument definition.
	 * 
	 * @param argDefinition				The argument definition as an object.
	 * @throws NonUniquePrefixException	Trigger an exception if more than one definition is defined with the same prefix.
	 */
	public void add(ArgumentDefinition argDefinition) throws NonUniquePrefixException {
		for (ArgumentDefinition ad : args) {
			if (ad.samePrefixes(argDefinition)) {
				throw new NonUniquePrefixException(ad, argDefinition);
			}
		}
		args.add(argDefinition);
	}

	/**
	 * Add a help argument like "java -help".
	 * 
	 * @param prefixes					Set prefixes that can be used to get help. E.g. "-help, -h".
	 * @param title						Set an title for the help output.
	 * @throws NonUniquePrefixException	Trigger an exception if more than one definition is defined with the same prefix.
	 */
	public void addHelpArgument(String[] prefixes, String title) throws NonUniquePrefixException {
		helpArgument = new HelpArgumentDefinition(prefixes, title, this);
	}

	/**
	 * Check if an argument trigger the helper.
	 * 
	 * @return	True: Help called / False: Help not called.
	 */
	public boolean isHelpCalled() {
		return isHelpCalled;
	}

	/**
	 * Get a list of all argument definitions.
	 * 
	 * @return	Return a list with all argument definitions.
	 */
	public List<ArgumentDefinition> getArgumentDefinitions() {
		return args;
	}

	/**
	 * This function parse and check the arguments.
	 * 
	 * @param args						Just put the args from the main here.
	 * @throws UnknownArgumentException	If set in the constructor: Will be thrown at an unknown argument.
	 * @throws MissingArgumentException If set in the constructor: Will be thrown at an required argument.
	 * @throws ValueParseException		Will be thrown if the data type not matches.
	 * @throws RuleNotObservedException	Will be thrown if the argument not observe an defined rule.
	 */
	public void parse(String[] args) throws UnknownArgumentException, MissingArgumentException, ValueParseException, RuleNotObservedException {
		if (terminateOnHelpArgument && helpArgument != null) {
			for (int i = 0; i < args.length; i += 2) {
				if (helpArgument.isPrefixMatch(args[i])) {
					isHelpCalled = true;
					System.out.println(helpArgument.getValue());
					System.exit(0);
				}
			}
		}
		ArgumentDefinition argumentDefinitionWaitForValue = null;
		for (String arg : args) {
			Boolean foundMatch = false;
			if (helpArgument != null && helpArgument.isPrefixMatch(arg)) {
				System.out.println(helpArgument.getValue());
				foundMatch = true;
				isHelpCalled = true;
			} else {
				for (ArgumentDefinition argumentDefinition : this.args) {
					if (argumentDefinitionWaitForValue != null) {
						try {
							if (argumentDefinitionWaitForValue instanceof IntegerArgumentDefinition) {
								((IntegerArgumentDefinition) argumentDefinitionWaitForValue)
										.setValue(Integer.parseInt(arg));
							} else if (argumentDefinitionWaitForValue instanceof DoubleArgumentDefinition) {
								((DoubleArgumentDefinition) argumentDefinitionWaitForValue)
										.setValue(Double.parseDouble(arg));
							} else if (argumentDefinitionWaitForValue instanceof StringArgumentDefinition) {
								((StringArgumentDefinition) argumentDefinitionWaitForValue).setValue(arg);
							}
						} catch (Exception e) {
							if(e instanceof RuleNotObservedException) {
								throw (RuleNotObservedException) e;
							} else {
								throw new ValueParseException(argumentDefinitionWaitForValue, arg);
							}
						}
						argumentDefinitionWaitForValue = null;
						foundMatch = true;
						break;
					} else if (argumentDefinition.isPrefixMatch(arg)) {
						if (argumentDefinition instanceof FlagArgumentDefinition) {
							((FlagArgumentDefinition)argumentDefinition).setValue();
						} else {
							argumentDefinitionWaitForValue = argumentDefinition;
						}
						foundMatch = true;
						break;
					}
				}
			}
			if (!foundMatch && unknownArgumentException) {
				throw new UnknownArgumentException(arg);
			}
		}
		if (missingRequiredException) {
			for (ArgumentDefinition ad : this.args) {
				if (ad.isRequired() && !ad.isSet()) {
					throw new MissingArgumentException(ad);
				}
			}
		}
	}
}
