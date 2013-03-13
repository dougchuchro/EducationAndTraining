package com.chuchro.ucbx.javax4362.craps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

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
public abstract class LineBet extends Bet {
	/** The Odds Bet that is optionally attached to a Line Bet of any type	*/
	public OddsBet 			 oddsBet;
	public static List<Integer> COME_OUT_LINE_BET_WINNERS	= new ArrayList<Integer>(Arrays.asList(7,11));
	public static List<Integer> COME_OUT_LINE_BET_LOSERS	= new ArrayList<Integer>(Arrays.asList(2,3,12));
	public static List<Integer> POINTS  					= new ArrayList<Integer>(Arrays.asList(4,5,6,8,9,10));
	

	/**
	 * Default constructor, calls super
	 * @param betName
	 * @param minAmt
	 * @param maxAmt
	 * @param amountMultiple
	 */
	LineBet(String betName, int minAmt, int maxAmt, int amountMultiple) {
		super(betName, minAmt, maxAmt, amountMultiple);
	}

	/**
	 * After the establishing roll is made, this is called to (re)set the winning and losing rolls
	 * based on the point. This is an abstract method because Pass/Come Line bets must have a different 
	 * implementation than DontPass/DontCome bets.
	 * @param point
	 */
	public abstract void setPoint(int point);
	
	/**
	 * Prompt the user if they want to place an Odds bet to attach to this Line Bet.
	 * @param	point		The point of the current game
	 * @param	chipCount	The players chip count, which will be used with the maximum theoretical
	 * 			Odds bet amount to determine the maximum amount of the current Odds bet.
	 * @return	Returns the amount of the odds bet placed to be deducted from the chip count.
	 * 			If no odds bet placed, zero (0) is returned.
	 */
	public int promptOddsBet(int point, int chipCount)	{
		int betAmount = 0;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1);
		System.out.println("Would you like to place odds on your " + betName + "? (y/n)");
		String resp = "";
		try {
			resp = stdin.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (resp.matches("y")) {
			betAmount = setOddsBet(point, chipCount);
		}
		return betAmount;
	}
	
	/**
	 * Abstract method requires implementing classes to define thier own OddsBet, specificly
	 * the PassLine bet will set a LineOdds bet, and DontPass bet will set DontOdds bet. Similarly with ComeLine/DontCome bets.
	 * @param point		The point set in the current game.
	 * @param chipCount	The player's current chip count.
	 * @return	The amount of the Odds bet, to be deducted from the player's chip count.
	 */
	public abstract int setOddsBet(int point, int chipCount);
	
	/**
	 * Check if the bet won or lost based on the roll of the Dice that was passed, then check the associated Odds bet
	 * @param dice		The dice roll result to be used to determine the bet outcome.
	 * @return result	If the bet was a winner, the amount won, plus the amount of the associated Odds bet, if there is one.
	 */
	public int checkBet(Dice dice)	{
		// First, run the standard check: Bet.checkBet()
		int result = super.checkBet(dice);
		// if the Line bet was won, then also pay out the Odds bet (if there is one)
		if ((betStatus == Bet.BetStatus.BET_WON || betStatus == Bet.BetStatus.BET_LOST) && this.oddsBet != null)	{
			result = result + this.oddsBet.checkBet(dice);
		}
		return result;
	}
	
	/**
	 * 
	 */
	public String getOddsBetString()	{
		String msg = "";
		if (oddsBet != null)	{
//			msg = "\tplus Odds Bet of $" + oddsBet.amount;
			msg = "\tplus Odds Bet of $" + oddsBet.amount + ", winners:" + oddsBet.winners + ", losers:" + oddsBet.losers;
		}
		return msg;
	}

}
