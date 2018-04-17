package eDepot;

import java.util.Date;

public class WorkSchedule {
	
	private Vehicle vehicleName;
	private Driver driverName;
	private String Client;
	private Date startDate;
	private Date endDate;
	
	public WorkSchedule(Vehicle vehicle, Driver driver, String clientName, Date startDate, Date endDate) {
		
		this.vehicleName = vehicle;
		this.driverName = driver;
		this.Client = clientName;
		this.startDate = startDate;
		this.endDate = endDate;
		
	}
	
	
	
}
