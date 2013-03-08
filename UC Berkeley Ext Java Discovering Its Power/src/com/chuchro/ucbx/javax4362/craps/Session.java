package com.chuchro.ucbx.javax4362.craps;

import java.io.*;
import java.util.*;

public class Session {
	public Game game;
	public int chipCount;
	public List<Bet> bets;
	/**	Initial Come or DontCome bet that hasn't had it's point set yet	 */
	private LineBet initComeBet = null;
	public boolean cashedOut;

	Session()	{
		cashedOut = false;
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

	public void play()	{
		while (!cashedOut)	{
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
			chipCount = chipCount + checkBets(game.dice);
			printChipCount();
			
			// If the come out roll sets the POINT and there was a pre-Point bet made, 
			// then reset the winners & losers of the pre-Point bet and prompt for odds on the Line Bet
			if (game.gameStatus == Game.GameStatus.POINT_SET && ppb != null)	{
				// Note that even though the setPoint() method alters the ppb object, we don't need to
				// replace it with the current object in the bets ArrayList because it is, in fact, already
				// pointing to the very same object in memory.  So we can use the ppb handle to alter the item
				// in the bets ArrayList without any manipulation to the ArrayList itself.
				ppb.setPoint(game.point);
				// Now that the point is set, ask the player if they want to place an Odds bet attached to
				// their Line bet and subtract that bet amount from the chip count.
				chipCount = chipCount - ppb.promptOddsBet(game.point, chipCount);
				printChipCount();
			}
			
			// Keep rolling until the GameStatus is WON or LOST
			while (game.gameStatus == Game.GameStatus.POINT_SET)	{
				game.printPoint();
				promptSubsequentBets();
				game.pointRoll();
				game.dice.printDice();
				chipCount = chipCount + checkBets(game.dice);
				printChipCount();
				if (initComeBet != null && initComeBet.betStatus == Bet.BetStatus.BET_ON)	{
					initComeBet.setPoint(game.dice.rollSum);
					chipCount = chipCount - initComeBet.promptOddsBet(game.dice.rollSum, chipCount);
					printChipCount();
				}
				initComeBet = null;
				printBetSummary();
			}
			
			printChipCount();
			System.out.println("If you'd like to quit playing and cash out, enter \"q\", else just press enter to continue playing.");
			String resp = getUserInput();
			if (resp.matches("q")) {cashedOut = true;}
		}
		System.out.println("Thanks for playing. You chip balance is: $" + chipCount);
	}
	
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
	
	private void promptSubsequentBets()	{
		boolean doneBetting = false;
		Bet bet = null;
		while (!doneBetting)	{
			System.out.println("Would you like to place any additional bets?.  \n" +
					"Enter \"c\" for Come Line bet or \"d\" for Don't Come bet. \n" +
					"Also, you can enter \"p\" for a Place bet, \"r\" for a Proposition bet, or \"h\" for a Hardway bet. \n" +
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
				break;
			case ("r"):
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

	private int checkBets(Dice dice)	{
		int result = 0;
		for (Bet b : bets)	{
			if (b.betStatus == Bet.BetStatus.BET_ON) {
				result = result + b.checkBet(dice);
			}
		}
		return result;
	}
	
	public void printChipCount()	{
		System.out.println("You Chip Count is: $" + chipCount);		
	}
	
	public void printBetSummary()	{
		System.out.println("Bet Name	Amount		Winners");		
		for (Bet b : bets) {
			if (b.betStatus == Bet.BetStatus.BET_ON)	{
			System.out.println(b.betName + "\t" + b.amount + "\t\t" + b.winners);					
			}
		}
	}
	
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