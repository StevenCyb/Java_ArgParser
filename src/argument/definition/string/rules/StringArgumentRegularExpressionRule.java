package argument.definition.string.rules;

import java.util.regex.Pattern;

import argument.definition.string.StringArgumentDefinition;
import argument.exception.RuleNotObservedException;

/**
 * Class for string argument rule based on regular expression.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class StringArgumentRegularExpressionRule implements StringArgumentRule {
	private Pattern pattern;
	private String expressionDescription;

	/**
	 * Constructor of the class.
	 * 
	 * @param expression			Define the regular expression for this rule.
	 * @param expressionDescription	Describe this rule.
	 */
	public StringArgumentRegularExpressionRule(String expression, String expressionDescription) {
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
	public StringArgumentRegularExpressionRule(String expression) {
		this(expression,"");
	}

	@Override
	public void doTest(StringArgumentDefinition stringArgumentDefinition, String value) throws RuleNotObservedException {
		if(!pattern.matcher(value).matches()) {
			throw new RuleNotObservedException(stringArgumentDefinition.getPrefixesAsString(), expressionDescription, String.valueOf(value));
		}
	}
}
