package argument.definition.integer;

import java.util.ArrayList;
import java.util.List;

import argument.definition.ArgumentDefinition;
import argument.definition.integer.rules.IntegerArgumentRule;
import argument.exception.RuleNotObservedException;

/**
 * Argument definition for an integer argument definition.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class IntegerArgumentDefinition extends ArgumentDefinition{
	private int value;
	private int defaultValue;
	private IntegerArgumentRule[] rules;

	/**
	 * Constructor of the Class.
	 * 
	 * @param prefixes		Set prefixes that can be used to get help. E.g. "-integer, -i".
	 * @param description	Add a description to this argument like "IP of the target server.".
	 * @param required		Determine whether this argument is required.
	 * @param defaultValue	What should be the default value, if this argument is not set by the user.
	 */
	public IntegerArgumentDefinition(String[] prefixes, String description, Boolean required, int defaultValue) {
		super(prefixes, description, required);
		this.rules = new IntegerArgumentRule[0];
		this.defaultValue = defaultValue;
		this.value = defaultValue;
	}

	/**
	 * Constructor of the Class.
	 * 
	 * @param prefixes		Set prefixes that can be used to get help. E.g. "-double, -d".
	 * @param description	Add a description to this argument like "IP of the target server.".
	 * @param required		Determine whether this argument is required.
	 * @param defaultValue	What should be the default value, if this argument is not set by the user.
	 * @param rules			Rules that apply to this argument definition.
	 */
	public IntegerArgumentDefinition(String[] prefixes, String description, Boolean required, int defaultValue, IntegerArgumentRule[] rules) {
		this(prefixes, description, required, defaultValue);
		if(rules != null) {
			this.rules = rules;
		}
	}
	
	/**
	 * Test if an given value matches the rules.
	 * 
	 * @param value						Value to be checked.
	 * @throws RuleNotObservedException	Exception thrown when a rule is violated.
	 */
	private void testRules(int value) throws RuleNotObservedException {
		for(IntegerArgumentRule rule : rules) {
			rule.doTest(this, value);
		}
	}

	/**
	 * Get the value with was set by the user or the default.
	 * 
	 * @return	Get the value.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Set a value to this argument definition.
	 * 
	 * @param value						Value with will be set.
	 * @throws RuleNotObservedException	Exception thrown when a rule is violated.
	 */
	public void setValue(int value) throws RuleNotObservedException {
		testRules(value);
		isSet = true;
		this.value = value;
	}

	/**
	 * Get the description of this argument definition
	 * (this was set by the constructor).
	 * 
	 * @return	Description of this argument definition.
	 */
	public String getDescription() {
		return super.getDescription()+(!super.isRequired()?" (default '"+defaultValue+"')":"");
	}
	
	/**
	 * Return the rules for this argument definition.
	 * 
	 * @return	Get an array with all rules.
	 */
	@Override
	public String[] getRules() {
		List<String> output = new ArrayList<String>();
		output.add("The number must be a integer.");
		for(IntegerArgumentRule rule : rules) {
			output.add(rule.toString());
		}
		return output.toArray(new String[0]);
	}
}
