package junit.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import argument.definition.doubIe.DoubleArgumentDefinition;
import argument.definition.flag.FlagArgumentDefinition;
import argument.definition.integer.IntegerArgumentDefinition;
import argument.definition.string.StringArgumentDefinition;
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
public class TestGeneral {
	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static final PrintStream originalOut = System.out;

	public TestGeneral() {}
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	@Test 
	public void testHelp() {
		try {
			ArgumentParser paramParser = new ArgumentParser(false, true, true);
			paramParser.addHelpArgument(new String[] {"-h"}, "Title");
			paramParser.add(new IntegerArgumentDefinition(new String[] {"-i"}, "Desc.", false, 0));
			paramParser.add(new DoubleArgumentDefinition(new String[] {"-d"}, "Desc.", false, 0.0));
			paramParser.add(new StringArgumentDefinition(new String[] {"-s"}, "Desc.", false, "test"));
			paramParser.add(new FlagArgumentDefinition(new String[] {"-f","-flag"}, "Desc.", false, false));
			paramParser.parse(new String[] {"-h"});
		    assertEquals("Title\r\n" + 
		    		"{-i}                 Required: no    Desc. (default '0')\r\n" + 
		    		"{-d}                 Required: no    Desc. (default '0.0')\r\n" + 
		    		"{-s}                 Required: no    Desc. (default 'test')\r\n" + 
		    		"{-f,-flag}           Required: no    Desc. (default 'no')\r\n" + 
		    		"\r\n", outContent.toString());
		} catch (Exception e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test(expected = UnknownArgumentException.class)
	public void testUnknownArgument() throws UnknownArgumentException {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.parse(new String[] {"-i", "1"});
		} catch (MissingArgumentException | ValueParseException | RuleNotObservedException e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test(expected = MissingArgumentException.class)
	public void testMissingArgument() throws MissingArgumentException  {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new IntegerArgumentDefinition(new String[] {"-a"}, "Int argument", true, 0));
			paramParser.parse(new String[] {});
		} catch (NonUniquePrefixException | UnknownArgumentException | ValueParseException | RuleNotObservedException e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
	
	@Test(expected = ValueParseException.class)
	public void testDatatypeErrorArgument() throws ValueParseException  {
		try {
			ArgumentParser paramParser = new ArgumentParser(true, true, true);
			paramParser.add(new IntegerArgumentDefinition(new String[] {"-i"}, "Int argument", true, 0));
			paramParser.parse(new String[] {"-i", "test"});
		} catch (UnknownArgumentException | MissingArgumentException | NonUniquePrefixException | RuleNotObservedException e) {
			fail("Unwanted exception: "+e.getMessage());
		}
	}
}
