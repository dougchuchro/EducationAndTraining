package com.chuchro.ucbx.javax4362.craps;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An OddsBet is attached to any LineBet type and is (optionally) set after the point of the line bet is set.
 * Unlike the LineBet, OddsBet pays true odds based on the point established.  For example, if a PassLineBet or
 * ComeLineBet has it's point set to 5, the odds of rolling the point (4 possible rolls) before rolling craps (6
 * possible rolls) is 4/6 or 2/3, so a $10 bet will pay $15.  This is the only bet in a casino where the house
 * has no advantage.  To avoid, non-integer results, the amountMultiple differs depending on the point. There 
 * is no minimum bet, aside from apart from the AmmountMultiple. The maximum amount of the bet varies by casino,
 * but us usually 2 times the amount of the Pass or Come bet.
 * The payout schedule is as follows:
 * 		Point	Payout	AmountMultiple	Example
 * 		4, 10	2-1		1				$10 bet pays $20
 * 		5, 9	3-2		2				$10 bet pays $15
 * 		6, 8	6-5		5				$10 bet pays $12
 * 
 * @author Doug Chuchro
 *
 */
public class OddsBet extends Bet {
	/**		*/
	public static String betName = "Odds bet";

	/**	Defines the maximum amount of an Odds bet as a multiple of the amount of the Line bet amount
	 *	For example, with a value of 2, the maximum Odds bet on a Pass Line bet of $10 is $20	*/
	public static final int MAX_ODDS_MULTIPLE = 2;
	
	/**	Provides a static lookup table of Line bet points and their corresponding Odds bet amount multiple.
	 * 	This table (in the form of a Map) is populated by the static method createPointAmountMultipleMap().
	 * 	@see	#createPointAmountMultipleMap()*/
	public static final Map<Integer, Integer> POINT_AMOUNT_MULTIPLE_MAP = createPointAmountMultipleMap();
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
	public static final Map<Integer, Double> POINT_PAYOUT_RATIO_MAP = createPointPayoutRatioMap();
	private static Map<Integer, Double> createPointPayoutRatioMap()	{
		Map<Integer, Double> tmpMap = new HashMap<Integer, Double>();
		tmpMap.put(4, new Double(2/1));
		tmpMap.put(10,new Double(2/1));
		tmpMap.put(5, new Double(3/2));
		tmpMap.put(9, new Double(3/2));
		tmpMap.put(6, new Double(6/5));
		tmpMap.put(8, new Double(6/5));
		return Collections.unmodifiableMap(tmpMap);
	}

	/**
	 * Constructor for an Odds bet, which calls the super (Bet) constructor, then sets the Payout Ratio, winning 
	 * and losing rolls.
	 * @param b		Line bet to which this Odds bet is attached.
	 * @param point Point set at the come out roll of the Line bet.
	 */
	OddsBet(LineBet b, int point)	{
		super(betName, 1, b.amount*MAX_ODDS_MULTIPLE, POINT_AMOUNT_MULTIPLE_MAP.get(point));
		super.payoutRatio = POINT_PAYOUT_RATIO_MAP.get(point);
		super.winners.add(new Integer(point));
		super.losers.add(new Integer(7));
	}

}
