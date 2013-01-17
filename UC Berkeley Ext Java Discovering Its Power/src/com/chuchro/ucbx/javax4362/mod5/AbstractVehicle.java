package com.chuchro.ucbx.javax4362.mod5;

public abstract class AbstractVehicle implements Vehicle {
	int weight;
	String modelName;
	@Override
	public int getWeightInPounds()	{
		return weight;
	}
	public String getModelName()	{
		return modelName;
	}
}
