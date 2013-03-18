package com.chuchro.ucbx.javax4362.craps;
/**
 * Don't Pass bet for the come out roll of a craps game.
 * @author Doug Chuchro (doug@chuchro.net)
 * @see com.chuchro.ucbx.javax4362.craps.LineBet														 */
public class DontPassBet extends LineBet {
	/** The common name of the bet.	*/
	public static String BET_NAME = "Don't Pass bet";
	/** Used to track if the "push on 12" rule is in effect (only on come out roll)
	 * @see #setPoint(int)	 */
	public int pushRoll;
	/** Roll in which bet is "pushed"
	 * @see #setPoint(int)	 */
	public static int DONT_PASS_PUSH_ROLL = 12;
	
	/**
	 * Default constructor
	 * @param maxAmt	The maximum amount the bet can be, usually limited by the player's chip count.	 */
	DontPassBet(int maxAmt)	{
		super(BET_NAME, maxAmt);
		super.winners.addAll(COME_OUT_LINE_BET_LOSERS.subList(0, COME_OUT_LINE_BET_LOSERS.size()-1));
		super.losers.addAll(COME_OUT_LINE_BET_WINNERS);
		this.pushRoll = DONT_PASS_PUSH_ROLL;	
	}

	/**
	 * Another constructor, called by extending classes that want to set their own bet name.
	 * @param maxAmt	The maximum amount the bet can be, usually limited by the player's chip count.
	 * @param betName	Common name of the bet.															 */
	DontPassBet(String betName, int maxAmt)	{
		super(betName, maxAmt);
		super.winners.addAll(COME_OUT_LINE_BET_LOSERS.subList(0, COME_OUT_LINE_BET_LOSERS.size()-1));
		super.losers.addAll(COME_OUT_LINE_BET_WINNERS);
		this.pushRoll = DONT_PASS_PUSH_ROLL;	
	}

	/**
	 * Resets the winning and losing rolls for the "Don't" bets: DontPassBet and DontComeBet. 
	 * @param point	The point set be the initial (come out) roll.										 */
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
	 * Overrides Bet.checkBet() because of a corner-case in the come out roll.
	 * If a 12 is rolled coming out on Don't Pass bet, the bet is considered "pushed", 
	 * neither won nor lost, and the player gets only his/her original bet amount back					
	 * @param dice	The roll of the dice to check the bet against										
	 * @return	If the bet was won, the amount of the bet's winnings.*/
	public int checkBet(Dice dice)	{
		int result = super.checkBet(dice);
		if (dice.getRollSum() == this.pushRoll)	{
			System.out.println("Your " + betName + " of $" + amount + " pushed, " +
								"the oringal bet amount will be added to your chip count");
			betStatus = BetStatus.BET_PUSHED;
			result = amount;
		}
		return result;
	}

	/**
	 * After the point is set, optionally attach an Odds bet.
	 * @param point	The point set for this bet.
	 * @param chipCount	The player's current chip count, used to determine the maximum value of the bet.
	 * @return	The amount of the Odds bet place, to be deducted from the chip count.					*/
	public int setOddsBet(int point, int chipCount)	{
		this.oddsBet = new DontOddsBet(this, point, chipCount);
		return this.oddsBet.amount;
	}
}