package com.chuchro.ucbx.javax4362.craps;

public class Dice {
	public Die d1;
	public Die d2;
	public int rollSum;
	
	public Dice()	{
		d1 = new Die();
		d2 = new Die();
		rollSum = 0;
	}
	
	public int rollDice()	{
		rollSum = d1.roll() + d2.roll();
		return rollSum;
	}

}
