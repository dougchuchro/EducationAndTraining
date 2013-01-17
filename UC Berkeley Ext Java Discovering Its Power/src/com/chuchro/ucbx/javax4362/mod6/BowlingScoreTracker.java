package com.chuchro.ucbx.javax4362.mod6;

import java.util.*;

public class BowlingScoreTracker {

	public static final int MIN_GAMES_PER_BOWLER = 10;
	public static final int MAX_GAMES_PER_BOWLER = 20;
	
	public static void main(String[] args) {
		// Use an ArrayList to "collect" our Bowlers
		// We will specify <Bowler> when creating our collection 
		// so there is no ambiguity what type objects are in our collection
		ArrayList<Bowler> bowlers = new ArrayList<Bowler>();

		// Let's create some bowlers and add them to the collection
		bowlers.add(new Bowler("Abigail Adams"));
		bowlers.add(new Bowler("Beetle Bailey"));
		bowlers.add(new Bowler("Charlie Chaplin"));
		bowlers.add(new Bowler("Dewey Decimal"));
		
		// Let's generate some games for our bowlers
		for (Bowler b : bowlers)	{
			// Generate a random number of games
			int n = Utility.randBetween(MIN_GAMES_PER_BOWLER, MAX_GAMES_PER_BOWLER);  
			for (int i=0; i < n; i++)	{
				// Game.randomGame() is a static method that generates 
				// a Game object with a random score on a random date	
				b.addGame(Game.randomGame());  
			}
		}
		
		// Let's print out the info we've collected on our bowlers and their games
		for (Bowler b : bowlers)	{
			System.out.println("Bowler Name: "		+ b.getBowlerName());
			System.out.println("Game Count: "		+ b.getGameCount());
			System.out.println("Last Game Date: "	+ b.getLastGameDateString());
			System.out.println("Last Game Score: "	+ b.getLastGameScore());
			System.out.println("Score Average: "	+ b.getScoreAverage());
			System.out.println("High Score: "		+ b.getHighScore());
			System.out.println("Game Details");
			for (Game g : b.getGames())	{
				System.out.println (g.toString());
			}
			System.out.println("------------------");
		}		
	}
}
