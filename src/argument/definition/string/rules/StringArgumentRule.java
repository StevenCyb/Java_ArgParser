package argument.definition.string.rules;

import argument.definition.string.StringArgumentDefinition;
import argument.exception.RuleNotObservedException;

/**
 *	Interface for string argument rules.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public interface StringArgumentRule {
	/**
	 * Checks the input against the rule.
	 * 
	 * @param stringArgumentDefinition	Argument definition object with have this rule.
	 * @param value						Value with will be checked again the rule.
	 * @throws RuleNotObservedException	This exception will be thrown if the value not matches the rule.
	 */
	public void doTest(StringArgumentDefinition stringArgumentDefinition, String value) throws RuleNotObservedException;
}
