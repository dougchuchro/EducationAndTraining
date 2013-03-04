package com.chuchro.ucbx.javax4362.craps;

public class PassLineBet extends Bet {
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
		// Set the winning roll of a Pass Line bet: 7
		super.winners.add(new Integer(7));
		// Set the losing rolls of a Pass Line bet: 2, 3, and 12
		super.losers.add(new Integer(2));
		super.losers.add(new Integer(3));
		super.losers.add(new Integer(12));
	}
	
	public void setPoint(int point)	{
		// Clear out the initial winner (7) and add the point as the only winning roll
		super.winners.clear();
		super.winners.add(new Integer(point));
		// Clear out the initial losing rolls (2,3,12) and add 7 as the only losing roll
		super.losers.clear();
		super.losers.add(new Integer(7));
		
	}
	
}
