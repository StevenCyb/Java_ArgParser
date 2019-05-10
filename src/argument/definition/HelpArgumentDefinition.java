package argument.definition;

import argument.parser.ArgumentParser;

/**
 * Argument definition for the help argument.
 * Enable the auto-generated help option.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class HelpArgumentDefinition extends ArgumentDefinition {
	private String title = "";
	private ArgumentParser argumentParser;
	
	/**
	 * Constructor of the Class.
	 * 
	 * @param prefixes			Set prefixes that can be used to get help. E.g. "-help, -h".
	 * @param title				Set an title for the help output.
	 * @param argumentParser	Trigger an exception if more than one definition is defined with the same prefix.
	 */
	public HelpArgumentDefinition(String[] prefixes, String title, ArgumentParser argumentParser) {
		super(prefixes, "Used to obtain this information.", false);
		this.title = title;
		this.argumentParser = argumentParser;
	}

	/**
	 * Get the available arguments with was defined.
	 * 
	 * @return	Output of the help argument.
	 */
	public String getValue() {
		String output = title+"\r\n";
		for(ArgumentDefinition ad : argumentParser.getArgumentDefinitions()) {
			output += String.format("%-20s %-15s %s", ad.getPrefixesAsString(), "Required: "+(ad.isRequired()==true?"yes":"no"), ad.getDescription()+"\r\n");
		}
		return output;
	}
}
