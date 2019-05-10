package argument.definition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parent class for an argument definition.
 * Do not use this class for an argument definition!
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class ArgumentDefinition {
	private List<String> prefixes;
	private String description = "This argument has no description";
	private Boolean required = false;
	protected Boolean isSet = false;

	/**
	 * Constructor of the Class.
	 * 
	 * @param prefixes		Set prefixes that can be used to get help. E.g. "-help, -h".
	 * @param description	Add a description to this argument like "IP of the target server.".
	 * @param required		Determine whether this argument is required.
	 */
	public ArgumentDefinition(String[] prefixes, String description, Boolean required) {
		this.prefixes = new ArrayList<String>(Arrays.asList(prefixes));
		this.description = description;
		this.required = required;
	}

	/**
	 *  Check if this argument definition have the same prefix as the given one.
	 * 
	 * @param argumentDefinition	Specify the argument definition you want to check. 
	 * @return						Return if the prefixes are the same.
	 */
	public boolean samePrefixes(ArgumentDefinition argumentDefinition) {
		String[] prefixes = argumentDefinition.getPrefixes();
		for (String a : prefixes) {
			if (this.prefixes.contains(a)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if the given prefix is set on this argument definition.
	 * 
	 * @param prefix	Prefix with should be searched.
	 * @return			Result of the search.
	 */
	public Boolean isPrefixMatch(String prefix) {
		return this.prefixes.contains(prefix);
	}

	/**
	 * Get all prefixes of this argument definition.
	 * 
	 * @return	Array with all prefixes.
	 */
	public String[] getPrefixes() {
		return prefixes.toArray(new String[0]);
	}

	/**
	 * Get all prefixes of this argument definition as a string.
	 * 
	 * @return	String with all prefixe.
	 */
	public String getPrefixesAsString() {
		String prefixString = "{";
		for (String prefix : prefixes) {
			prefixString += prefix + ",";
		}
		return prefixString.substring(0, prefixString.length() - 1) + "}";
	}

	/**
	 * Check if this argument was used and set correctly.
	 * 
	 * @return If an value is set to this argument definition.
	 */
	public Boolean isSet() {
		return isSet;
	}

	/**
	 * Check if this argument definition is required.
	 * 
	 * @return	Return if it is required.
	 */
	public Boolean isRequired() {
		return required;
	}

	/**
	 * Get the description of this argument definition
	 * (this was set by the constructor).
	 * 
	 * @return	Description of this argument definition.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Return the rules for this argument definition.
	 * 
	 * @return	Get an array with all rules.
	 */
	public String[] getRules() {
		return new String[] {""};
	}
}
