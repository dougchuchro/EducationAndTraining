package com.chuchro.ucbx.javax4362.craps;

/**
 * Main class for the Craps simulator program contained in this package.
 * The static main() method creates an object of it's class, creates a new Session object and invokes sesson.play() to 
 * start the player's session.  Note that this version only accommodates one player; future versions may accomodate multiple 
 * players by implementing multiple sessions.
 * @author Doug Chuchro
 * @see Session
 */
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
