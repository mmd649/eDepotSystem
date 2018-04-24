package eDepot;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Depot {
	
	private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

	//An arraylist for each attributes which makes up a user.
	private ArrayList<String> userDepot = new ArrayList<>();
	private static ArrayList<String> userName = new ArrayList<>();
	private static ArrayList<String> passWord = new ArrayList<>();
	private static ArrayList<String> jobType = new ArrayList<>();
	
	//An arraylist for each attributes which makes up a truck.
	private ArrayList<String> truckDepot = new ArrayList<>();
	private ArrayList<String> truckMake = new ArrayList<>();
	private ArrayList<String> truckModel = new ArrayList<>();
	private ArrayList<Integer> truckWeight = new ArrayList<>();
	private ArrayList<String> truckRegNo = new ArrayList<>();
	private ArrayList<Integer> truckCargoCapacity = new ArrayList<>();
	
	//An arraylist for each attributes which makes up a tanker.
	private ArrayList<String> tankerDepot = new ArrayList<>();
	private ArrayList<String> tankerMake = new ArrayList<>();
	private ArrayList<String> tankerModel = new ArrayList<>();
	private ArrayList<Integer> tankerWeight = new ArrayList<>();
	private ArrayList<String> tankerRegNo = new ArrayList<>();
	private ArrayList<String> tankerLiquidType = new ArrayList<>();
	private ArrayList<Integer> tankerLiquidCapacity = new ArrayList<>();
	
	//An arraylist for each attributes which makes up a schedule.
	private ArrayList<String> vehicleName = new ArrayList<>(); 
	private ArrayList<String> driverUsername = new ArrayList<>();
	private ArrayList<String> clientName = new ArrayList<>();
	private ArrayList<Date> startDate = new ArrayList<>();
	private ArrayList<Date> endDate = new ArrayList<>();
	private ArrayList<Boolean> isArchived = new ArrayList<>();
	

	public Depot() {
		
		loadUserDetails();
		loadTruckDetails();
		loadTankerDetails();
		loadScheduleDetails();
		
	}

	public boolean logOn(String user, String pass) {
		
		boolean authenticate = false;
		
		// gets input from uses
		if (userName.contains(user)) {
			
			int index = userName.indexOf(user);
			
			if(passWord.get(index).equals(pass)){
				
				System.out.print("\nWelcome " + user);
				authenticate = true;
				
			}else {
				
				System.err.print("Sorry username does not match our records!");
				authenticate = false;
				
			}
		}
		return authenticate;
	}
	
	public int getIndex(String username) {
		
		int index = userName.indexOf(username);
		
		return index;
		
	}
	
	public Boolean checkUserJobType(int index){
		
		Boolean verifyJob;
		// checks if user is manager or driver.
		if(jobType.get(index).equals("manager")){
			verifyJob = true;
						
		}else{
			verifyJob = false;
		}
		return verifyJob;
	}
	
	public void loadUserDetails() {
		try {
			Scanner s = new Scanner(Depot.class.getResourceAsStream("/txt/users.txt"));

			while (s.hasNext()) {
				String[] line = s.nextLine().split(" ");

				userName.add(line[0]);
				passWord.add(line[1]);
				jobType.add(line[2]);
				userDepot.add(line[3]);
			}
			s.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void loadTruckDetails() {
		
		try {
			
			Scanner S = new Scanner(Depot.class.getResourceAsStream("/txt/Trucks.txt"));
			
			while(S.hasNext()) {
				
				String[] line = S.nextLine().split(" ");
				
				truckDepot.add(line[0]);
				truckMake.add(line[1]);
				truckModel.add(line[2]);
				truckWeight.add(Integer.parseInt(line[3]));
				truckRegNo.add(line[4]);
				truckCargoCapacity.add(Integer.parseInt(line[5]));
				
			}
			
			S.close();
			
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
		
	}
	
	public void loadTankerDetails() {
		
		try {
			
			Scanner S = new Scanner(Depot.class.getResourceAsStream("/txt/Tankers.txt"));
			
			while(S.hasNext()) {
				
				String[] line = S.nextLine().split(" ");
				
				tankerDepot.add(line[0]);
				tankerMake.add(line[1]);
				tankerModel.add(line[2]);
				tankerWeight.add(Integer.parseInt(line[3]));
				tankerRegNo.add(line[4]);
				tankerLiquidType.add(line[5]);
				tankerLiquidCapacity.add(Integer.parseInt(line[6]));
				
			}
			
			S.close();
			
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
		
	}
	
	public void loadScheduleDetails() {
		
		try {
			
			Scanner S = new Scanner(Depot.class.getResourceAsStream("/txt/workSchedule.txt"));
			
			int x = 0;
			
			while(S.hasNext()) {
				
				String[] line = S.nextLine().split(" ");
				
				vehicleName.add(line[0]);
				driverUsername.add(line[1]);
				clientName.add(line[2]);
				
				SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy"); 
				Date tempStartDate = formatter1.parse(line[3]);
				Date tempEndDate = formatter1.parse(line[4]);
				
				startDate.add(tempStartDate);
				endDate.add(tempEndDate);

				if(endDate.get(x).after(new Date())) {
					
					isArchived.add(x, true);
					
				} else {
					
					isArchived.add(x, false);
				}
				x++;
			}
			S.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		
	}

	public synchronized Vehicle getVehicle(String regNo) {
		
		try {//Find vehicle information based on registration number
			
			if(truckRegNo.contains(regNo)) {
				
				int index = truckRegNo.indexOf(regNo);
				
				System.out.println("\n-----------------------");
				System.out.println("Truck Details           |");
				System.out.println("-----------------------");
				System.out.println("Make: " + truckMake.get(index));
				System.out.println("Model: " + truckModel.get(index));
				System.out.println("Weight: " + truckWeight.get(index));
				System.out.println("Registration Number: " + truckRegNo.get(index));
				System.out.println("Cargo Capacity: " + truckCargoCapacity.get(index));
				
			} else if (tankerRegNo.contains(regNo)) {
				
				int index = tankerRegNo.indexOf(regNo);
				
				System.out.println("\n----------------------------");
				System.out.println("     Tanker Details         |");
				System.out.println("----------------------------");
				System.out.println("Make: " + tankerMake.get(index));
				System.out.println("Model: " + tankerModel.get(index));
				System.out.println("Weight: " + tankerWeight.get(index));
				System.out.println("Registration Number: " + tankerRegNo.get(index));
				System.out.println("Liquid Type: " + tankerLiquidType.get(index));
				System.out.println("Liquid Capacity: " + tankerLiquidCapacity.get(index));
				
				Thread.sleep(500);
				
			} else {
				
				System.out.println("Sorry, no vehicle found with that registration number");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e);
		}
		
		return null;
	}

	public Driver getDriver() {
		return null;
	}
	
	public synchronized void viewWorkschdule(String driver){
		
		try { //Tries to load the work schedule based on the current username
			// NOTE: the variable names get printed
			
			
			int index = driverUsername.indexOf(driver);
			System.out.println("Displaying schedule for user: " + driver);
			System.out.println("---------------------------------------");
			System.out.println("Vehicle - " + vehicleName.get(index));
			System.out.println("clientName - " + clientName.get(index));
			System.out.println("Start Date - " + startDate.get(index));
			System.out.println("End Date - " + endDate.get(index));
			
			Thread.sleep(500);
			
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("No driver found with that username");
			
		}
	}
	
	 //Truck object will be made using the method written in eDepotMain called addTruck.
	 //addTruck in Depot class will take in a truck as its parameter. This will add the truck details to the current records.

	public void addTruck(Truck truck) {
		
		try {
			
			truckMake.add(truck.Make);
			truckModel.add(truck.Model);
			truckWeight.add(truck.Weight);
			truckRegNo.add(truck.regNo);
			truckCargoCapacity.add(truck.getCargoCapacity());
			
			System.out.println("Truck added.");
			
		} catch(Exception e) {
			
			System.out.println("Please make sure that you have entered a valid truck");
			
		}
		
	}
	
	

	 //Tanker object will be made using the method written in eDepotMain called addTanker.
	 //addTanker in Depot class will take in a tanker as its parameter. This will add the tanker details to the current records.
	public void addTanker(Tanker tanker) {
		
		try {
			
			tankerMake.add(tanker.Make);
			tankerModel.add(tanker.Model);
			tankerWeight.add(tanker.Weight);
			tankerRegNo.add(tanker.regNo);
			tankerLiquidType.add(tanker.getLiquidType());
			tankerLiquidCapacity.add(tanker.getLiquidCapacity());
			
			System.out.println("Tanker added");
			
		} catch(Exception e) {
			
			System.out.println("Please make sure that you have entered a valid tanker.");
		
		}

	}
	
	public void addSchedule(WorkSchedule Schedule) {
		
		try {
			
			vehicleName.add(Schedule.getVehicleName());
			driverUsername.add(Schedule.getDriverUsername());
			clientName.add(Schedule.getClientName());
			startDate.add(Schedule.getStartDate());
			endDate.add(Schedule.getEndDate());
			
		} catch (Exception e) {
			
			System.out.print(e);
		}
		
	}
	
	public boolean searchDrivers(String driverUsername) {
		
		boolean driverExist = false;
		
		if(userName.contains(driverUsername)) {
			driverExist = true;
		}
		
		return driverExist;
	}
	
	public boolean searchTruck(String regNo) {
		
		boolean truckExist = false;

			if(truckRegNo.contains(regNo)) {
				truckExist = true;
			}
			
		return truckExist;
		
	}
	
	public boolean searchTanker(String regNo) {
		
		boolean tankerExist = false;
		
		if(tankerRegNo.contains(regNo)) {
			tankerExist = true;
		}
		
		return tankerExist;
		
	}
	
	public boolean searchVehicle(String regNo) {
		
		boolean vehicleExist = false;
		
		if(searchTruck(regNo) || searchTanker(regNo)) {
			
			vehicleExist = true;
			
		}
		
		return vehicleExist;
	}
	
	public void transferVehicle(String vehicleRegNo,  String newDepot) {
		
		if(searchTruck(vehicleRegNo)) {
			
			try {
				
				int index = truckRegNo.indexOf(vehicleRegNo);
				truckDepot.add(index, newDepot);
				System.out.println("Vehicle transferred.");
				
			} catch (Exception e) {
				
				System.err.println("No vehicle found with that registration number.");
			}
			
		} else if(searchTanker(vehicleRegNo)) {
			
			try {
				
				int index = tankerRegNo.indexOf(vehicleRegNo);
				tankerDepot.add(index, newDepot);
				System.out.println("Vehicle transferred.");
				
			} catch (Exception e) {
				
				System.err.println("No vehicle found with that registration number.");
			}
			
		}
		
	}
	
	private void saveTrucks(){
		
		try {
			
			PrintWriter pw = new PrintWriter(new File("src/txt/Trucks.txt"));
			
			for(int x = 0; x < truckMake.size(); x++) {
				
				String a = truckDepot.get(x);
				String b = truckMake.get(x);
				String c = truckModel.get(x);
				String d = Integer.toString(truckWeight.get(x));
				String e = truckRegNo.get(x);
				String f = Integer.toString(truckCargoCapacity.get(x));
				
				pw.println(a + " " + b + " " + c + " " + d + " " + e + " " + f);
				
			}
			
			pw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void saveTankers() {
		
		try {
			
			PrintWriter pw = new PrintWriter(new File("src/txt/Tankers.txt"));
			
			for(int x = 0; x < tankerMake.size(); x++) {
				
				String a = tankerDepot.get(x);
				String b = tankerMake.get(x);
				String c = tankerModel.get(x);
				String d = Integer.toString(tankerWeight.get(x));
				String e = tankerRegNo.get(x);
				String f = tankerLiquidType.get(x);
				String g = Integer.toString(tankerLiquidCapacity.get(x));
				
				pw.println(a + " " + b + " " + c + " " + d + " " + e + " " + f + " " + g);
				
			}
			
			pw.close();
			
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
		
	}
	
	private void saveSchedule() {
		
		try {
			
			PrintWriter pw = new PrintWriter(new File("src/txt/workSchedule.txt"));
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
			for (int x = 0; x < vehicleName.size(); x++) {
				
				String a = vehicleName.get(x);
				String b = driverUsername.get(x);
				String c = clientName.get(x);
				String d = df.format(startDate.get(x));
				String e = df.format(endDate.get(x));
				String f = Boolean.toString(isArchived.get(x));
				
				pw.println(a + " " + b + " " + c + " " + d + " " + e + " " + f);
				
			}
			
			pw.close();
		} catch(Exception e) {
			
			System.err.println("Unable to add work schedule");
		}
		
	}
	
	public void saveAllChanges(){
		
		saveTrucks();
		saveTankers();
		saveSchedule();
		
	}
	
}
