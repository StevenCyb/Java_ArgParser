package argument.definition.doubIe.rules;

import java.util.regex.Pattern;

import argument.definition.doubIe.DoubleArgumentDefinition;
import argument.exception.RuleNotObservedException;

/**
 * Class for double argument rule based on regular expression.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class DoubleArgumentRegularExpressionRule implements DoubleArgumentRule {
	private Pattern pattern;
	private String expressionDescription;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param expression			Define the regular expression for this rule.
	 * @param expressionDescription	Describe this rule.
	 */
	public DoubleArgumentRegularExpressionRule(String expression, String expressionDescription) {
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
	public DoubleArgumentRegularExpressionRule(String expression) {
		this(expression,"");
	}

	@Override
	public void doTest(DoubleArgumentDefinition doubleArgumentDefinition, double value) throws RuleNotObservedException {
		if(!pattern.matcher(String.valueOf(value)).matches()) {
			throw new RuleNotObservedException(doubleArgumentDefinition.getPrefixesAsString(), expressionDescription, String.valueOf(value));
		}
	}
}
