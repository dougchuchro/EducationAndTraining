package com.chuchro.ucbx.javax4362.mod7;

public class Balloon {
	private static final int MAX_PRESSURE = 100;
	private int curPressure;
	
	Balloon()	{
		curPressure = 0;
	}
	
	public int getCurrentPressure()	{return curPressure;}
	
	public int inflate(int inflateByAmt) throws OverinflateException	{
		if(curPressure + inflateByAmt > MAX_PRESSURE)	{ 
			throw new OverinflateException("Unable to inflate beyond " + MAX_PRESSURE); 
		} else {
			curPressure = curPressure + inflateByAmt;
			return curPressure;
		}
	}

	public int deflate(int deflateByAmt)	{
		return curPressure = (curPressure - deflateByAmt > 0) ? curPressure - deflateByAmt : 0;
	}
	
}
