package com.chuchro.ucbx.javax4362;
/*
 * Student Name:	Doug Chuchro
 * Course Name:		X436.2 Java: Discovering Its Power  
 * Instructor:		Carl Limsico
 * Assignment:		Module 3 - String Reverser
*/
public class StringReverser {

	public static void main(String[] args) {
		if (args.length < 1)	{
			System.out.println("Usage: java StringReverser \"string to reverse\"");
			System.out.println("	Be sure to use quotes if you have any whitespace in your string");
			return;
		}
		String inputString = args[0];
		String revString = reverse(inputString);
		System.out.println("The reverse of your input string is:");
		System.out.println(revString);
	}
	
	private static String reverse(String s)	{
		//  Recursive method keeps calling itself until the string is whittled down to one character
		if (s.length() <= 1)	{
			return s;
		} else {
			char firstChar = s.charAt(0);
			return reverse(s.substring(1)) + firstChar;
		}
	}

}
