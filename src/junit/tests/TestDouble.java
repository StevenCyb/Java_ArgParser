package junit.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import argument.definition.doubIe.DoubleArgumentDefinition;
import argument.definition.doubIe.rules.DoubleArgumentProportionRule;
import argument.definition.doubIe.rules.DoubleArgumentRegularExpressionRule;
import argument.definition.doubIe.rules.DoubleArgumentRule;
import argument.definition.rules.NumberProportionRuleType;
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
public class TestDouble {

	public TestDouble() {}
	
	@Test 
	public void testDoubleRequired() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new DoubleArgumentDefinition(new String[] {"-d"}, "Double argument", true, 0.0));
			paramParser.parse(new String[] {"-d", "2.2"});

			assertEquals(((DoubleArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), 2.2, 1);
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test 
	public void testDoubleDefault() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new DoubleArgumentDefinition(new String[] {"-d"}, "Double argument", false, 2.2));
			paramParser.parse(new String[] {});

			assertEquals(((DoubleArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), 2.2, 1);
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test 
	public void testDoubleRulesOk() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new DoubleArgumentDefinition(new String[] {"-d"}, "Double argument", true, 0.0, new DoubleArgumentRule[] {
					new DoubleArgumentProportionRule(NumberProportionRuleType.GREATER, 4.0),
					new DoubleArgumentProportionRule(NumberProportionRuleType.LESS, 6.0),
					new DoubleArgumentProportionRule(NumberProportionRuleType.GREATER_EQUAL, 4.0),
					new DoubleArgumentProportionRule(NumberProportionRuleType.LESS_EQUAL, 6.0),
					new DoubleArgumentRegularExpressionRule("^[0-9]*\\.[0-9]*$") 
				}));
			paramParser.parse(new String[] {"-d", "5.0"});
			assertEquals(((DoubleArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), 5.0 ,1);
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test(expected = RuleNotObservedException.class)
	public void testDoubleRulesNkPropoortion() throws RuleNotObservedException {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new DoubleArgumentDefinition(new String[] {"-d"}, "Double argument", true, 0.0, new DoubleArgumentRule[] {new DoubleArgumentProportionRule(NumberProportionRuleType.GREATER, 4.0)}));
			paramParser.parse(new String[] {"-d", "4.0"});
			fail();
		} catch (MissingArgumentException | NonUniquePrefixException | UnknownArgumentException | ValueParseException e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test(expected = RuleNotObservedException.class)
	public void testDoubleRulesNkRegex() throws RuleNotObservedException {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new DoubleArgumentDefinition(new String[] {"-d"}, "Double argument", true, 0.0, new DoubleArgumentRule[] {new DoubleArgumentRegularExpressionRule("^[a-z]*?")}));
			paramParser.parse(new String[] {"-d", "4.0"});
			fail();
		} catch (MissingArgumentException | NonUniquePrefixException | UnknownArgumentException | ValueParseException e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
}
