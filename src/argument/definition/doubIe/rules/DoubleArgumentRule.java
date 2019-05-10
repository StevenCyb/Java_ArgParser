package argument.definition.doubIe.rules;

import argument.definition.doubIe.DoubleArgumentDefinition;
import argument.exception.RuleNotObservedException;

/**
 *	Interface for double argument rules.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public interface DoubleArgumentRule {
	/**
	 * Checks the input against the rule.
	 * 
	 * @param doubleArgumentDefinition	Argument definition object with have this rule.
	 * @param value						Value with will be checked again the rule.
	 * @throws RuleNotObservedException	This exception will be thrown if the value not matches the rule.
	 */
	public void doTest(DoubleArgumentDefinition doubleArgumentDefinition, double value) throws RuleNotObservedException;
}
