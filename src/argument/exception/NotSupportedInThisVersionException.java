package argument.exception;

/**
 * An exception class for unsupported stuff.
 *
 * @author Steven Cybinski
 * @version 0.1
 */
public class NotSupportedInThisVersionException extends Exception {
	private static final long serialVersionUID = -4580623397713062523L;
	private String description;

	/**
	 * Constructor of the class.
	 * 
	 * @param description	Describe the exception.
	 */
	public NotSupportedInThisVersionException(String description) {
		this.description = description;
	}

	/**
	 * Return the error as text.
	 */
	public String toString() {
		return description+" is not supported at this moment.";
	}

	/**
	 * Return the error as text.
	 */
	@Override
	public String getMessage() {
		return toString();
	}
}
