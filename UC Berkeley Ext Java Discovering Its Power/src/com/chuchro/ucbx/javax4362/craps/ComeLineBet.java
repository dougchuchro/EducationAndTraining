package com.chuchro.ucbx.javax4362.craps;

public class ComeLineBet extends PassLineBet {
	public static String betName = "Come Line bet";

	public ComeLineBet(int maxAmt) {
		super(maxAmt, betName);
	}
	public void setPoint(int point)	{
		if (POINTS.contains(point))	{
			super.setPoint(point);
			System.out.println("Your " + betName + " point has been set to " + point);
		}
	}

}