package com.chuchro.ucbx.javax4362.mod7;

import java.io.*;

public class CharStackTester {

	public static void main(String[] args) throws IOException	{
		String inputString = "";
		CharStack s = new CharStack(10);

		// Parse our input arguments 
		for (String arg : args) {
			inputString = inputString.concat(arg);
		}
		System.out.println(inputString);

		// convert our input into an array of chars
		char[] inChars = inputString.toCharArray();
		
		// push() all of the chars to the CharStack
		for (char c : inChars)	{
			s.push(c);
		}

		// pop() and write all of the chars to std out
		while (s.hasMoreElements())	{
			char c;
			try {
				c = s.pop();
			} catch (CharStackUnderflowException ctue)	{
				// print the exception info to std err
				System.err.println("oh boy!");				
				System.err.println(ctue.getMessage());
				return;
			}
			System.out.write(c);
		}
		// Clear the output buffer
		System.out.println();

		// here's where we pop() from an empty stack
		// we expect pop() to throw an exception, which
		// we we will catch and process.
		try {
			s.pop();
		} catch (CharStackUnderflowException ctue)	{
			// print the exception info to std err
			System.err.println("oh no!");				
			System.err.println(ctue.getMessage());
			return;
		}
	}
}
