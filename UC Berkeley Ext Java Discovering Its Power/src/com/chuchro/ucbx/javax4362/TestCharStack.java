package com.chuchro.ucbx.javax4362;

public class TestCharStack {

	public static void main(String[] args) {
		CharStack stack = new CharStack(10);
		String sample = "0123456789";
		char[] chars  = sample.toCharArray();
		
		System.out.println("Pushing ...");
		for (int i=0; i<chars.length; i++)	{
			stack.push(chars[i]);
		}
		
		System.out.println("Popping ...");
		for (int i=0; i<chars.length; i++)	{
			System.out.write(stack.pop());
		}
		System.out.println("One last pop ...");
		System.out.write(stack.pop());
		return;
	}

}
