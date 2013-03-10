package com.chuchro.ucbx.javax4362.craps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Doug Chuchro (doug@chuchro.net)
 *
 */
public class PropositionBet extends Bet {
	/**	Name of the bet type	*/
	public static String BET_NAME = "Proposition Bet";
	/**	Minimum bet size for a Propostion bet is $1	*/
	public static int MIN_AMOUNT = 1;
	/**	Maximum bet size for a Propostion bet is unlimited, but we will limit to the maximum size of an Integer in Java	*/
	public static int MAX_AMOUNT = Integer.MAX_VALUE;
	/**	Bet amount can be any integer	*/
	public static int AMT_MULTIPLE = 1;
	/**	Bet amount can be any integer	*/
	public PropositionBetType propBetType;
	/**	List of losing rolls.	*/
	public static List<Integer> PROP_BET_LOSERS = Arrays.asList(2,3,4,5,6,7,8,9,10,11,12);

	/**	Static lookup table of Don't bet points and their corresponding Don't Odds bet amount multiple.
	 * 	This table (in the form of a Map) is populated by the static method createPointAmountMultipleMap().
	 * 	@see	#createPointAmountMultipleMap()*/
	private static final Map<PropositionBetType, PropositionBetInfo> PROP_BET_TYPE_MAP = createPropBetTypeMap(); 
	private static Map<PropositionBetType, PropositionBetInfo> createPropBetTypeMap()	{
		Map<PropositionBetType, PropositionBetInfo> tmpMap = new HashMap<PropositionBetType, PropositionBetInfo>();
		tmpMap.put(PropositionBetType.FIELD,		new PropositionBetInfo("Field",		Arrays.asList(3,4,9,10,11),	new Double( 1.0)));
		tmpMap.put(PropositionBetType.ANY_CRAPS,	new PropositionBetInfo("Any Craps",	Arrays.asList(2,3,12),		new Double( 8.0)));
		tmpMap.put(PropositionBetType.ACES,			new PropositionBetInfo("Aces",		Arrays.asList(2),			new Double(31.0)));
		tmpMap.put(PropositionBetType.ACE_DEUCE, 	new PropositionBetInfo("Ace Deuce",	Arrays.asList(3),			new Double(16.0)));
		tmpMap.put(PropositionBetType.ANY_SEVEN,	new PropositionBetInfo("Any Seven",	Arrays.asList(7),			new Double( 5.0)));
		tmpMap.put(PropositionBetType.YO,			new PropositionBetInfo("Yo",		Arrays.asList(11),			new Double(16.0)));
		tmpMap.put(PropositionBetType.TWELVE,		new PropositionBetInfo("Twelve",	Arrays.asList(12),			new Double(31.0)));
		return Collections.unmodifiableMap(tmpMap);
	}
	
	/**
	 * Constructor for this class
	 */
	public PropositionBet(PropositionBetType pbt) {
		super(BET_NAME, MIN_AMOUNT, MAX_AMOUNT, AMT_MULTIPLE);
		this.propBetType = pbt;
		PropositionBetInfo pbi = PROP_BET_TYPE_MAP.get(pbt);
		this.betName = betName + ": " + pbi.propBetCommonName;
		this.winners.addAll(pbi.winners);
		this.losers.addAll(PROP_BET_LOSERS);
		this.payoutRatio = pbi.payoutRatio;
	}

	public static PropositionBetType promptPropositionBetType()	{
		PropositionBetType propBetTypeResp = null;
		System.out.println("What kind of Proposition Bet would you like to make?\n" +
				"Field (enter \"f\"), Any Craps (enter \"c\"), Aces (enter \"2\"), Ace Deuce(enter \"3\") " +
				"Any Seven (enter \"7\"), Yo (enter \"11\"), Twelve (enter \"12\"), or enter \"n\" if you don't want to place the bet.");
		String resp = null;
		while (resp == null)	{
			resp = Session.getUserInput();
			switch (resp)	{
			case "f":
				propBetTypeResp = PropositionBetType.FIELD;
				break;
			case "c":
				propBetTypeResp = PropositionBetType.ANY_CRAPS;
				break;
			case "2":
				propBetTypeResp = PropositionBetType.ACES;
				break;
			case "3":
				propBetTypeResp = PropositionBetType.ACE_DEUCE;
				break;
			case "7":
				propBetTypeResp = PropositionBetType.ANY_SEVEN;
				break;
			case "11":
				propBetTypeResp = PropositionBetType.YO;
				break;
			case "12":
				propBetTypeResp = PropositionBetType.TWELVE;
				break;
			case "n":
				System.out.println("No bet placed.");
				break;
			default:
				System.out.println("Invalid entry, please try again.");
			}
		}
		return propBetTypeResp;
	}
	
	/**
	 * Inner class to hold Proposition bet information for all of the different Proposition bet types.
	 */
	private static class PropositionBetInfo	{
		String propBetCommonName;
		ArrayList<Integer> winners = new ArrayList<Integer>();
		Double payoutRatio;
		
		PropositionBetInfo(String propBetCommonName, List<Integer> winers,	Double payoutRatio)	{
			this.propBetCommonName = propBetCommonName;
			this.winners.addAll(winers);
			this.payoutRatio = payoutRatio;
		}
	}
	
	/**
	 * All valid Proposition Bet Types
	 */
	public enum PropositionBetType	{
		/** 3,4,9,10,or 11	*/	FIELD,
		/** 2,3 or 12		*/	ANY_CRAPS,
		/** 7				*/	ANY_SEVEN,
		/** 11				*/	YO,
		/** 3				*/	ACE_DEUCE,
		/** 2				*/	ACES,
		/** 12				*/	TWELVE,
	}
}