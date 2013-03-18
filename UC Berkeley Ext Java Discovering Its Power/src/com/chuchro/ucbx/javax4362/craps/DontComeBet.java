package com.chuchro.ucbx.javax4362.craps;
/**
 * Don't Come bet for the subsequent rolls of a craps game.
 * @author Doug Chuchro (doug@chuchro.net)
 * @see com.chuchro.ucbx.javax4362.craps.DontPassBet														 */
public class DontComeBet extends DontPassBet {
	/** The common name of the bet.	*/
	public static String BET_NAME = "Don't Come bet";

	/**
	 * Default constructor calls super with this.BET_NAME and the maximum bet amount.
	 * @param maxAmt	The maximum amount the bet can be, usually limited by the player's chip count.		*/
	public DontComeBet(int maxAmt) {
		super(BET_NAME, maxAmt);
	}

	/**
	 * The come out roll of the game has been established; this sets the winning and losing rolls
	 * for this bet accordingly.
	 * @param	point	The point, which is the winning roll for this bet.									 */
	public void setPoint(int point)	{
		if (POINTS.contains(point))	{
			super.setPoint(point);
			System.out.println("Your " + betName + " point has been set to " + point);
		}
	}
}