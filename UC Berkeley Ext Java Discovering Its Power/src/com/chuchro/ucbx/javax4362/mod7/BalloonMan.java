package com.chuchro.ucbx.javax4362.mod7;

import java.util.Random;

public class BalloonMan {
	Random rand = new Random();
	
	public static void main(String[] args) {
		Balloon b = new Balloon();
		Random rand = new Random();
		boolean maxPressureReached = false;

		while (!maxPressureReached)	{
			int inflateByAmt = rand.nextInt(21); // pick a random number to inflate the Balloon by
			System.out.println("Trying to inflate our balloon by " + inflateByAmt + " PSI");
			try {
				b.inflate(inflateByAmt);
				System.out.println("New balloon pressure: " + b.getCurrentPressure() + " PSI");
			} catch (OverinflateException e) {
				System.out.println(e.getMessage());
				maxPressureReached = true;
			}
		}
	}
}
