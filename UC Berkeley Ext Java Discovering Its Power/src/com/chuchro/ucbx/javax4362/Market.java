package com.chuchro.ucbx.javax4362;

public class Market {

	public static void main(String[] args) {
		// Create 3 cash registers for our Market (we're expecting a lot of customers!)
		// First create an array of CashRegisters, this will initialize the array
		CashRegister[] myCashRegisters = new CashRegister[3];
		// The previous statement initialized the array, but did not initialize the elements of the array
		// so we have to loop through and "construct" each CashRegister in the array
		for (int i=0; i<myCashRegisters.length; i++)	{
			myCashRegisters[i] = new CashRegister();
		}

		// Let's confirm that our registers start out clean (all totals should be zero)
		System.out.println("Initial register settings");
		printRegisterInfo(myCashRegisters);
		
		System.out.println("Recording some transactions");		
		// Record some transactions for Register One
		myCashRegisters[0].AddTransaction(19.45);
		myCashRegisters[0].AddTransaction(28.19);
		myCashRegisters[0].AddTransaction(4.60);
		
		// Record some transactions for Register Two
		myCashRegisters[1].AddTransaction(28.33);
		myCashRegisters[1].AddTransaction(9.06);

		// Record some transactions for Register Three
		myCashRegisters[2].AddTransaction(22.65);
		myCashRegisters[2].AddTransaction(32.37);
		myCashRegisters[2].AddTransaction(41.11);
		myCashRegisters[2].AddTransaction(14.61);
		
		// Let's get our totals
		printRegisterInfo(myCashRegisters);

		// Now let's reset the registers
		System.out.println("Resetting registers");
		for (int i=0; i<myCashRegisters.length; i++)	{
			myCashRegisters[i].ResetTransactions();
		}

		// Let's confirm that our registers are all reset
		printRegisterInfo(myCashRegisters);
	}
	
	private static void printRegisterInfo(CashRegister[] myCashRegisters)	{
		// Prints Transaction Count and Total Amount for all registers in our array of CashRegisters
		for (int i=0; i<myCashRegisters.length; i++)	{
			System.out.println("Register " + (i+1) + " Transaction Count: " + myCashRegisters[i].TransactionCount());
			System.out.println("Register " + (i+1) + " Total Amount: " + myCashRegisters[i].Total());
			System.out.println();
		}
	}
}
