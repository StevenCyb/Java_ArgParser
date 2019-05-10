package argument.definition.string.rules;

import java.io.File;

import argument.definition.string.StringArgumentDefinition;
import argument.exception.RuleNotObservedException;

/**
 * This class add an rule with verify if the file exists.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class StringArgumentFileExistsRule implements StringArgumentRule {
	/**
	 * Constructor of the class.
	 */
	public StringArgumentFileExistsRule() {
	}

	@Override
	public void doTest(StringArgumentDefinition stringArgumentDefinition, String value) throws RuleNotObservedException {
		File file = new File(value);
		if(!file.exists()) { 
			throw new RuleNotObservedException(stringArgumentDefinition.getPrefixesAsString(), "file exists", value);
		} else if (file.isDirectory()) {
			throw new RuleNotObservedException(stringArgumentDefinition.getPrefixesAsString(), "is a file", value);
		}
	}
}
