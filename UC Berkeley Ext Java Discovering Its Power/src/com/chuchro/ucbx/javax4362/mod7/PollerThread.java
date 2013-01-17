package com.chuchro.ucbx.javax4362.mod7;

public class PollerThread extends Thread {
	private IntegerHolder ih;
	
	public PollerThread(IntegerHolder ih)	{
		this.ih = ih;
	}
	
	public void run()	{
		System.out.println("Running Poller");
		int i;
		do {
			i = ih.extract();			
			System.out.println("Polled Integer Value: " + i);
		}	while (i < 10);
		return;
	}

}
