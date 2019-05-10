package argument.exception;

/**
 * An exception for ignored argument rules.
 *
 * @author Steven Cybinski
 * @version 0.1
 */
public class RuleNotObservedException extends Exception {
	private static final long serialVersionUID = -4580623397713062523L;
	private String prefix;
	private String ruleDescription;
	private String value;

	/**
	 * Constructor of the class.
	 * 
	 * @param prefix			Argument prefix with contains the rule.
	 * @param ruleDescription	Description of the rule.
	 * @param value				Value with guilty of the exception.
	 */
	public RuleNotObservedException(String prefix, String ruleDescription, String value) {
		this.prefix = prefix;
		this.ruleDescription = ruleDescription;
		this.value = value;
	}
	
	/**
	 * Return the error as text.
	 */
	public String toString() {
		return "The rule '"+ruleDescription+"' for argument "+prefix+" is not observed with the argument '"+value+"'.";
	}
	
	/**
	 * Return the error as text.
	 */
	@Override
	public String getMessage() {
		return toString();
	}
}
