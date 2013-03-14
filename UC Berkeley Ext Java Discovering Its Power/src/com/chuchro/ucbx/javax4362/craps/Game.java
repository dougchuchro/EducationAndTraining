package com.chuchro.ucbx.javax4362.craps;

/**
 * This class represents a craps game, absent any of the wagering that a player may make for, against, or
 * about the game.  The full game lifecycle is represented from the come out roll to the final outcome of the game: win or lose.
 * @author Doug Chuchro (doug@chuchro.net)
 */
public class Game {
	/** Dice used to play the game.  Set as private because we don't want any tampering with the dice.	*/
	private Dice dice;
	/** The point established for the game.  If the point has not been established, this object will either be null or set to zero.	
	 *	Set as private because we don't want any tampering with the point to influence the outcome of the game.*/
	private int point;
	/** Current status of the game as defined by Game.GameStatus enum.	*/
	public GameStatus gameStatus;

	/**
	 * Basic constructor initializes member fields.
	 */
	public Game()	{
		dice = new Dice();
		point = 0;
		gameStatus = GameStatus.NOT_STARTED;
	}

	/** Getter method for the point	
	 * @return this.point	*/
	public int getPoint()	{return this.point;}
	/** Getter method for the dice 
	 * @return this.dice	*/
	public Dice getDice()	{return this.dice;}
	
	/** 
	 * Initial roll of the dice which will either win/lose the game, or establish the point.
	 */
	public void comeOutRoll()	{
		System.out.println("Coming out!");
		dice.rollDice();
		switch (dice.getRollSum())	{
		case (7): case (11):
			System.out.println("Come out winner!");
			gameStatus = GameStatus.WON;		// come out roll game winner!
			break;
		case (2): case (3): case (12):
			System.out.println("Come out loser!");
			gameStatus = GameStatus.LOST;		// crap out ... game loser!
			break;		
		case (4): case (5):	case (6): case (8): case (9): case (10): 
			point = dice.getRollSum();			// point is established
			gameStatus = GameStatus.POINT_SET;
		default:
			break;
		}
		return;
	}

	/** 
	 * After the point has been established on the come out roll, this method will be called until the game is won or lost.
	 */
	public void pointRoll()	{
		dice.rollDice();
		if (dice.getRollSum() == point) {	// hit the point ... game winner!
			gameStatus = GameStatus.WON;		
		} else {
			if (dice.getRollSum() == 7) {	// crap out ... game loser!
				gameStatus = GameStatus.LOST;		
			}
		}
		return;
	}

	/**
	 * Prints the game's point to the console.
	 */
	public void printPoint()	{
		System.out.println("Point set to: " + point);
	}

	/**
	 * Enumeration of all of the valid game states/statuses.
	 */
	public enum GameStatus {
		/** The game has not yet started. */		NOT_STARTED, 
		/** The point of the game has been set. */	POINT_SET, 
		/** The game has been won. */				WON, 
		/** The game has been lost. */				LOST,
	}

}
