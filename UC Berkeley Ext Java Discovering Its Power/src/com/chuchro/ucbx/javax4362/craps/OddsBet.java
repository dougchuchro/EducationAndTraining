package com.chuchro.ucbx.javax4362.craps;

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
public abstract class OddsBet extends Bet {
	/**	Defines the maximum amount of an Odds bet as a multiple of the amount of the Line bet amount
	 *	For example, with a value of 2, the maximum Odds bet on a Pass Line bet of $10 is $20	*/
	public static final int MAX_ODDS_MULTIPLE = 2;

	/**
	 * Constructor for an Odds bet, which calls the super (Bet) constructor, then sets the Payout Ratio, winning 
	 * and losing rolls.
	 * @param betName		Name of the bet type.
	 * @param point			Point set at the come out roll of the Line bet.
	 * @param minAmount		Theoretical minimum amount of the bet to be placed.
	 * @param maxAmount 	Theoretical maximum amount of the bet to be placed.
	 * @param amtMultiple	Bet must be a multiple of this amount to have an integer payout.
	 * @param payoutRatio	Ratio of payout-to-bet amount. For example 3/2 pays $3 on a $2 bet.
	 */
	OddsBet(String betName, int point, int minAmount, int maxAmount, int amtMultiple, Double payoutRatio)	{
		super(betName, minAmount, maxAmount, amtMultiple);
		super.payoutRatio = payoutRatio;
		super.winners.add(new Integer(point));
		super.losers.add(new Integer(7));
	}

}
