package argument.definition.rules;

import argument.exception.NotSupportedInThisVersionException;

/**
 * This class contains some basic regular expressions. Most of theme are from other source (with are referenced).
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class RegularExpressionCollection {
	/**
	 * Check if the IPv4 format is valid.
	 * 
	 * @return Regular expression string.
	 */
	public static String IP_FORMAT() {
		// Source: https://www.oreilly.com/library/view/regular-expressions-cookbook/9780596802837/ch07s16.html
		return "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
	}
	
	/**
	 * Check if the URL format is valid.
	 * 
	 * @return Regular expression string.
	 */
	public static String URL_FORMAT() {
		// Source: https://projects.lukehaas.me/regexhub/
		return "/^((https?|ftp|file):\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$/";
	}
	
	/**
	 * Check if HEX color format is valid.
	 * 
	 * @return Regular expression string.
	 */
	public static String COLOR_HEX_FORMAT() {
		// Source: https://projects.lukehaas.me/regexhub/
		return "/^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$/";
	}
	
	/**
	 * Check if E-Mail address is valid.
	 * 
	 * @return  Regular expression string.
	 */
	public static String E_MAIL_FORMAT() {
		// Source: https://emailregex.com/
		return "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	}
	
	/**
	 * Check if PIN={0-9} format is valid.
	 * 
	 * @param length								Exact length of the PIN.
	 * @return										Regular expression string.
	 * @throws NotSupportedInThisVersionException	Throw an exception if parameter are invalid.
	 */
	public static String PIN_FORMAT(int length) throws NotSupportedInThisVersionException {
		if(length<=0) {
			throw new NotSupportedInThisVersionException("PIN with negative length");
		}
		return "^[0-9]{"+length+"}?";
	}
	
	/**
	 * Check if PIN={0-9} format is valid.
	 * 
	 * @param minLength								Min. length of the PIN.
	 * @param maxLength								Max. length of the PIN.
	 * @return										Regular expression string.
	 * @throws NotSupportedInThisVersionException	Throw an exception if parameter are invalid.
	 */
	public static String PIN_FORMAT(int minLength, int maxLength) throws NotSupportedInThisVersionException {
		if(minLength<=0) {
			throw new NotSupportedInThisVersionException("PIN with negative min. length");
		} else if(maxLength<=0) {
			throw new NotSupportedInThisVersionException("PIN with negative max. length");
		} else if(minLength>=maxLength) {
			throw new NotSupportedInThisVersionException("PIN with greater min. than max. length");
		}
		return "^[0-9]{"+minLength+","+maxLength+"}?";
	}
	
	/**
	 * Check if the path the path format is valid
	 * (Distinguishes between operating systems).
	 * 
	 * @return										Regular expression string.
	 * @throws NotSupportedInThisVersionException	Throw an exception if parameter are invalid.
	 */
	public static String PATH_FORMAT() throws NotSupportedInThisVersionException {
		// OS detection source: https://stackoverflow.com/questions/14288185/detecting-windows-or-linux @Domenico Monaco
		String OS = System.getProperty("os.name").toLowerCase();
		if(OS.contains("win")) {
			// Source: https://www.oreilly.com/library/view/regular-expressions-cookbook/9781449327453/ch08s18.html
			return "^(?:(?:[a-zA-Z]:|\\\\\\\\[a-zA-Z0-9_.$.-]+\\\\[a-zA-Z0-9_.$.-]+)\\\\|\\\\?[^\\:*?\\\"<>|\\r\\n]+\\\\?)(?:[^\\:*?\\\"<>|\\r\\n]+\\\\)*[^\\:*?\\\"<>|\\r\\n]*$";
		} else if (OS.contains("nix") || OS.contains("nux") || OS.contains("aix")) {
			return "^([.]?[\\.\\.\\/]?[\\/]?){1}([a-zA-Z0-9_ -]*\\/)*([a-zA-Z0-9_ -])*((\\.)[a-zA-Z0-9]*)?$";
		} else if(OS.contains("mac")) {
			throw new NotSupportedInThisVersionException("Regular expression for path validation");
		}
		throw new NotSupportedInThisVersionException("Regular expression for path validation for OS["+OS+"]");
	}
}