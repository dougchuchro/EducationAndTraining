package com.chuchro.ucbx.javax4362.mod6;

import java.util.*;
import java.text.*;

// Because we are using this class as our generic for a TreeSet, which implements SortedSet,
// we need to implement the Comparable interface so that the TreeSet knows how to compare 2 game objects
// Since we will be ordering by gameDate, our compareTo() method will use the compareTo() of that member variable
public class Game implements Comparable<Game>	{
	private final int score;
	private final GregorianCalendar gameDate;
	private static SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
	public static final int MAX_GAME_SCORE = 300;
	public static final int MIN_GAME_SCORE =   0;

	Game(int score, GregorianCalendar date)	{
		this.score		= score;
		this.gameDate	= date;
	}
	
	public int getScore()					{return score;}
	public GregorianCalendar getGameDate()	{return gameDate;}
	public String getGameDateString()		{return sdf.format(gameDate.getTime());}	

	public static Game randomGame()	{
		// Generate a random game score base on the static MIN and MAX specific above
		int randScore = Utility.randBetween(MIN_GAME_SCORE, MAX_GAME_SCORE);
		GregorianCalendar gc = Utility.randomGregCal(2001);
		return new Game(randScore, gc);
	}
	
	public int compareTo(Game g)	{
		return this.gameDate.compareTo(g.getGameDate());
	}
	
	public String toString()	{
		return "\tGame Date: " + getGameDateString() + "\t\tGame Score: " + score;		
	}
}
