package eDepot;

import java.util.Date;

public class WorkSchedule {
	
	private String vehicleName;
	private String driverUsername;
	private String clientName;
	private Date startDate;
	private Date endDate;
	
	public WorkSchedule(String vehicle, String driverUsername, String clientName, Date startDate, Date endDate) {
		
		this.vehicleName = vehicle;
		this.driverUsername = driverUsername;
		this.clientName = clientName;
		this.startDate = startDate;
		this.endDate = endDate;
		
	}
	
	public String getVehicleName() {
		return vehicleName;
	}
	
	public String getDriverUsername() {
		return driverUsername;
	}
	
	public String getClientName() {
		return clientName;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
}
