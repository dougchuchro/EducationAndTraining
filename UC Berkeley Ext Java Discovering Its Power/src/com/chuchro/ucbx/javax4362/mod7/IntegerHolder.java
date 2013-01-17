package com.chuchro.ucbx.javax4362.mod7;

public class IntegerHolder {
    private int data;
    private boolean dataPresentFlag;	// indicator to whether data is present
    
    IntegerHolder ()	{
    	dataPresentFlag = false;
    }
    
    synchronized boolean isDataPresent ()	{
		System.out.println("Is Data Present? " + dataPresentFlag);
   	return dataPresentFlag;
    }
    
    synchronized void setDataPresentFlag (Boolean f)	{
    	dataPresentFlag = f;
    	return;
    }
    
    synchronized void insert(int i)	{
		System.out.println("inserting " + i);
		while (isDataPresent())	{
			System.out.println("waiting for data to clear ...");
			notify();
			try {
				wait(1000);
			} catch (InterruptedException e)	{
				return;
			}
		}
		data = i;
		dataPresentFlag = true;
   }
   
	synchronized int extract()	{
		System.out.println("extracting");
		while (!isDataPresent())	{
			System.out.println("waiting for data ...");
			notify();
			try {
				wait(1000);
			} catch (InterruptedException e)	{
				System.out.println("Interupted with message: " + e.getMessage());
			}
		}
		dataPresentFlag = false;
		int temp = data;
		data = 0;
		notify();
		return temp;
	}
}
