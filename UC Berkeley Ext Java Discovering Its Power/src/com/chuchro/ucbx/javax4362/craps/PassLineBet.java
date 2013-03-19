package com.chuchro.ucbx.javax4362.craps;
/**
 * Pass Line bet for the come out roll of a craps game.
 * @author Doug Chuchro (doug@chuchro.net)
 * @see com.chuchro.ucbx.javax4362.craps.LineBet														*/
public class PassLineBet extends LineBet {
	/** The common name of the bet.	*/
	public static String BET_NAME = "Pass Line bet";
	
	/**
	 * Default constructor, calls super constructor, then sets winners and losers specific to this bet.
	 * @param maxAmt	The maximum amount the bet can be, usually limited by the player's chip count.	 */
	PassLineBet(int maxAmt)	{
		super(BET_NAME, maxAmt);
		super.winners.addAll(COME_OUT_LINE_BET_WINNERS);
		super.losers.addAll(COME_OUT_LINE_BET_LOSERS);
	}

	/**
	 * Another constructor, called by extending classes that want to set their own bet name.
	 * @param maxAmt	The maximum amount the bet can be, usually limited by the player's chip count.
	 * @param betName	Common name of the bet.															 */
	PassLineBet(int maxAmt, String betName)	{
		super(betName, maxAmt);
		super.winners.addAll(COME_OUT_LINE_BET_WINNERS);
		super.losers.addAll(COME_OUT_LINE_BET_LOSERS);
	}

	/**
	 * The come out roll of the game has been established, so this sets the winning and losing rolls
	 * for this bet accordingly.
	 * @param	point	The point, which is the winning roll for this bet.								*/
	public void setPoint(int point)	{
		super.winners.clear();
		super.winners.add(new Integer(point));
		super.losers.clear();
		super.losers.add(new Integer(7));
	}

	/**
	 * After the point is set, optionally attach an Odds bet.
	 * @return	The amount of the Odds bet, zero if no odds bet placed									*/
	public int setOddsBet(int point, int chipCount)	{
		this.oddsBet = new LineOddsBet(this, point, chipCount);
		return this.oddsBet.amount;
	}
}