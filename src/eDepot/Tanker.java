package eDepot;

public class Tanker extends Vehicle{
	
	private String liquidType;
	private int liquidCapacity;
	
	public Tanker(String make, String model, Integer weight, String regNo, String liquidType, int liquidCapacity) {
		
		this.Make = make;
		this.Model = model;
		this.Weight = weight;
		this.regNo = regNo;
		this.liquidType = liquidType;
		this.liquidCapacity = liquidCapacity;
		
	}

	public void updateTankerDetails() {
		
	}
	
	public String getLiquidType() {
		
		return liquidType;
		
	}
	
	public int getLiquidCapacity() {
		
		return liquidCapacity;
		
	}
	
}
