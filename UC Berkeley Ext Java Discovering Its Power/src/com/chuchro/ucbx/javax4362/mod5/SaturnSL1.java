package com.chuchro.ucbx.javax4362.mod5;

public class SaturnSL1 extends AbstractVehicle implements SpringHill, Sedan {
	public static final String MODEL_NAME = "Saturn SL1";

	public SaturnSL1()	{
		super.weight 	= Sedan.WEIGHT;
		super.modelName	= SaturnSL1.MODEL_NAME;
	}
}
