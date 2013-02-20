package com.chuchro.ucbx.javax4362.craps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
		System.out.println("Ready to roll ... press enter to roll the dice");
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1);	
		try {
			stdin.readLine();
		} catch (IOException e) { }
		rollSum = d1.roll() + d2.roll();
		return rollSum;
	}

	public void printDice()	{
		System.out.println(d1.cSide + " / " + d2.cSide + " (" + rollSum + ")");
	}

}
