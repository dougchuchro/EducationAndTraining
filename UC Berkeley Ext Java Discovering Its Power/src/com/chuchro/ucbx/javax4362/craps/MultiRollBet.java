package com.chuchro.ucbx.javax4362.craps;

/**
 * This abstract class represents all types of bets that may require multiple rolls to be determined.
 * Bets of this type will be represented by their own class that extend this class and include: PassLineBet,
 * DontPassBet, ComeBet, and DontComeBet
 * It also extends the abstract Bet class, which contains fields and methods common to all Bets.
 * @author Doug Chuchro
 * @see com.chuchro.ucbx.javax4362.craps.Bet
 * @see com.chuchro.ucbx.javax4362.craps.PassLineBet
 * @see com.chuchro.ucbx.javax4362.craps.DontPassBet
 * @see com.chuchro.ucbx.javax4362.craps.ComeLineBet
 * @see com.chuchro.ucbx.javax4362.craps.DontComeBet
 *
 */
public abstract class MultiRollBet extends Bet {
	/** The Odds Bet that is optionally attached to a Line Bet of any type	*/
	public OddsBet 			 oddsBet;

	/**
	 * Default constructor, calls super
	 * @param betName
	 * @param minAmt
	 * @param maxAmt
	 * @param amountMultiple
	 */
	MultiRollBet(String betName, int minAmt, int maxAmt, int amountMultiple) {
		super(betName, minAmt, maxAmt, amountMultiple);
	}

	/**
	 * After the establishing roll is made, this is called to (re)set the winning and losing rolls
	 * based on the point. Note that the implementation here is valid for PassLineBet and ComeLineBet,
	 * it needs to be overridden in the Don't classes for proper behavior.
	 * @param point
	 */
	public void setPoint(int point)	{
		// Clear out the initial winner (7) and add the point as the only winning roll
		super.winners.clear();
		super.winners.add(new Integer(point));
		// Clear out the initial losing rolls (2,3,12) and add 7 as the only losing roll
		super.losers.clear();
		super.losers.add(new Integer(7));
	}

	/**
	 * Resets the winning and losing rolls for the "Don't" bets: DontPassBet and DontComeBet
	 * These two classes will override setPoint() to call this method.
	 * @param point
	 */
	public void setDontPoint(int point)	{
		// Clear out the initial winner (7) and add the point as the only winning roll
		super.winners.clear();
		super.winners.add(new Integer(7));
		// Clear out the initial losing rolls (2,3,12) and add 7 as the only losing roll
		super.losers.clear();
		super.losers.add(new Integer(point));	
	}
	
	/**
	 * Sets the Odds Bet that is optionally attached to this Line Bet
	 * @param ob
	 */
	public void setOddsBet(OddsBet oddsBet)	{
		this.oddsBet = oddsBet;
	}
	
	public int checkBet(Dice dice)	{
		// First, run the standard check: Bet.checkBet()
		int result = super.checkBet(dice);
		// if the Line bet was won, then also pay out the Odds bet (if there is one)
		if ((betStatus == Bet.BetStatus.BET_WON || betStatus == Bet.BetStatus.BET_LOST) && this.oddsBet != null)	{
			result = result + this.oddsBet.checkBet(dice);
		}
		return result;
	}
	

}
