package com.chuchro.ucbx.javax4362;
import java.io.IOException;

public class CharStack {
	private char[] m_data;
	private int m_ptr;
		
	public CharStack(int size)	{
		m_ptr = 0;
		m_data = new char[(size > 1 ? size : 10)];
	}
	  
	public void push(char c)	{
		if (m_ptr >= m_data.length)	{
			// Grow the array automatically
		    char[] tmp = new char[m_data.length * 2];
		    System.arraycopy(m_data, 0, tmp, 0, m_data.length);
		    m_data = tmp;
		}
		m_data[m_ptr++] = c;
	}

	public char pop()	{
		return m_data[--m_ptr];
	}
	
	public boolean hasMoreElements()	{
		return (m_ptr != 0);
	}

	public static void main(String[] argv) throws IOException	{
		CharStack s = new CharStack(10);
		int i;
		while ( (i = System.in.read()) != -1 )	{
		         s.push((char) i);
		}
		while (s.hasMoreElements())	{
			System.out.write(s.pop());
		}
		System.out.println();
	}
}
