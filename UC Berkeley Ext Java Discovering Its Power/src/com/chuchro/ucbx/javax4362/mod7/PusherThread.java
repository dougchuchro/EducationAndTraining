package com.chuchro.ucbx.javax4362.mod7;

public class PusherThread extends Thread {
	private IntegerHolder ih;
	
	public PusherThread(IntegerHolder ih)	{
		this.ih = ih;
	}
	
	public void run()	{
		System.out.println("Running Pusher");
		for (int i=1; i<11; i++)	{
			System.out.println("Pusher Loop: " + i);
			ih.insert(i);
		}
		return;
	}
}

