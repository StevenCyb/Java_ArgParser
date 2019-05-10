package argument.definition.string.rules;

import java.io.File;

import argument.definition.string.StringArgumentDefinition;
import argument.exception.RuleNotObservedException;

/**
 * This class add an rule with verify if the directory exists.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class StringArgumentDirectoryExistsRule implements StringArgumentRule {
	/**
	 * Constructor of the class.
	 */
	public StringArgumentDirectoryExistsRule() {
	}

	@Override
	public void doTest(StringArgumentDefinition stringArgumentDefinition, String value) throws RuleNotObservedException {
		File file = new File(value);
		if(!file.exists()) { 
			throw new RuleNotObservedException(stringArgumentDefinition.getPrefixesAsString(), "directory exists", value);
		} else if (!file.isDirectory()) {
			throw new RuleNotObservedException(stringArgumentDefinition.getPrefixesAsString(), "is a directory", value);
		}
	}
}
