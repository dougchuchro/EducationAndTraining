package com.chuchro.ucbx.javax4362.mod7;

public class CharStackUnderflowException extends Exception	{
	private static final long serialVersionUID = 1L;
	public final String message;

	public CharStackUnderflowException(String msg) {
        super(msg);
        message = msg;
    }
}

