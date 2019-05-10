package argument.exception;

/**
 * An exception class for unspecified arguments.
 *
 * @author Steven Cybinski
 * @version 0.1
 */
public class UnknownArgumentException extends Exception {
	private static final long serialVersionUID = 1L;
	private String argument = "";
	
	/**
	 * Constructor of the class.
	 * 
	 * @param argument	Argument with invoke the exception.
	 */
	public UnknownArgumentException(String argument) {
		this.argument = argument;
	}

	/**
	 * Return the error as text.
	 */
	public String toString() {
		return "Unknown argument '"+argument+"'";
	}

	/**
	 * Return the error as text.
	 */
	@Override
	public String getMessage() {
		return toString();
	}
}
