package argument.definition.integer.rules;

import java.util.regex.Pattern;

import argument.definition.integer.IntegerArgumentDefinition;
import argument.exception.RuleNotObservedException;

/**
 * Class for integer argument rule based on regular expression.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class IntegerArgumentRegularExpressionRule implements IntegerArgumentRule {
	private Pattern pattern;
	private String expressionDescription;

	/**
	 * Constructor of the class.
	 * 
	 * @param expression			Define the regular expression for this rule.
	 * @param expressionDescription	Describe this rule.
	 */
	public IntegerArgumentRegularExpressionRule(String expression, String expressionDescription) {
		pattern = Pattern.compile(expression);
		if(expressionDescription == null || expressionDescription.isEmpty()) {
			this.expressionDescription = expression;
		} else {
			this.expressionDescription = expressionDescription;
		}
	}

	/**
	 * Constructor of the class.
	 * 
	 * @param expression	Define the regular expression for this rule.
	 */
	public IntegerArgumentRegularExpressionRule(String expression) {
		this(expression,"");
	}

	@Override
	public void doTest(IntegerArgumentDefinition integerArgumentDefinition, int value) throws RuleNotObservedException {
		if(!pattern.matcher(String.valueOf(value)).matches()) {
			throw new RuleNotObservedException(integerArgumentDefinition.getPrefixesAsString(), expressionDescription, String.valueOf(value));
		}
	}
}
