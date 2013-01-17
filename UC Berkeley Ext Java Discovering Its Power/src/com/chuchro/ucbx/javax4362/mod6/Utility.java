package com.chuchro.ucbx.javax4362.mod6;

import java.util.GregorianCalendar;
import java.util.Random;

public class Utility {
	private static Random random = new Random();
	
    public static GregorianCalendar randomGregCal() {
    	return randomGregCal(1900);
    }
    
    public static GregorianCalendar randomGregCal(int startYear) {
        GregorianCalendar gc = new GregorianCalendar();	// No-arg constructor sets the date to today
        
        // Get a random year between the specified startYear and this year (inclusive)
        int year = randBetween(startYear, gc.get(GregorianCalendar.YEAR));

        // Get a random month
        int month = randBetween(GregorianCalendar.JANUARY, GregorianCalendar.DECEMBER);
        
        // Set the calendar object to the first day of the randomly select year and month
        gc.set(year, month, 1);
        
        // Get a random day of the specified month/year
        int day = randBetween(1, gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
        gc.set(year, month, day);
        return gc;
    }

	public static int randBetween(int start, int end) {
		return start + random.nextInt(end - start + 1);  // we need to + 1 to make the set of number inclusive of the high-end
    }
}
