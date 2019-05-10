package argument.definition.string.rules;

import java.net.InetAddress;

import argument.definition.string.StringArgumentDefinition;
import argument.exception.RuleNotObservedException;

/**
 * This class add an rule with verify if an IP address is reachable.
 * 
 * @author Steven Cybinski
 * @version 0.1
 */
public class StringArgumentIpAddressReachableRule implements StringArgumentRule {
	private int msTtimeout;
	/**
	 * Constructor of the class.
	 */
	public StringArgumentIpAddressReachableRule() {
		this(2000);
	}
	/**
	 * Constructor of the class.
	 * 
	 * @param msTtimeout	Timeout for the ping command.
	 */
	public StringArgumentIpAddressReachableRule(int msTtimeout) {
		this.msTtimeout = msTtimeout;
	}

	@Override
	public void doTest(StringArgumentDefinition stringArgumentDefinition, String value) throws RuleNotObservedException {
		try {
			if(!InetAddress.getByName(value).isReachable(msTtimeout)) {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new RuleNotObservedException(stringArgumentDefinition.getPrefixesAsString(), "is reachable", value);
		}
	}
}
