package com.chuchro.ucbx.javax4362.mod5;

public class HondaCivic extends AbstractVehicle implements Japanese, Compact {
	public static final String MODEL_NAME = "Honda Civic";

	public HondaCivic()	{
		super.weight	= Compact.WEIGHT;
		super.modelName	= HondaCivic.MODEL_NAME;
	}

}
