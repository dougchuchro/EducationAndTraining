package com.chuchro.ucbx.javax4362.craps;

import java.io.*;
import java.util.*;

/**
 * Represents a players session at this virtual craps table.  The user will be prompted to buy chips, place bets,
 * roll the dice, etc.  The player is able to play an unlimited number of games within the session, until the chose to 
 * quit or thier chips run out.  Player may place any number of bets, either for (Pass or Come), against (Don't Pass or
 * Don't Come) the game, or bets that are not dependent on the game being won or lost (Proposition bet).  Some bets will span
 * multiple games, which requires the program design to keep bets and games independent of one another.
 * @author Doug Chuchro (doug@chuchro.net)
 */
public class Session {
	/**	Game that the player will play one or more times	 */
	public Game game;
	/**	The amount of chips (in $) that the current player has.	*/
	private int chipCount;
	/**	A collection of all of the bets the player has made.	 */
	private List<Bet> bets;
	/**	Initial Come or DontCome bet that hasn't had it's point set yet	 */
	private LineBet initComeBet = null;

	/** 
	 * Constructor initializes class fields and prompts user for amount of chips to buy.
	 */
	Session()	{
		chipCount = 0;
		bets = new ArrayList<Bet>();
		System.out.println("Welcome to Craps");
		System.out.println("Please enter the amout of chips you would like to buy");
		while (chipCount == 0)	{
			try {
				chipCount = Integer.parseInt(getUserInput());
			} catch (Exception e) {
				System.out.println("Please enter the amount as an integer");
				chipCount = 0;
			}			
		}
		printChipCount();
	}

	/**
	 * Primary method of the Session class, cycles through a series of games, takes bets, and manages the
	 * chip count.
	 */
	public void play()	{
		boolean cashedOut = false;
		while (!cashedOut  && chipCount > 0)	{
			game = new Game();
			// Prompt for a bet prior to the come out roll (Pass Line or Don't Pass)
			LineBet ppb = promptPrePointBet();
			if (ppb != null)	{
				bets.add(ppb);
				chipCount = chipCount - ppb.amount;
				printChipCount();
			}
			// Start the game with the "Come Out" roll
			game.comeOutRoll();
			chipCount = chipCount + checkBets(game.getDice());
			printChipCount();
			// If the come out roll sets the POINT and there was a pre-Point bet made, 
			// then reset the winners & losers of the pre-Point bet and prompt for odds on the Line Bet
			if (game.gameStatus == Game.GameStatus.POINT_SET && ppb != null)	{
				// Note that even though the setPoint() method alters the ppb object, we don't need to
				// replace it with the current object in the bets ArrayList because it is, in fact, already
				// pointing to the very same object in memory.  
				ppb.setPoint(game.getPoint());
				// Now that the point is set, ask the player if they want to place an Odds bet attached to
				// their Line bet and subtract that bet amount from the chip count.
				chipCount = chipCount - ppb.promptOddsBet(game.getPoint(), chipCount);
				printChipCount();
			}
			// Keep rolling until the GameStatus is WON or LOST
			while (game.gameStatus == Game.GameStatus.POINT_SET)	{
				game.printPoint();
				promptSubsequentBets();
				printBetSummary();
				game.pointRoll();
				chipCount = chipCount + checkBets(game.getDice());
				printChipCount();
				if (initComeBet != null && initComeBet.betStatus == Bet.BetStatus.BET_ON)	{
					initComeBet.setPoint(game.getDice().getRollSum());
					chipCount = chipCount - initComeBet.promptOddsBet(game.getDice().getRollSum(), chipCount);
					printChipCount();
				}
				initComeBet = null;
			}
			System.out.println("If you'd like to quit playing and cash out, enter \"q\", else just press enter to continue playing.");
			String resp = getUserInput();
			if (resp.matches("q")) {cashedOut = true;}
		}
		// Player has decided to quit so determine if s/he has open bets and return the principal amount.
		if (hasOpenBets())	{
			int retAmt = 0;
			System.out.println("Returning principal amount on your open bets.");
			for (Bet b : bets)	{
				if (b.betStatus == Bet.BetStatus.BET_ON)	{
					System.out.println("\tReturning $" + b.amount + " on your " + b.betName);
					retAmt = retAmt + b.amount;
				}
			}
			chipCount = chipCount + retAmt;
		}
		System.out.println("Thanks for playing. You chip balance is: $" + chipCount);
	}
	
	/**
	 * Prompt the player for a bet prior to the new game's come out roll.
	 * @return LineBet is the bet that the player has placed, either Pass Line or Don't Pass bet.
	 */
	private LineBet promptPrePointBet()	{
		LineBet lineBet = null;
		System.out.println("New game, place you bet.  \n" +
				"Enter \"p\" for Pass Line bet or \"d\" for Don't Pass bet, " +
				"or just hit return for no initial bet");
		String resp = getUserInput();
		switch (resp)	{
		case ("p"):
			lineBet = new PassLineBet(chipCount);
			break;
		case ("d"):
			lineBet = new DontPassBet(chipCount);
			break;
		}
		return lineBet;
	}
	
	/**
	 * Prompt the player for additional bets after the point of the game has been established, which could be one
	 * or more of the following bets: Come Line, Don't Come, Place, Proposition.
	 */
	private void promptSubsequentBets()	{
		boolean doneBetting = false;
		Bet bet = null;
		while (!doneBetting)	{
			System.out.println("Would you like to place any additional bets?.  \n" +
					"Enter \"c\" for Come Line bet or \"d\" for Don't Come bet. \n" +
					"Also, you can enter \"p\" for a Place bet, or \"r\" for a Proposition bet. \n" +
					"Or just hit return for no further bets.");
			String resp = getUserInput();
			switch (resp)	{
			case ("c"):
				if (initComeBet == null)	{
					ComeLineBet clb = new ComeLineBet(chipCount);
					initComeBet = clb;
					bet = clb;
				} else {
					System.out.println("You've already made a Come Line or Don't Come bet for this roll.");
				}
				break;
			case ("d"):
				if (initComeBet == null)	{
					DontComeBet dcb = new DontComeBet(chipCount);
					initComeBet = dcb;
					bet = dcb;
				} else {
					System.out.println("You've already made a Come Line or Don't Come bet for this roll.");
				}
				break;
			case ("p"):
				PlaceBet.PlaceBetType plbt = PlaceBet.promptPlaceBetType();
				if (plbt != null)	{
					PlaceBet placeBet = new PlaceBet(plbt);
					bet = placeBet;
				}
				break;
			case ("r"):
				PropositionBet.PropositionBetType pbt = PropositionBet.promptPropositionBetType();
				if (pbt != null)	{
					PropositionBet pb = new PropositionBet(pbt);
					bet = pb;				
				}
				break;
			case ("h"):
				break;
			default:
				doneBetting = true;
				return;
			}
			if (bet != null)	{
				bets.add(bet);
				chipCount = chipCount - bet.amount;
				printChipCount();
			}
		}
	}

	/**
	 * Iterates through the List of bets, checking to see if the dice just rolled won or lost the bet.
	 * @param dice	Most current roll of the dice.
	 * @return	The winnings amount to be added to the player's chipCount.
	 */
	private int checkBets(Dice dice)	{
		int result = 0;
		for (Bet b : bets)	{
			if (b.betStatus == Bet.BetStatus.BET_ON) result = result + b.checkBet(dice);
		}
		return result;
	}
	
	/**
	 * Determine if the player has any open bets.
	 * @return TRUE if the player has open bets, FALSE if not.
	 */
	private boolean hasOpenBets()	{
		boolean result = false;
		for (Bet b : bets)	{
			if (b.betStatus == Bet.BetStatus.BET_ON) result = true;
		}
		return result;
	}
	
	/**
	 * Print the player's current chip count to the console.
	 */
	private void printChipCount()	{
		System.out.println("You Chip Count is: $" + chipCount);		
	}
	
	/**
	 * Print a summary table of the players current open bets.
	 */
	private void printBetSummary()	{
		System.out.println("BET SUMMERY \n\tBET NAME\tAMOUNT\tWINNING ROLLS\tLOSING ROLLS");		
		for (Bet b : bets) {
			if (b.betStatus == Bet.BetStatus.BET_ON)	{
			System.out.println("\t" + b.betName + "\t$" + b.amount + "\t" + b.winners + "\t\t"+ b.losers + "\t"+ b.getOddsBetString());					
			}
		}
	}
	
	/**
	 * Static method do read input from the command line within try/catch block. 
	 * Used by other classes in this package
	 * @return	User provided String captured from the command line
	 */
	public static String getUserInput()	{
		String resp = "";
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1);
		try {
			resp = stdin.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}