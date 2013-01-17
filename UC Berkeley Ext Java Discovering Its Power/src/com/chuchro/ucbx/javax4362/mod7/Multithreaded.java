package com.chuchro.ucbx.javax4362.mod7;

public class Multithreaded {
	
	public static void main(String[] args)	{
		IntegerHolder ih = new IntegerHolder();
		
		PusherThread pusher = new PusherThread(ih);
		PollerThread poller = new PollerThread(ih);

		pusher.start();
		poller.start();
		
	}
	
}
