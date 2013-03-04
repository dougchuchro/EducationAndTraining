package com.chuchro.ucbx.javax4362.craps;

public class DontPassBet extends Bet {
	public static String betName = "Don't Pass bet";
	public static int 	 minAmount = 5;				// minimum $5 bet
	public static double payoutRatio = 1/1;			// payout for passline bet is 1:1
	public static int 	 amountMultiple = 1;		// pass line bet can be any amount greater than minimum
	public Bet 			 dontPassOddsBet;			// This bet can only be made after the point is set and
													// has a payout ratio exactly that of the probability of
													// hitting the point.  For example, if the point is 6
													// the probability of hitting the point before crapping
													// out if 5:6, so a $5 bet wins $6
	
	DontPassBet(int maxAmt)	{
		super(betName, minAmount, maxAmt, amountMultiple);
		super.payoutRatio		= PassLineBet.payoutRatio;
		// Set the winning roll of a Don't Pass bet: 2 and 3
		super.winners.add(new Integer(2));
		super.winners.add(new Integer(3));
		// Set the losing rolls of a Pass Line bet: 2, 3, and 12
		super.losers.add(new Integer(7));
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
