package com.chuchro.ucbx.javax4362.mod5;

public class CompilingExercise	{

	@SuppressWarnings("unused")
	public static void main(String[] args) {

		// 1
		SaturnSL1 sl = new SaturnSL1();

		// 2
		HondaCivic hc = new HondaCivic();

		// 3
		//	Japanese jp = new Japanese();
		Japanese jp = null;

		// 4
		German gr = new MercedesC230();

		// 5
		//	ChevyS10 cs = new Truck();
		ChevyS10 cs = null;

		// 6
		SubaruOutback sb = new SubaruOutback();

		// 7
		Domestic dm = sl;

		// 8
		//	dm = hc;
		dm = null;
		
		// 9
		//	dm = (Domestic) hc;

		// 10
		dm = cs; 

		// 11
		dm = (Domestic) cs;
		
		// 12
		Import im = sb;

		// 13
	//	gr = im;
		gr = null;
		
		// 14
	//	gr = (German) im; 
		
		// 15
	//	jp = im; 
		jp = null; 
		
		// 16
	//	jp = (German) im;
		jp = null;
		
		// 17
		jp = (Japanese) im; 
		
		// 18
	//	Automobile a = cs; 
		Automobile a = null; 

		// 19
		a = hc; 
	}
}
