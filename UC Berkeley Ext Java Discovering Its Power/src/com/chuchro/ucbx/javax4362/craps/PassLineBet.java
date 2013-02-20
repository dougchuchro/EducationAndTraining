package com.chuchro.ucbx.javax4362.craps;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;

public class PassLineBet extends Bet {
	public static String betName = "Pass Line bet";
	public static int minAmount = 5;				// minimum $5 bet
	public static double payoutRatio = 1/1;			// payout for passline bet is 1:1
	public static int[] losers = { 7 };				// only loser for passline bet is to crap out
	public static int amountMultiple = 1;			// pass line bet can be any amount greater than minimum
	
//	@SuppressWarnings("static-access")
	PassLineBet(int maxAmt)	{
		super(betName, minAmount, maxAmt, amountMultiple);
		super.payoutRatio		= PassLineBet.payoutRatio;
		super.losers			= PassLineBet.losers;
	}
	
	public void setPoint(int point)	{
		super.winners = new int[] { point };
	}
	
}
