package com.chuchro.ucbx.javax4362;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
			// Usage: java StringSorter [word list]
			// if no words are provided when invoked, we will prompt user for input
			String[] words;				// Array or strings("words") to be sorted
			String inputString = "";	// String of space-delimited words
			if (args.length == 0)	{
				System.out.println("Please enter a list of words, seperated by spaces, and I'll sort them for you");
				BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1);
				inputString = stdin.readLine();
				// Use String.split() to split the space-delimited string into an array of strings
				words = inputString.split("\\s+");
			} else	{
				words = args;
				for (int i = 0; i < words.length; i++)
					inputString = inputString + words[i] + " ";
			}
		
			System.out.println();
			System.out.println("The series of words that you asked me to sort are: \n" + inputString);
			
			// Parse the input string, put each element (word) into a new array of Strings
			
			// Sort the words in the array, using the bubble sort algorithm
			// (http://en.wikipedia.org/wiki/Sorting_algorithm#Bubble_sort)
			final int wordListSize = words.length;	// we'll use the array length often, so let's put in into an int
			String tempHolder;						// we'll use this as a temp holder when we need to swap word order
			Boolean isSorted = false;				// set this flag to false to enter the while loop
			
			while (!isSorted)	{	// repeat the following until isSorted stays true by always failing the compairTo() test
				isSorted = true;	// set the flag to true, it will stay true only if all items are in order
				for (int i = 0; i < (wordListSize - 1); i++)	{	// note that we can't go to the last item,
																	// only the next to last item (wordListSize - 1)
																	// which will compare it to the last item
					if (words[i].compareTo(words[i+1]) > 0)	{	// are the current and next items out of order?
						// we have out-of-order words, so set the flag to false
						isSorted	= false;
						// swap the order of the current and next words
						tempHolder	= words[i];
						words[i]	= words[i+1];
						words[i+1]	= tempHolder;
					}
				}
			}
			
			// Print out the sorting results
			System.out.println();
			System.out.println("Here are your words sorted alphabetically");
			for (int i = 0; i < wordListSize; i++)	{
				System.out.println("Sorted Item #" + (i+1) + ": " + words[i]);
			}
		}
}
