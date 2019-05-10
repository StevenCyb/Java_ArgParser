package argument.definition.integer.rules;

import argument.definition.integer.IntegerArgumentDefinition;
import argument.exception.RuleNotObservedException;

/**
 *	Interface for integer argument rules.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public interface IntegerArgumentRule {
	/**
	 * Checks the input against the rule.
	 * 
	 * @param integerArgumentDefinition	Argument definition object with have this rule.
	 * @param value						Value with will be checked again the rule.
	 * @throws RuleNotObservedException	This exception will be thrown if the value not matches the rule.
	 */
	public void doTest(IntegerArgumentDefinition integerArgumentDefinition, int value) throws RuleNotObservedException;
}
