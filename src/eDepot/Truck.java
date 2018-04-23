package eDepot;

/**
 * Truck information for eDepot.
 * 
 * Truck is an extension of "Vehicle".
 * 
 * Has the following attributes:
 * make
 * model
 * weight
 * regNo {FORMAT: AA00AAA}
 * cargoCapacity
 * 
 */

public class Truck extends Vehicle{
	
	private int cargoCapacity;
	
	public Truck(String make, String model, Integer weight, String regNo, int cargoCapacity) {
		
		this.Make = make;
		this.Model = model;
		this.Weight = weight;
		this.regNo = regNo;
		this.cargoCapacity = cargoCapacity;
		
	}

	public void updateTruckDetails() {
		
	}
	
	public int getCargoCapacity() {
		
		return cargoCapacity;
		
	}
	
}
