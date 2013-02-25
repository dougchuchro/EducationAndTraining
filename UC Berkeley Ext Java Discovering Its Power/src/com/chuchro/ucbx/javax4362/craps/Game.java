package com.chuchro.ucbx.javax4362.craps;

public class Game {
	public Dice dice;
	public int point;
	public String cPoint;		// English word for point
	public GameStatus gameStatus;

	public Game()	{
		dice = new Dice();
		point = 0;
		gameStatus = GameStatus.NOT_STARTED;
	}

	public void comeOutRoll()	{
		dice.rollDice();
		dice.printDice();
		switch (dice.rollSum)	{
		case (7):
		case (11): 
			System.out.println("Come out winner!");
			gameStatus = GameStatus.WON;		// come out roll winner!
			break;
		case (2):
		case (3):
		case (12):
			System.out.println("Come out loser!");
			gameStatus = GameStatus.LOST;		// crap out ... lose!
			break;		
		case (4):
		case (5):
		case (6):
		case (8):
		case (9):
		case (10): 
			point = dice.rollSum;				// point is established
			gameStatus = GameStatus.POINT_SET;
		default:
			break;
		}
		return;
	}

	public void pointRoll()	{
		dice.rollDice();
		if (dice.rollSum == point) {	// hit the point ... winner!
			gameStatus = GameStatus.WON;		
		} else {
			if (dice.rollSum == 7) {	// crap out ... lose!
				gameStatus = GameStatus.LOST;		
			}
		}
		return;
	}

	public void printPoint()	{
		System.out.println("Point set to: " + point);
	}

	public enum GameStatus {
		NOT_STARTED, POINT_SET, WON, LOST
	}

}
