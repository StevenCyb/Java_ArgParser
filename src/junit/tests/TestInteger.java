package junit.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


import org.junit.Test;

import argument.definition.integer.IntegerArgumentDefinition;
import argument.definition.integer.rules.IntegerArgumentProportionRule;
import argument.definition.integer.rules.IntegerArgumentRegularExpressionRule;
import argument.definition.integer.rules.IntegerArgumentRule;
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
public class TestInteger {

	public TestInteger() {}
	
	@Test 
	public void testIntegerRequired() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new IntegerArgumentDefinition(new String[] {"-i"}, "Int argument", true, 0));
			paramParser.parse(new String[] {"-i", "1"});

			assertEquals(((IntegerArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), 1);
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test 
	public void testIntegerDefault() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new IntegerArgumentDefinition(new String[] {"-i"}, "Int argument", false, 1));
			paramParser.parse(new String[] {});

			assertEquals(((IntegerArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), 1);
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test 
	public void testIntegerRulesOk() {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new IntegerArgumentDefinition(new String[] {"-i"}, "Int argument", true, 0, new IntegerArgumentRule[] {
					new IntegerArgumentProportionRule(NumberProportionRuleType.GREATER, 4),
					new IntegerArgumentProportionRule(NumberProportionRuleType.LESS, 6),
					new IntegerArgumentProportionRule(NumberProportionRuleType.GREATER_EQUAL, 4),
					new IntegerArgumentProportionRule(NumberProportionRuleType.LESS_EQUAL, 6),
					new IntegerArgumentRegularExpressionRule("^[0-9]{1}$") 
				}));
			paramParser.parse(new String[] {"-i", "5"});
			assertEquals(((IntegerArgumentDefinition)paramParser.getArgumentDefinitions().get(0)).getValue(), 5);
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test(expected = RuleNotObservedException.class)
	public void testIntegerRulesNkPropoortion() throws RuleNotObservedException {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new IntegerArgumentDefinition(new String[] {"-i"}, "Int argument", true, 0, new IntegerArgumentRule[] {new IntegerArgumentProportionRule(NumberProportionRuleType.GREATER, 4)}));
			paramParser.parse(new String[] {"-i", "4"});
			fail();
		} catch (MissingArgumentException | NonUniquePrefixException | UnknownArgumentException | ValueParseException e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test(expected = RuleNotObservedException.class)
	public void testIntegerRulesNkRegex() throws RuleNotObservedException {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new IntegerArgumentDefinition(new String[] {"-i"}, "Int argument", true, 0, new IntegerArgumentRule[] {new IntegerArgumentRegularExpressionRule("^[a-z]*?")}));
			paramParser.parse(new String[] {"-i", "4"});
			fail();
		} catch (MissingArgumentException | NonUniquePrefixException | UnknownArgumentException | ValueParseException e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
}
