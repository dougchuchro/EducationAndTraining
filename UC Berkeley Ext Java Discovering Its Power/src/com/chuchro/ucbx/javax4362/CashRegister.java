package com.chuchro.ucbx.javax4362;

public class CashRegister {
	private double	totalAmount;
	private int		transactionCount;
	
	public CashRegister()	{
		this.ResetTransactions();
	}
	
	public void AddTransaction(double amount)	{
		this.totalAmount = this.totalAmount + amount;
		transactionCount++;
	}
	
	public int TransactionCount()	{
		return transactionCount;
	}
	
	public double Total()	{
		return totalAmount;
	}
	
	public void ResetTransactions()	{
		totalAmount = 0.0;
		transactionCount = 0;
	}
	
}