package com.chuchro.ucbx.javax4362.craps;
import java.util.*;
/**	DontOddsBet is similar to a OddsBet, except that it's attached to a DontPassBet or DontComeBet.
 * Like OddsBet, the DontOddBet pays true odds based on the point established.
 * The payout schedule is as follows:
 *	<table border="1" cellpadding="3" cellspacing="0"><tbody>
 *	<tr><th>Point</th>	<th>Payout</th>	<th>AmountMultiple</th>	<th>Example</th></tr>
 *	<tr><td>4, 10</td>	<td>1-2</td>	<td>2</td>				<td>$20 bet pays $10</td></tr>
 *	<tr><td>5, 9</td>	<td>3-2</td>	<td>3</td>				<td>$15 bet pays $10</td></tr>
 *	<tr><td>6, 8</td>	<td>6-5</td>	<td>6</td>				<td>$12 bet pays $10</td></tr></tbody></table>
 * @author Doug Chuchro																				*/
public class DontOddsBet extends OddsBet {
	/**	Name of the bet type																		*/
	public static String BET_NAME = "Don't Odds bet";

	/**	Static lookup table of Don't bet points and their corresponding Don't Odds bet amount multiple.
	 * 	This table (in the form of a Map) is populated by the static method createPointAmountMultipleMap().
	 * 	@see	#createPointAmountMultipleMap()														*/
	private static final Map<Integer, DontOddsBetInfo> DONT_ODDS_MAP = createPointAmountMultipleMap(); 
	private static Map<Integer, DontOddsBetInfo> createPointAmountMultipleMap()	{
		Map<Integer, DontOddsBetInfo> tmpMap = new HashMap<Integer, DontOddsBetInfo>();
		tmpMap.put(4, new DontOddsBetInfo(2, new Double(1.0/2.0)));
		tmpMap.put(4, new DontOddsBetInfo(2, new Double(1.0/2.0)));
		tmpMap.put(4, new DontOddsBetInfo(3, new Double(2.0/3.0)));
		tmpMap.put(4, new DontOddsBetInfo(3, new Double(2.0/3.0)));
		tmpMap.put(4, new DontOddsBetInfo(6, new Double(5.0/6.0)));
		tmpMap.put(4, new DontOddsBetInfo(6, new Double(5.0/6.0)));
		return Collections.unmodifiableMap(tmpMap);
	}
	
	/**	Constructor Don't Odds bet.
	 * @param lineBet	Bet to which this Don't Odds bet is attached.
	 * @param point		Point set on the come out roll.
	 */
	public DontOddsBet(LineBet lineBet, int point, int chipCount) {
		super(BET_NAME,																						// betName
				point, 																						// point
				DONT_ODDS_MAP.get(point).amountMultiple, 													// minAmount
				((chipCount<lineBet.amount*MAX_ODDS_MULTIPLE)?chipCount:lineBet.amount*MAX_ODDS_MULTIPLE),	// maxAmount
				DONT_ODDS_MAP.get(point).amountMultiple,													// amountMultiple
				DONT_ODDS_MAP.get(point).payoutRatio);														// payoutRatio
		winners.clear();
		winners.add(new Integer(7));
		losers.clear();
		losers.add(new Integer(point));
	}
	
	/**	Inner class to hold Don't Odds bet information for all of the different Proposition bet types.	*/
	private static class DontOddsBetInfo	{
		Integer	amountMultiple;
		Double payoutRatio;
		/**	Constructor for inner class																	*/
		DontOddsBetInfo(Integer amountMultiple, Double payoutRatio)	{
			this.amountMultiple	= amountMultiple;
			this.payoutRatio	= payoutRatio;
		}
	}

}