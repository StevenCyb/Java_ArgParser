package argument.exception;

import argument.definition.ArgumentDefinition;

/**
 * An exception class for missing arguments.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class MissingArgumentException extends Exception {
	private static final long serialVersionUID = -6678172170597320309L;
	private ArgumentDefinition argumentDefinition;

	/**
	 * Constructor of the class.
	 * 
	 * @param argumentDefinition	Argument definition with misses a argument.
	 */
	public MissingArgumentException(ArgumentDefinition argumentDefinition) {
		this.argumentDefinition = argumentDefinition;
	}
	
	/**
	 * Return the error as text.
	 */
	public String toString() {
		return "Missing argument:\r\n" +
				argumentDefinition.getPrefixesAsString() +
				"\t" + argumentDefinition.getDescription();
	}
	
	/**
	 * Return the error as text.
	 */
	@Override
	public String getMessage() {
		return toString();
	}
}
