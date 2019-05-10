package argument.definition.doubIe.rules;

import argument.definition.doubIe.DoubleArgumentDefinition;
import argument.definition.rules.NumberProportionRuleType;
import argument.exception.RuleNotObservedException;

/**
 * This class define a proportion rule for double arguments.
 * E.g. '{@literal <}', '{@literal >}', '{@literal <}=' and '{@literal >}='
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class DoubleArgumentProportionRule implements DoubleArgumentRule {
	private double than;
	private NumberProportionRuleType ruleType;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param ruleType	Define the rule type e.g. 'GREATER' for 'x {@literal >}'
	 * @param than		Reference value - e.g. 'that {@literal <}= argument'
	 */
	public DoubleArgumentProportionRule(NumberProportionRuleType ruleType, double than) {
		this.than = than;
		this.ruleType = ruleType;
	}

	@Override
	public void doTest(DoubleArgumentDefinition doubleArgumentDefinition, double value) throws RuleNotObservedException {
		switch(ruleType) {
		case GREATER:
			if(value <= than) {
				throw new RuleNotObservedException(doubleArgumentDefinition.getPrefixesAsString(), "greater than "+than, String.valueOf(value));
			}
			break;
		case LESS:
			if(value >= than) {
				throw new RuleNotObservedException(doubleArgumentDefinition.getPrefixesAsString(), "less than "+than, String.valueOf(value));
			}
			break;
		case GREATER_EQUAL:
			if(value < than) {
				throw new RuleNotObservedException(doubleArgumentDefinition.getPrefixesAsString(), "greater or equal than "+than, String.valueOf(value));
			}
			break;
		case LESS_EQUAL:
			if(value > than) {
				throw new RuleNotObservedException(doubleArgumentDefinition.getPrefixesAsString(), "greater or equal than "+than, String.valueOf(value));
			}
			break;
		}
	}
}
