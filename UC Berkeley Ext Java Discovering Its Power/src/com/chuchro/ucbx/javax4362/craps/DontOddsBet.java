/**
 * 
 */
package com.chuchro.ucbx.javax4362.craps;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * DontOddsBet is similar to a OddsBet, except that it's attached to a DontPassBet or DontComeBet.
 * Like OddsBet, the DontOddBet pays true odds based on the point established.
 * The payout schedule is as follows:
 * 		Point	Payout	AmountMultiple	Example
 * 		4, 10	1-2		2				$20 bet pays $10
 * 		5, 9	3-2		3				$15 bet pays $10
 * 		6, 8	6-5		6				$12 bet pays $10
 * 
 * @author Doug Chuchro
 *
 */
public class DontOddsBet extends OddsBet {
	/**	Name of the bet type	*/
	public static String betName = "Don't Odds bet";

	/**	Static lookup table of Don't bet points and their corresponding Don't Odds bet amount multiple.
	 * 	This table (in the form of a Map) is populated by the static method createPointAmountMultipleMap().
	 * 	@see	#createPointAmountMultipleMap()*/
	private static final Map<Integer, Integer> POINT_AMOUNT_MULTIPLE_MAP = createPointAmountMultipleMap(); 
	private static Map<Integer, Integer> createPointAmountMultipleMap()	{
		Map<Integer, Integer> tmpMap = new HashMap<Integer, Integer>();
		tmpMap.put(4, 2);
		tmpMap.put(10,2);
		tmpMap.put(5, 3);
		tmpMap.put(9, 3);
		tmpMap.put(6, 6);
		tmpMap.put(8, 6);
		return Collections.unmodifiableMap(tmpMap);
	}
	
	/**	Provides a static lookup table of Don't bet points and their corresponding Odds bet Payout Ratio.
	 * 	This table (in the form of a Map) is populated by the static method createPointPayoutRatioMap().
	 * 	@see	#createPointPayoutRatioMap()*/
	private static final Map<Integer, Double> POINT_PAYOUT_RATIO_MAP = createPointPayoutRatioMap(); 
	private static Map<Integer, Double> createPointPayoutRatioMap()	{
		Map<Integer, Double> tmpMap = new HashMap<Integer, Double>();
		tmpMap.put(4, new Double(1.0/2.0));
		tmpMap.put(10,new Double(1.0/2.0));
		tmpMap.put(5, new Double(2.0/3.0));
		tmpMap.put(9, new Double(2.0/3.0));
		tmpMap.put(6, new Double(5.0/6.0));
		tmpMap.put(8, new Double(5.0/6.0));
		return Collections.unmodifiableMap(tmpMap);
	}

	/**
	 * Constructor Don't Odds bet.
	 * @param lineBet	Bet to which this Don't Odds bet is attached.
	 * @param point		Point set on the come out roll.
	 */
	public DontOddsBet(LineBet lineBet, int point, int chipCount) {
		super(betName, 																						// betName
				point, 																						// point
				POINT_AMOUNT_MULTIPLE_MAP.get(point), 														// minAmount
				((chipCount<lineBet.amount*MAX_ODDS_MULTIPLE)?chipCount:lineBet.amount*MAX_ODDS_MULTIPLE),	// maxAmount
				POINT_AMOUNT_MULTIPLE_MAP.get(point),														// amountMultiple
				POINT_PAYOUT_RATIO_MAP.get(point));															// payoutRatio
		// Clear out the default winning rolls (set by Bet constructor) and add the 7 as the only winning roll
		winners.clear();
		winners.add(new Integer(7));
		// Clear out the default losing roll (set by Bet constructor) and add the point as the only losing roll
		losers.clear();
		losers.add(new Integer(point));
	}

}
