package com.chuchro.ucbx.javax4362.craps;

public class Craps {
	public Session session;
	
	public Craps()	{
		session = new Session();
	}
	
	public void play()	{
		session.play();
		return;
	}
	
	public static void main(String[] args) {
		Craps craps = new Craps();
		craps.play();
	}
}
