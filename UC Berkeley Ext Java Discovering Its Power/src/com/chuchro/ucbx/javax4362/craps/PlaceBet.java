package com.chuchro.ucbx.javax4362.craps;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Doug Chuchro (doug@chuchro.net)
 *
 */
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
	
	/**
	 * 
	 * @param PlaceBetType
	 */
	public PlaceBet(PlaceBetType pbt) {
		super(BET_NAME, PLACE_BET_TYPE_MAP.get(pbt).minAmount, MAX_AMOUNT, PLACE_BET_TYPE_MAP.get(pbt).amountMultiple);
		this.placeBetType = pbt;
		PlaceBetInfo pbi = PLACE_BET_TYPE_MAP.get(pbt);
		this.betName = betName + ": " + pbi.placeBetCommonName;
		this.winners.add(pbi.winner);
		this.losers.add(PLACE_BET_LOSER);
		this.payoutRatio = pbi.payoutRatio;		
	}
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
	/**
	 * Inner class to hold Place bet information for all of the different Place bet types.
	 */
	private static class PlaceBetInfo	{
		String placeBetCommonName;
		int winner;
		int minAmount;
		int amountMultiple;
		Double payoutRatio;
		
		PlaceBetInfo(String propBetCommonName, int winner ,int minAmount ,int amountMultiple ,Double payoutRatio)	{
			this.placeBetCommonName	= propBetCommonName;
			this.winner				= winner;
			this.minAmount			= minAmount;
			this.amountMultiple		= amountMultiple;
			this.payoutRatio		= payoutRatio;
		}
	}
	
	/**
	 * All valid Place bet types
	 */
	public enum PlaceBetType	{
		/** 4	*/	FOUR,
		/** 5	*/	FIVE,
		/** 6	*/	SIX,
		/** 8	*/	EIGHT,
		/** 9	*/	NINE,
		/** 10	*/	TEN,
	}
}
