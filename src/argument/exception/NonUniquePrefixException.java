package argument.exception;

import argument.definition.ArgumentDefinition;

/**
 * An exception class for non-unique argument definitions.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class NonUniquePrefixException extends Exception {
	private static final long serialVersionUID = 9048481593026313988L;
	private ArgumentDefinition oldArgumentDefinition;
	private ArgumentDefinition newArgumentDefinition;
	
	/**
	 * Constructor of the class.
	 * 
	 * @param oldArgumentDefinition	One of the argument definitions with cause the exception.
	 * @param newArgumentDefinition	One of the argument definitions with cause the exception.
	 */
	public NonUniquePrefixException(ArgumentDefinition oldArgumentDefinition, ArgumentDefinition newArgumentDefinition) {
		this.oldArgumentDefinition = oldArgumentDefinition;
		this.newArgumentDefinition = newArgumentDefinition;
	}

	/**
	 * Return the error as text.
	 */
	public String toString() {
	      return "Two argument definitions have one or more identical prefixes:\r\n" +
		    		 "AD1: "+oldArgumentDefinition.getPrefixesAsString() + "\r\n"+
		    		 "AD2: "+newArgumentDefinition.getPrefixesAsString();
	}

	/**
	 * Return the error as text.
	 */
	@Override
	public String getMessage() {
		return toString();
	}
}
