package com.chuchro.ucbx.javax4362.craps;

import java.util.Random;

public class Die {
	public int side;			// the side of the die that faces up when rolled
	public DieNumber cSide;	// English word for the number of "side" (above)
	private Random random;
	private int numSidesOfDie = 6;

	public Die()	{
		side = 0; // die not yet rolled
		cSide = null;
		random = new Random();
	}
	public int roll()	{
		side = random.nextInt(numSidesOfDie) + 1;
		cSide = DieNumber.values()[side-1];
		return side;
	}
	public enum DieNumber {
		ONE, TWO, THREE, FOUR, FIVE, SIX,
	}
}