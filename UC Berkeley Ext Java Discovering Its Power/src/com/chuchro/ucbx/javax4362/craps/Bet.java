package com.chuchro.ucbx.javax4362.craps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

abstract class Bet {
	public String		betName;		// name of the bet, ex: "Pass Line bet", "Come line bet", "Field bet"
	public BetStatus	betStatus;		// current status of this bet, based on BetStatus enumeration defined below
	public int			amount;			// amount of the bet
	public double		payoutRatio;	// expressed as x/y, how much the house pays ($x) for every $y bet, 
										// ex: 6/5 pays $6 on a $5 bet
	public List<Integer>	winners;	// all the rolls that win this bet
	public List<Integer>	losers;		// all the rolls that lose this bet
	public int		minAmount;			// minimum amount of bet
	public int		maxAmount;			// maximum amount of bet
	public int 		amountMultiple;		// some bets can only be a multiple of a certain number for the odds 
										// payout to not be fractional therefore the house stipulates that 
										// the bet must be a multiple of a certain amount (1=no multiple requirement) 

	Bet(String betName, int minAmt, int maxAmt, int amountMultiple)	{
		this.betName		= betName;
		this.minAmount		= minAmt;
		this.maxAmount		= maxAmt;
		this.amountMultiple	= amountMultiple;
		this.amount			= promptBetAmount();
		this.betStatus		= Bet.BetStatus.BET_ON;
		this.winners		= new ArrayList<Integer>();
		this.losers			= new ArrayList<Integer>();
		System.out.println(betName + " for the amount of $" + this.amount + 
						" is placed, bet amount deducted from your chip count");
	}

	private int promptBetAmount()	{
		int betAmount = -1;
		System.out.println("Place your " + betName + " , which must be greater than $" + minAmount);
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1);
		while (betAmount == -1)	{
			try {
				betAmount = Integer.parseInt(stdin.readLine());
			} catch (Exception e) {
				System.out.println("Please enter you bet amount as an integer");
				betAmount = -1;
				continue;
			}
			if (betAmount > maxAmount) {
				System.out.println("You bet cannot exceed your chip count, which is currently $" + maxAmount);
				betAmount = -1;
				continue;				
			}
			if (betAmount < minAmount) {
				System.out.println("You bet must be at least the minimum amount, which is $" + minAmount);
				betAmount = -1;
				continue;				
			}
			if (!checkBetMultiple(amount))	{
				System.out.println(betName +" must be a multiple of " + amountMultiple);
				betAmount = -1;
				continue;				
			}
		}
		return betAmount;
	}

	public int payoutWinner()	{
		int payoutAmt =(int)Math.round(amount * payoutRatio);
		System.out.println(betName + " winner!");
		System.out.println("$" + payoutAmt + " is added to your chip count");
		betStatus = BetStatus.BET_SETTLED;
		return payoutAmt;
	}

	public int deductLoser()	{
		System.out.println("Sorry, you lost your " + betName + " of $" + amount);
		betStatus = BetStatus.BET_SETTLED;
		return amount;
	}

	private boolean checkBetMultiple(int amount)	{
		if (amount % this.amountMultiple == 0)	{
			return true;
		} else {
			return false;
		}	
	}
	
	public int checkBet(Dice dice)	{
		int result = 0;
		// if the bet is not active, then just return zero
		if (this.betStatus != Bet.BetStatus.BET_ON)	{
			return result;
		}
		// check to see if this roll was a winner
		for (int winner : winners)	{
			if (dice.rollSum == winner)	{
				result = payoutWinner();
				return result;
			}
		}
		// check to see if this roll was a loser
		for (int loser : losers)	{
			if (dice.rollSum == loser)	{
				result = deductLoser();
				return result;
			}
		}
		return amount; 
	}
	
	public enum BetStatus	{
		BET_ON,			// the bet is currently on and will be evaluated after the next roll
		BET_OFF,		// the bet is still on the table but will be off for the next roll, can neither be won nor lost
		BET_WON,		// the previous roll has been evaluated as a winner for this bet, player will be credited
		BET_LOST,		// the previous roll has been evaluated as a loser for this bet, house will take the player's bet $
		BET_SETTLED,	// the bet has been either won or lost and the player has been credited or debited, bet is no longer relevant
		BET_PULLED,		// the player has chosen to take the bet off the table (only valid for certain bet types)
	}

}