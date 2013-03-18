package com.chuchro.ucbx.javax4362.craps;
/**
 * A Come Line bet is essentially the same as a Pass Line bet, except for the fact that the Come Line bet is made after
 * the point is rolled.  The bet is either won or lose, or the point is established, just as a Pass Line bet.  This
 * class extends PassLineBet to take advantage of their similarities.
 * @author Doug Chuchro (doug@chuchro.net)
 * @see com.chuchro.ucbx.javax4362.craps.PassLineBet
 */
public class ComeLineBet extends PassLineBet {
	/** The common name of the bet.	*/
	public static String BET_NAME = "Come Line bet";

	/**
	 * Default constructor calls super with this.BET_NAME and the maximum bet amount.
	 * @param maxAmt	The maximum amount the bet can be, usually limited by the player's chip count.		*/
	public ComeLineBet(int maxAmt) {
		super(maxAmt, BET_NAME);
	}
	
	/**
	 * The come out roll of the game has been established, so this sets the winning and losing rolls
	 * for this bet accordingly.
	 * @param	point	The point, which is the winning roll for this bet.									*/
	public void setPoint(int point)	{
		if (POINTS.contains(point))	{
			super.setPoint(point);
			System.out.println("Your " + betName + " point has been set to " + point);
		}
	}
}