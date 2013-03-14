package com.chuchro.ucbx.javax4362.craps;

public class PassLineBet extends LineBet {
	public static String betName = "Pass Line bet";
	public static int 	 minAmount = 5;				// minimum $5 bet
	public static double payoutRatio = 1/1;			// payout for passline bet is 1:1
	public static int[]  losers = { 7 };			// only loser for passline bet is to crap out
	public static int 	 amountMultiple = 1;		// pass line bet can be any amount greater than minimum

	
	PassLineBet(int maxAmt)	{
		super(betName, minAmount, maxAmt, amountMultiple);
		initialize();
	}

	PassLineBet(int maxAmt, String betName)	{
		super(betName, minAmount, maxAmt, amountMultiple);
		initialize();
	}
	
	private void initialize()	{
		super.payoutRatio		= PassLineBet.payoutRatio;
		// Set the winning roll of a Pass Line bet: 7 and 11
		super.winners.addAll(COME_OUT_LINE_BET_WINNERS);
		// Set the losing rolls of a Pass Line bet: 2, 3, and 12
		super.losers.addAll(COME_OUT_LINE_BET_LOSERS);
	}

	public void setPoint(int point)	{
		// Clear out the initial winner (7) and add the point as the only winning roll
		super.winners.clear();
		super.winners.add(new Integer(point));
		// Clear out the initial losing rolls (2,3,12) and add 7 as the only losing roll
		super.losers.clear();
		super.losers.add(new Integer(7));
	}

	public int setOddsBet(int point, int chipCount)	{
		this.oddsBet = new LineOddsBet(this, point, chipCount);
		return this.oddsBet.amount;
	}

}
