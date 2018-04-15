package eDepot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
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

	public Depot() {
		
		loadUserDetails();
		loadTruckDetails();
		loadTankerDetails();
		
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

	
	public static Boolean checkUserJobType(int index){
		
		Boolean verifyJob;
		// checks if user is manager or driver.
		if(jobType.get(index).equals("manager")){
			verifyJob = true;
			Scanner scan = new Scanner(System.in);
			String choice = "";
			do {
				
				System.out.println("Username: " + userName.get(index));
				System.out.println("Job Title: " + jobType.get(index));
				System.out.println("1 - [V]iew Work Schedule  ");
				System.out.println("2 - [S]et up Work Schedule");
				System.out.println("3 - [A]dd Vehicle         ");
				System.out.println("4 - [R]e-Assign Vehicle   ");
				System.out.println("Q - Logout                ");
				System.out.println(" --------------------------");
				System.out.print("\nPick: ");
				
				choice = scan.next().toUpperCase();
				
				switch(choice) {
				
					case "1":
					case "v":{
						viewWorkschdule();
						break;
						
					}
					
					case "2":
					case "s":{
						setupWorkSchedule();
						break;
						
					}
				}
				
			} while(!choice.equalsIgnoreCase("Q"));
			
			System.out.print("Logged Out.");
			scan.close();
		}else{
			verifyJob = false;
			
			System.out.println("\nUsername: " + userName.get(index));
			System.out.println("Job Title: " + jobType.get(index));
			viewWorkschdule();
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

	public Vehicle getVehicle() {
		return null;
	}

	public Driver getDriver() {
		return null;
	}
	
	public static void viewWorkschdule(){
		
	}

	public static void setupWorkSchedule() {
		
		String Client;
		String input;
		Scanner read = new Scanner(System.in);

		System.out.print("\nPlease enter Client name: ");
		Client = read.next();
		System.out.print("Please enter Start Date[dd/mm/yyyy]: ");
		input = read.next();
		
		// checks if date is real date
		boolean inputValid = false;
		String dateRegex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$"; 
		
		do {
			
			Boolean validDate = input.matches(dateRegex);
			
			if (validDate == false) {
				
				System.err.print("\nInvalid start date");
				System.out.println("\nStart Date[dd/mm/yyyy]:  ");
				input = read.nextLine();
				
			} else {
				// converts string to date
				DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date startDate = null;
				inputValid=true;
				
				try {
					
					startDate = sourceFormat.parse(input);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} while (inputValid == false);
		
		do {
			
			System.out.print("Please enter End Date[dd/mm/yyyy]: ");
			input = read.next();
			Boolean validDate = input.matches(dateRegex);
			
			if (validDate == false) {
				
				System.err.print("\nInvalid start date");
				System.out.println("\nStart Date[dd/mm/yyyy]:  ");
				input = read.nextLine();
				
			} else {
				// converts string to date
				DateFormat sourceFormat = new SimpleDateFormat("dd/mm/yyyy");
				Date endDate = null;
				inputValid=false;
				try {
					
					endDate = sourceFormat.parse(input);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} while (inputValid == true);
		
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader("txt/workSchedule.txt"));
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		PrintWriter writer = null;
		
		try {
			
			writer = new PrintWriter(new File("txt/workSchedule.txt"), "UTF-8");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line;
		
		try {
			
			while ((line = file.readLine()) != null) {
				
				writer.println(Client);
				
			}
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			
			file.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (writer.checkError()){
			
			try {
				
				throw new IOException("Unable to write to file");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		writer.close();

	}
	
	/**
	 * Truck object will be made using the method written in eDepotMain called addTruck.
	 * addTruck in Depot class will take in a truck as its parameter. This will add the truck details to the current records.
	 * @param truck
	 */
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
	
	
	/**
	 * Tanker object will be made using the method written in eDepotMain called addTanker.
	 * addTanker in Depot class will take in a tanker as its parameter. This will add the tanker details to the current records.
	 * @param tanker
	 */
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
	
	public void saveTrucks() throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter("/txt/Trucks.txt");
		
		for(int x = 0; x < truckMake.size(); x++) {
			
			String a = truckMake.get(x);
			String b = truckModel.get(x);
			String c = Integer.toString(truckWeight.get(x));
			String d = truckRegNo.get(x);
			String e = Integer.toString(truckCargoCapacity.get(x));
			
			pw.println(a + " " + b + " " + c + " " + d + " " + e);
			
		}
		
		pw.close();
		
	}
	
	public void saveTankers() throws FileNotFoundException {
		
		PrintWriter pw = new PrintWriter("/txt/Trucks.txt");
		
		for(int x = 0; x < truckMake.size(); x++) {
			
			String a = tankerMake.get(x);
			String b = tankerModel.get(x);
			String c = Integer.toString(tankerWeight.get(x));
			String d = tankerRegNo.get(x);
			String e = tankerLiquidType.get(x);
			String f = Integer.toString(tankerLiquidCapacity.get(x));
			
			pw.println(a + " " + b + " " + c + " " + d + " " + e + " " + f);
			
		}
		
		pw.close();
		
	}
	
}
