package argument.definition.integer.rules;

import argument.definition.integer.IntegerArgumentDefinition;
import argument.definition.rules.NumberProportionRuleType;
import argument.exception.RuleNotObservedException;

/**
 * This class define a proportion rule for integer arguments.
 * E.g. '{@literal <}', '{@literal >}', '{@literal <}=' and '{@literal >}='
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class IntegerArgumentProportionRule implements IntegerArgumentRule {
	private double than;
	private NumberProportionRuleType ruleType;

	/**
	 * Constructor of the class.
	 * 
	 * @param ruleType	Define the rule type e.g. 'GREATER' for 'x {@literal >}'
	 * @param than		Reference value - e.g. 'that {@literal <}= argument'
	 */
	public IntegerArgumentProportionRule(NumberProportionRuleType ruleType, int than) {
		this.than = than;
		this.ruleType = ruleType;
	}

	@Override
	public void doTest(IntegerArgumentDefinition integerArgumentDefinition, int value) throws RuleNotObservedException {
		switch(ruleType) {
		case GREATER:
			if(value <= than) {
				throw new RuleNotObservedException(integerArgumentDefinition.getPrefixesAsString(), "greater than "+than, String.valueOf(value));
			}
			break;
		case LESS:
			if(value >= than) {
				throw new RuleNotObservedException(integerArgumentDefinition.getPrefixesAsString(), "less than "+than, String.valueOf(value));
			}
			break;
		case GREATER_EQUAL:
			if(value < than) {
				throw new RuleNotObservedException(integerArgumentDefinition.getPrefixesAsString(), "greater or equal than "+than, String.valueOf(value));
			}
			break;
		case LESS_EQUAL:
			if(value > than) {
				throw new RuleNotObservedException(integerArgumentDefinition.getPrefixesAsString(), "greater or equal than "+than, String.valueOf(value));
			}
			break;
		}
	}
}
