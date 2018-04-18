package eDepot;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Depot {

	private static ArrayList<String> userName = new ArrayList<>();
	private static ArrayList<String> passWord = new ArrayList<>();
	private static ArrayList<String> jobType = new ArrayList<>();
	
	private ArrayList<String> truckMake = new ArrayList<>();
	private ArrayList<String> truckModel = new ArrayList<>();
	private ArrayList<Integer> truckWeight = new ArrayList<>();
	private ArrayList<String> truckRegNo = new ArrayList<>();
	private ArrayList<Integer> truckCargoCapacity = new ArrayList<>();
	
	private ArrayList<String> tankerMake = new ArrayList<>();
	private ArrayList<String> tankerModel = new ArrayList<>();
	private ArrayList<Integer> tankerWeight = new ArrayList<>();
	private ArrayList<String> tankerRegNo = new ArrayList<>();
	private ArrayList<String> tankerLiquidType = new ArrayList<>();
	private ArrayList<Integer> tankerLiquidCapacity = new ArrayList<>();
	
	private ArrayList<String> vehicleName = new ArrayList<>(); 
	private ArrayList<String> driverUsername = new ArrayList<>();
	private ArrayList<String> clientName = new ArrayList<>();
	private ArrayList<Date> startDate = new ArrayList<>();
	private ArrayList<Date> endDate = new ArrayList<>();
	

	public Depot() {
		
		loadUserDetails();
		loadTruckDetails();
		loadTankerDetails();
		loadScheduleDetails();
		
	}

	public void logOn(String user, String pass) {
		// gets input from uses
		if (userName.contains(user)) {
			
			int index = userName.indexOf(user);
			
			if(passWord.get(index).equals(pass)){
				
				System.out.print("Welcome ");
				checkUserJobType(index);
				
			}else {
				
				System.err.print("Sorry username does not match our records!");
				
			}
		} 
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
				
				truckMake.add(line[0]);
				truckModel.add(line[1]);
				truckWeight.add(Integer.parseInt(line[2]));
				truckRegNo.add(line[3]);
				truckCargoCapacity.add(Integer.parseInt(line[4]));
				
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
				
				tankerMake.add(line[0]);
				tankerModel.add(line[1]);
				tankerWeight.add(Integer.parseInt(line[2]));
				tankerRegNo.add(line[3]);
				tankerLiquidType.add(line[4]);
				tankerLiquidCapacity.add(Integer.parseInt(line[5]));
				
			}
			
			S.close();
			
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
		
	}
	
	public void loadScheduleDetails() {
		
		try {
			
			Scanner S = new Scanner(Depot.class.getResourceAsStream("/txt/workSchedule.txt"));
			
			while(S.hasNext()) {
				
				String[] line = S.nextLine().split(" ");
				
				vehicleName.add(line[0]);
				driverUsername.add(line[1]);
				clientName.add(line[2]);
				startDate.add(new SimpleDateFormat("dd/MM/yyyy").parse(line[3]));
				endDate.add(new SimpleDateFormat("dd/MM/yyyy").parse(line[4]));
				
			}
			S.close();
		} catch (Exception e) {
			System.out.print(e);
		}
		
	}

	public Vehicle getVehicle(String regNo) {
		
		try {
			
			if(truckRegNo.contains(regNo)) {
				
				int index = truckRegNo.indexOf(regNo);
				
				System.out.println("\n-------------");
				System.out.println("Truck Details");
				System.out.println("-------------");
				System.out.println("Make: " + truckMake.get(index));
				System.out.println("Model: " + truckModel.get(index));
				System.out.println("Weight: " + truckWeight.get(index));
				System.out.println("Registration Number: " + truckRegNo.get(index));
				System.out.println("Cargo Capacity: " + truckCargoCapacity.get(index));
				
			} else if (tankerRegNo.contains(regNo)) {
				
				int index = tankerRegNo.indexOf(regNo);
				
				System.out.println("\n-------------");
				System.out.println("Tanker Details");
				System.out.println("-------------");
				System.out.println("Make: " + tankerMake.get(index));
				System.out.println("Model: " + tankerModel.get(index));
				System.out.println("Weight: " + tankerWeight.get(index));
				System.out.println("Registration Number: " + tankerRegNo.get(index));
				System.out.println("Liquid Type: " + tankerLiquidType.get(index));
				System.out.println("Liquid Capacity: " + tankerLiquidCapacity.get(index));
				
			} else {
				
				System.out.println("Sorry, no vehicle found with that registration number");
				
			}
			
		} catch (Exception e) {
			System.out.print(e);
		}
		
		return null;
	}

	public Driver getDriver() {
		return null;
	}
	
	public static void viewWorkschdule(){
		
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
	
	private void saveTrucks(){
		
		try {
			
			PrintWriter pw = new PrintWriter(new File("src/txt/Trucks.txt"));
			
			for(int x = 0; x < truckMake.size(); x++) {
				
				String a = truckMake.get(x);
				String b = truckModel.get(x);
				String c = Integer.toString(truckWeight.get(x));
				String d = truckRegNo.get(x);
				String e = Integer.toString(truckCargoCapacity.get(x));
				
				pw.println(a + " " + b + " " + c + " " + d + " " + e);
				
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
				
				String a = tankerMake.get(x);
				String b = tankerModel.get(x);
				String c = Integer.toString(tankerWeight.get(x));
				String d = tankerRegNo.get(x);
				String e = tankerLiquidType.get(x);
				String f = Integer.toString(tankerLiquidCapacity.get(x));
				
				pw.println(a + " " + b + " " + c + " " + d + " " + e + " " + f);
				
			}
			
			pw.close();
			
		} catch (Exception e) {
			
			System.out.println(e);
			
		}
		
	}
	
	private void saveSchedule() {
		
		try {
			
			PrintWriter pw = new PrintWriter(new File("src/txt/workSchedule.txt"));
			
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			
			for (int x = 0; x < vehicleName.size(); x++) {
				
				String a = vehicleName.get(x);
				String b = driverUsername.get(x);
				String c = clientName.get(x);
				String d = df.format(startDate.get(x));
				String e = df.format(endDate.get(x));
				
				pw.println(a + " " + b + " " + c + " " + d + " " + e);
				
			}
			
			pw.close();
		} catch(Exception e) {
			
			System.err.print(e);
		}
		
	}
	
	public void saveAllChanges(){
		
		saveTrucks();
		saveTankers();
		saveSchedule();
		
	}
	
}
