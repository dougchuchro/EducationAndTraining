package com.chuchro.ucbx.javax4362.mod5;

public class MercedesC230 extends AbstractVehicle implements German, Sedan {
	public static final String MODEL_NAME = "Mercedes C230";

	public MercedesC230()	{
		super.weight = Sedan.WEIGHT;
		super.modelName	= MercedesC230.MODEL_NAME;
	}
}
