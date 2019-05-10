package argument.exception;

import argument.definition.ArgumentDefinition;

/**
 * An exception class for data type parsing problems.
 *
 * @author Steven Cybinski
 * @version 0.1
 */
public class ValueParseException extends Exception {
	private static final long serialVersionUID = 6529552685688129815L;
	private ArgumentDefinition argumentDefinition;
	private String problemArgument;

	/**
	 * Constructor of the class.
	 * 
	 * @param argumentDefinition	Argument definition object with invoke the exception.
	 * @param problemArgument		Argument with invoke the exception.
	 */
	public ValueParseException(ArgumentDefinition argumentDefinition, String problemArgument) {
		this.argumentDefinition = argumentDefinition;
		this.problemArgument = problemArgument;
	}
	
	/**
	 * Return the error as text.
	 */
	public String toString() {
	      return "Parsing error for argument '"+problemArgument+"'.\r\n" +
	    		  "This argument has the following policy:\r\n" + argumentDefinition.getRules();
	}
	
	/**
	 * Return the error as text.
	 */
	@Override
	public String getMessage() {
		return toString();
	}
}
