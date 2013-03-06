package com.chuchro.ucbx.javax4362.craps;

import java.io.*;
import java.util.*;

public class Session {
	public Game game;
	public int chipCount;
	public List<Bet> bets;
	public boolean cashedOut;
	public BufferedReader stdin;

	Session()	{
		cashedOut = false;
		chipCount = 0;
		bets = new ArrayList<Bet>();
		System.out.println("Welcome to Craps");
		System.out.println("Please enter the amout of chips you would like to buy");
		stdin = new BufferedReader(new InputStreamReader(System.in), 1);
		while (chipCount == 0)	{
			try {
				chipCount = Integer.parseInt(stdin.readLine());
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
			MultiRollBet ppb = getPrePointBet();
			if (ppb != null)	{
				bets.add(ppb);
				chipCount = chipCount - ppb.amount;
			}
			
			// Start the game with the "Come Out" roll
			game.comeOutRoll();
			chipCount = chipCount + checkBets(game.dice);
			
			// If the come out roll sets the POINT and there was a pre-Point bet made, 
			// then reset the winners & losers of the pre-Point bet and prompt for odds on the Line Bet
			if (game.gameStatus == Game.GameStatus.POINT_SET && ppb != null)	{
				// Note that even though the setPoint() method alters the ppb object, we don't need to
				// replace it with the current object in the bets ArrayList because it is, in fact, already
				// pointing to the very same object in memory.  So we can use the ppb handle to alter the item
				// in the bets ArrayList without any manipulation to the ArrayList itself.
				ppb.setPoint(game.point);
				OddsBet ob = getOddsBet(ppb, game.point);
				// Note that we don't add the OddsBet to the List of Bets (this.bets) as we did the Line bet and 
				// will for other bets. Rather, we attach it to the Line bet and only take action if that Line bet
				// is won or lost.
				ppb.setOddsBet(ob);
				chipCount = chipCount - ob.amount;
			}
			
			// Keep rolling until the GameStatus is WON or LOST
			while (game.gameStatus == Game.GameStatus.POINT_SET)	{
				game.printPoint();
				game.pointRoll();
				game.dice.printDice();
				chipCount = chipCount + checkBets(game.dice);
			}
			
			printChipCount();
			System.out.println("If you'd like to quit playing and cash out, enter \"q\", else just press enter to continue playing.");
			String resp = "";
			try {
				resp = stdin.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (resp.matches("q")) {cashedOut = true;}
		}
		System.out.println("Thanks for playing. You chip balance is: $" + chipCount);
	}
	
	private MultiRollBet getPrePointBet()	{
		MultiRollBet bet = null;
		System.out.println("New game, place you bet.  \n" +
				"Enter \"p\" for Pass Line bet or \"d\" for Don't Pass bet, " +
				"or just hit return for no initial bet");
		String resp = "";
		try {
			resp = stdin.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		switch (resp)	{
		case ("p"):
			bet = new PassLineBet(chipCount);
			break;
		case ("d"):
			bet = new DontPassBet(chipCount);
			break;
		}
		return bet;
	}
	
	private OddsBet getOddsBet(MultiRollBet b, int point)	{
		OddsBet ob = null;
		System.out.println("Would you like to place odds on your " + b.betName + "? (y/n)");
		String resp = "";
		try {
			resp = stdin.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (resp.matches("y")) {
			ob = new OddsBet(b, point);
		}
		return ob;
	}

	private int checkBets(Dice dice)	{
		int result = 0;
		for (Bet b : bets)	{
			if (b.betStatus == Bet.BetStatus.BET_ON) {
				result = b.checkBet(dice);
			}
		}
		return result;
	}
	
	public void printChipCount()	{
		System.out.println("You Chip Count is: $" + chipCount);		
	}
}