package com.chuchro.ucbx.javax4362.mod5;

public class ChevyS10 extends AbstractVehicle implements Detroit, Truck {
	public static final String MODEL_NAME = "Chevy S10";

	public ChevyS10()	{
		super.weight 	= Truck.WEIGHT;
		super.modelName	= ChevyS10.MODEL_NAME;
	}

}
