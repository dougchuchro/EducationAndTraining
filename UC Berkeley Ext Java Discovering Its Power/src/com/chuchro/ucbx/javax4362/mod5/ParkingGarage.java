package com.chuchro.ucbx.javax4362.mod5;

public class ParkingGarage {
	public static final int MAX_NUM_VEHICLES = 20;			// Number of spaces in Parking Garage
	public static final int MAX_TOTAL_WEIGHT = 25000;		// Maximum load in pounds of Parking Garage
	public static final int NUM_PARKING_GARAGES = 3;		// Number of Parking Garages
	private static String PACKAGE_NAME = ParkingGarage.class.getPackage().getName();  // Current Java package name

	private int parkingGarageId;			// An ID for the Parking Garage
	private int curVehicleCount;			// Current number of vehicles in Parking Garage
	private int curTotalWeight;				// Current total weight of all cars in Parking Garage
	private Boolean garageIsFull;			// Flag to indicate if the garage if full
	private Vehicle[] vehicles;				// Array of Vehicles in our Parking Garage
	
	// Our constructor initializes the object's member variables
	ParkingGarage(int pgid)	{
		this.parkingGarageId = pgid;
		this.curVehicleCount = 0;
		this.curTotalWeight	= 0;
		this.garageIsFull = false;
		this.vehicles = new Vehicle[MAX_NUM_VEHICLES];
	}
	
	public static void main(String[] args) {
		// We've invested in <NUM_PARKING_GARAGES> Parking Garages; we'll use an array to collect them
		ParkingGarage[] pgs = new ParkingGarage[NUM_PARKING_GARAGES];

		// Create/instantiate our parking garages
		for (int i=0; i < NUM_PARKING_GARAGES; i++ )	{
			pgs[i] = new ParkingGarage(i+1);			
		}

		// We're open for business, let's park some cars in all our garages!
		for (ParkingGarage pg : pgs)	{
			pg.parkCarsUntilFull();
		}

		// Print out some final information about the cars in our Parking Garage
		for (ParkingGarage pg : pgs)	{
			System.out.println("Final Number of Cars in Parking Garage #" + pg.parkingGarageId + ": " + pg.curVehicleCount);
			System.out.println("Final Total Weight of Cars in Parking Garage: "	+ pg.curTotalWeight);
			System.out.println("List of Cars in Parking Garage: ");
			for (Vehicle v : pg.vehicles)	{
				if (v == null)	{break;}
				System.out.print(v.getModelName() + ", ");
			}
			System.out.println();
			System.out.println("--------------------------------------------");
		}	
	}
	
	private void parkCarsUntilFull ()	{
		while (!garageIsFull)	{
			// Let's get our next customer
			Vehicle randomV = randomVehicle();
			// Check to see if we can accommodate this vehicle that wants to park in our garage
			if (curVehicleCount + 1> MAX_NUM_VEHICLES || curTotalWeight + randomV.getWeightInPounds()> MAX_TOTAL_WEIGHT )	{
				// Garage is full
				garageIsFull = true;
				System.out.println("Sorry, we're full");
				System.out.println("--------------------------------------------");
				break;
			} else {
				// We've got room for this vehicle
				curVehicleCount++;
				curTotalWeight = curTotalWeight + randomV.getWeightInPounds();
				vehicles[curVehicleCount - 1]= randomV;
				System.out.println(	"Thank you for parking your " + randomV.getModelName() + 
									" (weight: " + randomV.getWeightInPounds() + " lbs)" );
				System.out.println( "Current Number of Cars in Parking Garage: " + curVehicleCount);
				System.out.println( "Total Weight of Cars in Parking Garage: " + curTotalWeight);
				System.out.println("--------------------------------------------");
			}
		}
	}
	
	private static Vehicle randomVehicle()	{
		// This method returns a random Vehicle Class from the VehicleClasses enum (below)
		// We use the method to simulate random vehicles coming to our Parking Garage 
	    int pick = new java.util.Random().nextInt(VehicleClasses.values().length);
	    String vehicleClass = VehicleClasses.values()[pick].name();
	    try {
	    	return (Vehicle)Class.forName(PACKAGE_NAME + "." + vehicleClass).newInstance();
	    } catch (ClassNotFoundException e)	{
	    	System.out.println("ClassNotFoundException thrown: " + e.getMessage());
	    	return null;
	    } catch (IllegalAccessException e)	{
	    	System.out.println("IllegalAccessException thrown: " + e.getMessage());
	    	return null;
	    } catch (InstantiationException e)	{
	    	System.out.println("InstantiationException thrown: " + e.getMessage());
	    	return null;
	    }
	}
}

enum VehicleClasses {
	SaturnSL1,
	HondaCivic,
	MercedesC230,
	ChevyS10,
	SubaruOutback,	
}
