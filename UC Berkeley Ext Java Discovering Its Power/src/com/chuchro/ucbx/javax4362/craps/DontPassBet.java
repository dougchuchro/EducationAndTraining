package com.chuchro.ucbx.javax4362.craps;

public class DontPassBet extends LineBet {
	public static String betName = "Don't Pass bet";
	public static int 	 minAmount = 5;				// minimum $5 bet
	public static double payoutRatio = 1/1;			// payout for passline bet is 1:1
	public static int 	 amountMultiple = 1;		// pass line bet can be any amount greater than minimum
	public int pushRoll;
	public static int DONT_PASS_PUSH_ROLL = 12;		// If a 12 is rolled coming out on Don't Pass bet, the bet
													// is considered "pushed", neither won nor lost, and the 
													// player gets only his original bet amount back
	
	DontPassBet(int maxAmt)	{
		super(betName, minAmount, maxAmt, amountMultiple);
		super.payoutRatio		= PassLineBet.payoutRatio;
		// Set the winning rolls of a Don't Pass bet: 2 and 3, but not 12 (hence the offset index of 1)
		super.winners.addAll(1, COME_OUT_LINE_BET_LOSERS);
		// Set the losing roll of a Don't Pass bet: 7 and 11
		super.losers.addAll(COME_OUT_LINE_BET_WINNERS);
		// Set the come-out push roll of a Don't Pass bet: 12
		this.pushRoll = DONT_PASS_PUSH_ROLL;	
	}

	/**
	 * Resets the winning and losing rolls for the "Don't" bets: DontPassBet and DontComeBet
	 * These two classes will override setPoint() to call this method.
	 * @param point	The point set be the initial (come out) roll.
	 */
	public void setPoint(int point)	{
		// Clear out the initial winner (7) and add the point as the only winning roll
		super.winners.clear();
		super.winners.add(new Integer(7));
		// Clear out the initial losing rolls (2,3,12) and add 7 as the only losing roll
		super.losers.clear();
		super.losers.add(new Integer(point));	
		// After the come-out roll (when the point is set), 12 is no longer a roll that "pushes"
		this.pushRoll = 0;
	}
	
	/**
	 * Overrides Bet.checkBet() because the Don't Pass Bet has a corner case where if a 12 is rolled, the bet is pushed.
	 */
	public int checkBet(Dice dice)	{
		// First run the standard checks for winners/losers.
		int result = super.checkBet(dice);
		// Then check to see if the roll was a 12 on the come out roll. Note that this.pushRoll will only be 12
		// on the come out roll; we reset it to 0 after the come out roll in DontPassBet.setPoint() above.
		if (dice.rollSum == this.pushRoll)	{
			System.out.println("Your " + betName + " of $" + amount + " pushed, " +
								"the oringal bet amount will be added to your chip count");
			betStatus = BetStatus.BET_PUSHED;
			result = amount;
		}
		return result;
	}

	public int setOddsBet(int point, int chipCount)	{
		this.oddsBet = new DontOddsBet(this, point, chipCount);
		return this.oddsBet.amount;
	}

}
