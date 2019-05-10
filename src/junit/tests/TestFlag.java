package junit.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import argument.definition.flag.FlagArgumentDefinition;
import argument.parser.ArgumentParser;

/**
 * 
 * @author Steven Cybinski
 *
 */
public class TestFlag {

	public TestFlag() {}
	
	@Test 
	public void testFlagRequired() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new FlagArgumentDefinition(new String[] {"-f"}, "Flag argument", true, false));
			paramParser.parse(new String[] {"-f"});

			assertEquals(((FlagArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), true);
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test 
	public void testFlagDefault() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new FlagArgumentDefinition(new String[] {"-f"}, "Flag argument", false, true));
			paramParser.parse(new String[] {});

			assertEquals(((FlagArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), true);
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
}
