package com.chuchro.ucbx.javax4362.mod7;

public class StudyQuestion2 {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String[] testArray = new String[] {"a", "b", "c"};
		try	{
			String s = testArray[5];
		} catch (ArrayIndexOutOfBoundsException e)	{
			System.out.println("Error Message: " + e.getMessage());			
		}
		System.out.println("FINISHED");
	}

}
