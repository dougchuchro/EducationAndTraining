package com.chuchro.ucbx.javax4362.craps;
import java.util.*;
/**	A Place bet is on one of the six field numbers and wins if that number is rolled before a seven (7) is rolled.
 * The bet pays odds based on the point selected.
 * The payout schedule is as follows:
 *	<table border="1" cellpadding="3" cellspacing="0"><tbody>
 *	<tr><th>Point</th>	<th>Payout</th>	<th>Amount Multiple</th>	<th>Example</th></tr>
 *	<tr><td>4, 10</td>	<td>9:5</td>	<td>5</td>					<td>$5 bet pays $9</td></tr>
 *	<tr><td>5, 9</td>	<td>7:5</td>	<td>5</td>					<td>$5 bet pays $7</td></tr>
 *	<tr><td>6, 8</td>	<td>7:6</td>	<td>6</td>					<td>$6 bet pays $7</td></tr></tbody></table>
 * @author Doug Chuchro																				*/
public class PlaceBet extends Bet {
	/**	Name of the bet type	*/
	public static String BET_NAME = "Place Bet";
	/**	Minimum bet size for a Place bet is $1	*/
	public static int MIN_AMOUNT = 1;
	/**	Maximum bet size for a Place bet is unlimited, but we will limit to the maximum size of an Integer in Java	*/
	public static int MAX_AMOUNT = Integer.MAX_VALUE;
	/**	Bet amount can be any integer	*/
	public static int AMT_MULTIPLE = 1;
	/**	Place bet lose when a seven (7) is rolled before the place number	*/
	public static int PLACE_BET_LOSER = 7;
	/**	Bet amount can be any integer	*/
	public PlaceBetType placeBetType;

	/**	Static lookup table of Proposition bets and their corresponding attribute values.
	 * 	This table (in the form of a Map) is populated by the static method createPlaceBetTypeMap().
	 * 	@see	#createPlaceBetTypeMap()*/
	private static final Map<PlaceBetType, PlaceBetInfo> PLACE_BET_TYPE_MAP = createPlaceBetTypeMap(); 
	/** Used to populate the PLACE_BET_TYPE_MAP with information on all bets of that type.
	 * @return	The static Map table with bet information.													 */
	private static Map<PlaceBetType, PlaceBetInfo> createPlaceBetTypeMap()	{
		Map<PlaceBetType, PlaceBetInfo> tmpMap = new HashMap<PlaceBetType, PlaceBetInfo>();
		tmpMap.put(PlaceBetType.FOUR,	new PlaceBetInfo("Four",	4,	5,	5,	new Double(9.0/5.0)));
		tmpMap.put(PlaceBetType.FIVE,	new PlaceBetInfo("Five",	5,	5,	5,	new Double(7.0/5.0)));
		tmpMap.put(PlaceBetType.SIX,	new PlaceBetInfo("Six",		6,	6,	6,	new Double(7.0/6.0)));
		tmpMap.put(PlaceBetType.EIGHT, 	new PlaceBetInfo("Eight",	8,	6,	6,	new Double(7.0/6.0)));
		tmpMap.put(PlaceBetType.NINE,	new PlaceBetInfo("Nine",	9,	5,	5,	new Double(7.0/5.0)));
		tmpMap.put(PlaceBetType.TEN,	new PlaceBetInfo("Ten",		10,	5,	5,	new Double(9.0/5.0)));
		return Collections.unmodifiableMap(tmpMap);
	}
	
	/**	Constructor creates a Place bet based on player's input: Place type (number) and bet amount.
	 * @param pbt	PlaceBetType is the type of place bet the player has selected.							 */
	public PlaceBet(PlaceBetType pbt) {
		super(BET_NAME, PLACE_BET_TYPE_MAP.get(pbt).minAmount, MAX_AMOUNT, PLACE_BET_TYPE_MAP.get(pbt).amountMultiple);
		this.placeBetType = pbt;
		PlaceBetInfo pbi = PLACE_BET_TYPE_MAP.get(pbt);
		this.betName = betName + ": " + pbi.placeBetCommonName;
		this.winners.add(pbi.winner);
		this.losers.add(PLACE_BET_LOSER);
		this.payoutRatio = pbi.payoutRatio;		
	}
	/** Prompt the player to specify which number (point) they want their Place bet on.
	 * @return Place bet number.																			 */
	public static PlaceBetType promptPlaceBetType()	{
		PlaceBetType pbt = null;
		System.out.println("What number would you like to put your Place on?\n" +
				"Four (enter \"4\"), Five (enter \"5\"), Six (enter \"6\"), Eight (enter \"8\") " +
				"Nine (enter \"9\"), Ten (enter \"10\"), or enter \"n\" if you don't want to place the bet.");
		String resp = null;
		while (resp == null)	{
			resp = Session.getUserInput();
			switch (resp)	{
			case "4":
				pbt = PlaceBetType.FOUR;
				break;
			case "5":
				pbt = PlaceBetType.FIVE;
				break;
			case "6":
				pbt = PlaceBetType.SIX;
				break;
			case "8":
				pbt = PlaceBetType.EIGHT;
				break;
			case "9":
				pbt = PlaceBetType.NINE;
				break;
			case "10":
				pbt = PlaceBetType.TEN;
				break;
			case "n":
				System.out.println("No bet placed.");
				break;
			default:
				System.out.println("Invalid entry, please try again.");
				resp = null;
			}
		}
		return pbt;
	}
	/**	Inner class to hold Place bet information for all of the different Place bet types.	*/
	private static class PlaceBetInfo	{
		String placeBetCommonName;
		int winner;
		int minAmount;
		int amountMultiple;
		Double payoutRatio;
		/**	Constructor for inner class																	*/
		PlaceBetInfo(String propBetCommonName, int winner ,int minAmount ,int amountMultiple ,Double payoutRatio)	{
			this.placeBetCommonName	= propBetCommonName;
			this.winner				= winner;
			this.minAmount			= minAmount;
			this.amountMultiple		= amountMultiple;
			this.payoutRatio		= payoutRatio;
		}
	}
	
	/** All valid Place bet types																		*/
	public enum PlaceBetType	{
		/** 4	*/	FOUR,
		/** 5	*/	FIVE,
		/** 6	*/	SIX,
		/** 8	*/	EIGHT,
		/** 9	*/	NINE,
		/** 10	*/	TEN,
	}
}
