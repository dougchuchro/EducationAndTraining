package com.chuchro.ucbx.javax4362.craps;

public class DontComeBet extends DontPassBet {
	public static String betName = "Don't Come bet";

	public DontComeBet(int maxAmt) {
		super(maxAmt, betName);
	}
	public void setPoint(int point)	{
		if (POINTS.contains(point))	{
			super.setPoint(point);
			System.out.println("Your " + betName + " point has been set to " + point);
		}
	}

}
