package com.chuchro.ucbx.javax4362.craps;

import java.io.BufferedReader;
//import java.io.IOException;
import java.io.InputStreamReader;

public class Session {
	public Game game;
	public int chipCount;
	public Bet[] bets;
	public boolean cashedOut;
	public BufferedReader stdin;

	Session()	{
		cashedOut = false;
		chipCount = 0;
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
			game.comeOutRoll();
			game.printPoint();
			while (game.gameStatus == Game.GameStatus.POINT_SET)	{
				game.pointRoll();
				game.printDice();
			}
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
	
	public void printChipCount()	{
		System.out.println("You Chip Count is: $" + chipCount);		
	}
}
