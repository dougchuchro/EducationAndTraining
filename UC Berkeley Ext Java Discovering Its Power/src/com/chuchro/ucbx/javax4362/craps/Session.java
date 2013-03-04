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
			Bet ppb = getPrePointBet();
			if (ppb != null)	{
				bets.add(ppb);
				chipCount = chipCount - ppb.amount;
			}
			
			// Start the game with the "Come Out" roll
			game.comeOutRoll();
			chipCount = chipCount - checkBets(game.dice);
			
			// Keep rolling until the GameStatus is WON or LOST
			while (game.gameStatus == Game.GameStatus.POINT_SET)	{
				game.printPoint();
				game.pointRoll();
				game.dice.printDice();
				chipCount = checkBets(game.dice);
			}
			
/*			if (game.gameStatus == Game.GameStatus.WON)	{
				chipCount = chipCount + ppb.amount;
				chipCount = chipCount + ppb.payoutWinner();
			}
			if (game.gameStatus == Game.GameStatus.LOST)	{
				ppb.deductLoser();
			}
*/			
			System.out.println("Game status: " + game.gameStatus);
			printChipCount();
			System.out.println("If you'd like to quit playing and cash out, enter q");
			String resp = "";
			try {
				resp = stdin.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (resp.matches("q")) {cashedOut = true;}
		}
		System.out.println("Thanks for playing. You chip balance is: $" + chipCount);
		return;
	}
	
	private Bet getPrePointBet()	{
		Bet bet = null;
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