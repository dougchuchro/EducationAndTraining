package com.chuchro.ucbx.javax4362.craps;
import java.util.*;
/**	Odds bet for the a pass line bet or a come line bet.
 * The payout schedule is as follows:
 * <table border="1" cellpadding="3" cellspacing="0"><tbody>
 *	<tr><th>Point</th>	<th>Payout</th>	<th>Amount Multiple</th>	<th>Example</th></tr>
 *	<tr><td>4, 10</td>	<td>1:2</td>	<td>1</td>				<td>$10 bet pays $20</td></tr>
 *	<tr><td>5, 9</td>	<td>2:3</td>	<td>2</td>				<td>$10 bet pays $15</td></tr>
 *	<tr><td>6, 8</td>	<td>5:6</td>	<td>5</td>				<td>$10 bet pays $12</td></tr></tbody></table>
 * 	@author Doug Chuchro																				*/
public class LineOddsBet extends OddsBet {
	/**	Name of the bet type																			*/
	public static String BET_NAME = "Line Odds bet";
	
	/**	Provides a static lookup table of Line bet points and their corresponding Odds bet amount multiple.
	 * 	This table (in the form of a Map) is populated by the static method createPointAmountMultipleMap().
	 * 	@see	#createPointAmountMultipleMap()*/
	private static final Map<Integer, LineOddsBetInfo> LINE_ODDS_MAP = createPointAmountMultipleMap();
	/** Used to populate the LINE_ODDS_MAP with information on all bets of that type.
	 * @return	The static Map table with bet information.												 */
	private static Map<Integer, LineOddsBetInfo> createPointAmountMultipleMap()	{
		Map<Integer, LineOddsBetInfo> tmpMap = new HashMap<Integer, LineOddsBetInfo>();
		tmpMap.put(4, new LineOddsBetInfo(1, new Double(2.0/1.0)));
		tmpMap.put(10,new LineOddsBetInfo(1, new Double(2.0/1.0)));
		tmpMap.put(5, new LineOddsBetInfo(2, new Double(3.0/2.0)));
		tmpMap.put(9, new LineOddsBetInfo(2, new Double(3.0/2.0)));
		tmpMap.put(6, new LineOddsBetInfo(5, new Double(6.0/5.0)));
		tmpMap.put(8, new LineOddsBetInfo(5, new Double(6.0/5.0)));
		return Collections.unmodifiableMap(tmpMap);
	}

	/**	Constructor for LineOddsBet object, calls super with the appropriate parameters.
	 * @param lineBet	Bet to which this Odds bet is attached.
	 * @param point		Point set on the come out roll.
	 * @param chipCount Player's chip count.
	 */
	public LineOddsBet(LineBet lineBet, int point, int chipCount) {
		super(BET_NAME, 																				// betName
			point, 																						// point
			LINE_ODDS_MAP.get(point).amountMultiple, 													// minAmount
			((chipCount<lineBet.amount*MAX_ODDS_MULTIPLE)?chipCount:lineBet.amount*MAX_ODDS_MULTIPLE),	// maxAmount
			LINE_ODDS_MAP.get(point).amountMultiple,													// amountMultiple
			LINE_ODDS_MAP.get(point).payoutRatio);														// payoutRatio
	}
	
	/**	Inner class to hold Line Odds bet information for all of the different Proposition bet types.	*/
	private static class LineOddsBetInfo	{
		Integer	amountMultiple;
		Double payoutRatio;
		/**	Constructor for inner class																	*/
		LineOddsBetInfo(Integer amountMultiple, Double payoutRatio)	{
			this.amountMultiple	= amountMultiple;
			this.payoutRatio	= payoutRatio;
		}
	}
}