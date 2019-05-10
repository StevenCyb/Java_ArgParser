package argument.definition.flag;

import argument.definition.ArgumentDefinition;

/**
 * Argument definition for an flag argument definition.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class FlagArgumentDefinition extends ArgumentDefinition{
	private Boolean value;
	private Boolean defaultValue;

	/**
	 * Constructor of the Class.
	 * 
	 * @param prefixes		Set prefixes that can be used to get help. E.g. "-flag, -f".
	 * @param description	Add a description to this argument like "IP of the target server.".
	 * @param required		Determine whether this argument is required.
	 * @param defaultValue	What should be the default value, if this argument is not set by the user.
	 */
	public FlagArgumentDefinition(String[] prefixes, String description, Boolean required, Boolean defaultValue) {
		super(prefixes, description, required);
		this.defaultValue = defaultValue;
		this.value = defaultValue;
	}

	/**
	 * Get the value with was set by the user or the default.
	 * 
	 * @return	Get the value.
	 */
	public Boolean getValue() {
		return value;
	}

	/**
	 * Set a value to this argument definition.
	 */
	public void setValue() {
		isSet = true;
		this.value = !this.value;
	}

	/**
	 * Get the description of this argument definition
	 * (this was set by the constructor).
	 * 
	 * @return	Description of this argument definition.
	 */
	public String getDescription() {
		return super.getDescription()+(!super.isRequired()?" (default '"+(defaultValue?"yes":"no")+"')":"");
	}

	/**
	 * Return the rules for this argument definition.
	 * 
	 * @return	Return empty array because flag cannot have rules.
	 */
	@Override
	public String[] getRules() {
		return new String[] {""};
	}
}
