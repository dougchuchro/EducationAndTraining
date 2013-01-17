package com.chuchro.ucbx.javax4362.mod6;

import java.util.*;

public class Bowler {
	private String			bowlerName;
	private TreeSet<Game>	games;
	private int				highScore;
	
	// Constructors
	Bowler()	{
		new Bowler("");
	}
	
	Bowler(String name)	{
		this.bowlerName	= name;
		this.highScore	= 0;
		this.games		= new TreeSet<Game>();
	}
	
	// Setter methods
	public void setBowlerName(String name)	{this.bowlerName = name;}

	// Getter methods
	public String				getBowlerName()			{return bowlerName;}
	public int					getHighScore()			{return highScore;}
	public TreeSet<Game> 		getGames()				{return games;}	
	public int 					getGameCount()			{return games.size();}
	public int 					getLastGameScore()		{return games.last().getScore();}
	public GregorianCalendar	getLastGameDate()		{return games.last().getGameDate();}
	public String 				getLastGameDateString()	{return games.last().getGameDateString();}
	
	public void addGame(Game g)	{
		if (this.highScore < g.getScore())	{this.highScore = g.getScore();	}
		games.add(g);
	}
	
	public double getScoreAverage()	{
		int sumOfScores = 0;
		for (Game g : games)	{sumOfScores += g.getScore();}
		return (double)sumOfScores/games.size();
	}
	
}
