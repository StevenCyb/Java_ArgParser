package junit.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import argument.definition.rules.RegularExpressionCollection;
import argument.definition.string.StringArgumentDefinition;
import argument.definition.string.rules.StringArgumentDirectoryExistsRule;
import argument.definition.string.rules.StringArgumentIpAddressReachableRule;
import argument.definition.string.rules.StringArgumentRegularExpressionRule;
import argument.definition.string.rules.StringArgumentRule;
import argument.exception.MissingArgumentException;
import argument.exception.NonUniquePrefixException;
import argument.exception.RuleNotObservedException;
import argument.exception.UnknownArgumentException;
import argument.exception.ValueParseException;
import argument.parser.ArgumentParser;

/**
 * 
 * @author Steven Cybinski
 *
 */
public class TestString {

	public TestString() {
	}
	
	@Test 
	public void testStringRequired() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new StringArgumentDefinition(new String[] {"-s"}, "String argument", true, "default"));
			paramParser.parse(new String[] {"-s", "test"});
			assertEquals(((StringArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), "test");
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test 
	public void testStringDefault() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new StringArgumentDefinition(new String[] {"-s"}, "String argument", false, "default"));
			paramParser.parse(new String[] {});
			assertEquals(((StringArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), "default");
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test 
	public void testStringRulesOk() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new StringArgumentDefinition(new String[] {"-s"}, "String argument", false, "default", new StringArgumentRule[] {
					new StringArgumentRegularExpressionRule("^[a-z]*$")
				}));
			paramParser.parse(new String[] {"-s", "test"});
			assertEquals(((StringArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), "test");
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test 
	public void testStringRulesOkAdvanced() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new StringArgumentDefinition(new String[] {"-s"}, "String argument", false, "default", new StringArgumentRule[] {
					new StringArgumentRegularExpressionRule("^.*$"),
					new StringArgumentDirectoryExistsRule()
				}));
			paramParser.add(new StringArgumentDefinition(new String[] {"-ip"}, "Local IP", false, "0.0.0.0", new StringArgumentRule[] {
					new StringArgumentRegularExpressionRule(RegularExpressionCollection.IP_FORMAT()),
					new StringArgumentIpAddressReachableRule(1000)
				}));
			paramParser.parse(new String[] {"-s", TestString.class.getProtectionDomain().getCodeSource().getLocation().getPath(),
					"-ip","127.0.0.1"});
			assertEquals(((StringArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), TestString.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test(expected = RuleNotObservedException.class)
	public void testStringRulesNkRegex() throws RuleNotObservedException {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new StringArgumentDefinition(new String[] {"-s"}, "String argument", false, "default", new StringArgumentRule[] {
					new StringArgumentRegularExpressionRule("^[0-9]*$")
				}));
			paramParser.parse(new String[] {"-s", "test"});
			fail();
		} catch (MissingArgumentException | NonUniquePrefixException | UnknownArgumentException | ValueParseException e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
}
