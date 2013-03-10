/**
 * 
 */
package com.chuchro.ucbx.javax4362.craps;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Doug Chuchro
 *
 */
public class LineOddsBet extends OddsBet {
	/**	Name of the bet type	*/
	public static String betName = "Line Odds bet";
	
	/**	Provides a static lookup table of Line bet points and their corresponding Odds bet amount multiple.
	 * 	This table (in the form of a Map) is populated by the static method createPointAmountMultipleMap().
	 * 	@see	#createPointAmountMultipleMap()*/
	private static final Map<Integer, Integer> POINT_AMOUNT_MULTIPLE_MAP = createPointAmountMultipleMap(); 
	private static Map<Integer, Integer> createPointAmountMultipleMap()	{
		Map<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();
		tmpMap.put(4, 1);
		tmpMap.put(10,1);
		tmpMap.put(5, 2);
		tmpMap.put(9, 2);
		tmpMap.put(6, 5);
		tmpMap.put(8, 5);
		return Collections.unmodifiableMap(tmpMap);
	}

	/**	Provides a static lookup table of Line bet points and their corresponding Odds bet Payout Ratio.
	 * 	This table (in the form of a Map) is populated by the static method createPointPayoutRatioMap().
	 * 	@see	#createPointPayoutRatioMap()*/
	private static final Map<Integer, Double> POINT_PAYOUT_RATIO_MAP = createPointPayoutRatioMap(); 
	private static Map<Integer, Double> createPointPayoutRatioMap()	{
		Map<Integer, Double> tmpMap = new HashMap<Integer, Double>();
		tmpMap.put(4, new Double(2.0/1.0));
		tmpMap.put(10,new Double(2.0/1.0));
		tmpMap.put(5, new Double(3.0/2.0));
		tmpMap.put(9, new Double(3.0/2.0));
		tmpMap.put(6, new Double(6.0/5.0));
		tmpMap.put(8, new Double(6.0/5.0));
		return Collections.unmodifiableMap(tmpMap);
	}

	/**
	 * @param lineBet	Bet to which this Odds bet is attached.
	 * @param point		Point set on the come out roll.
	 * @param chipCount Player's chip count.
	 */
	public LineOddsBet(LineBet lineBet, int point, int chipCount) {
		super(betName, 																			// betName
				point, 																			// point
				POINT_AMOUNT_MULTIPLE_MAP.get(point), 											// minAmount
				((chipCount<lineBet.amount*MAX_ODDS_MULTIPLE)?chipCount:lineBet.amount*MAX_ODDS_MULTIPLE),	// maxAmount
				POINT_AMOUNT_MULTIPLE_MAP.get(point),											// amountMultiple
				POINT_PAYOUT_RATIO_MAP.get(point));												// payoutRatio
	}

}
