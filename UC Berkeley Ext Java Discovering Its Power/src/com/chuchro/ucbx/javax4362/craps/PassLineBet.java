package com.chuchro.ucbx.javax4362.craps;

public class PassLineBet extends MultiRollBet {
	public static String betName = "Pass Line bet";
	public static int 	 minAmount = 5;				// minimum $5 bet
	public static double payoutRatio = 1/1;			// payout for passline bet is 1:1
	public static int[]  losers = { 7 };			// only loser for passline bet is to crap out
	public static int 	 amountMultiple = 1;		// pass line bet can be any amount greater than minimum
	public Bet 			 passLineOddsBet;			// This bet can only be made after the point is set and
													// has a payout ratio exatly that of the probability of
													// hitting the point.  For example, if the point is 6
													// the probability of hitting the point before crapping
													// out if 5:6, so a $5 bet wins $6
	
	PassLineBet(int maxAmt)	{
		super(betName, minAmount, maxAmt, amountMultiple);
		super.payoutRatio		= PassLineBet.payoutRatio;
		// Set the winning roll of a Pass Line bet: 7 and 11
		super.winners.add(new Integer(7));
		super.winners.add(new Integer(11));
		// Set the losing rolls of a Pass Line bet: 2, 3, and 12
		super.losers.add(new Integer(2));
		super.losers.add(new Integer(3));
		super.losers.add(new Integer(12));
	}
	
}
