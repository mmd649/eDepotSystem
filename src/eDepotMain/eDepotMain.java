package eDepotMain;
/**
 * Menu system for interfacing with eDepot.
 * 
 * "Driver" and "Manager" each have separate menu options.
 * These are accessed by first; logging in, depending on "userType" they will be redirected accordingly.
 * 
 * Manager has access to:      User has access to:
 * [V]iew work schedule        [V]iew work schedule
 * [S]et up Work Schedule      [G]et Vehicle Details
 * [A]dd Vehicle
 * [R]e-Assign Vehicle
 * [G]et Vehicle Details
 * [N]ew Driver
 * 
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import eDepot.Depot;
import eDepot.Tanker;
import eDepot.Truck;
import eDepot.WorkSchedule;

public class eDepotMain {
	
	private final static Scanner S = new Scanner(System.in);
	
	private static String userName, passWord, choice = "";
	
	static Depot depotObject = new Depot();
	
	public static void main(String[] args){
		
		do {
			System.out.println("\n __________________________");
			System.out.println("|     eDepot Main Menu     |");
			System.out.println("|--------------------------|");
			System.out.println("|1 - Login                 |");
			System.out.println("|Q - Quit                  |");
			System.out.println(" --------------------------");
			System.out.print("\nPick: ");
			
			choice = S.next().toUpperCase();
			
			switch(choice) {
			
				case "1":
				case "V":{
					
					System.out.print("\nPlease enter your username: ");
					userName = S.next();
					System.out.print("Please enter your password: ");
					passWord = S.next();
					
					depotObject.logOn(userName, passWord);
					int index = 0;
					
					if(depotObject.checkUserJobType(index).toString()=="true"){
						Menu("manager");
					}else{
						Menu("driver");
					}
					
					break;
					
				}

			}
			
		} while(!choice.equalsIgnoreCase("Q"));
		
		System.out.print("Application Closed.");
		depotObject.saveAllChanges();
		S.close();
	}
	
	public static void Menu(String userType){
		
		String choice="";
		
		if (userType.equalsIgnoreCase("Manager")){
			
			do {
				System.out.println("\n __________________________");
				System.out.println("|     eDepot manager Menu  |");
				System.out.println("|--------------------------|");
				System.out.println("|1 - [V]iew work schedule  |");
				System.out.println("|2 - [S]et up Work Schedule|");
				System.out.println("|3 - [A]dd Vehicle         |");
				System.out.println("|4 - [R]e-Assign Vehicle   |");
				System.out.println("|5 - [G]et Vehicle Details |");
				System.out.println("|6 - [N]ew Driver          |");
				System.out.println("|L - Logout                |");
				System.out.println(" --------------------------");
				System.out.print("\nPick: ");
			
			choice = S.next().toUpperCase();
			
			switch(choice) {
			
			case "1":
			case "V":{
				depotObject.viewWorkschdule(userName);
				break;
				
			}
									
				case "2":
				case "S":{
					
					depotObject.addSchedule(newSchedule());
					
					break;
					
				}
				
				case "3":
				case "A":{
					
					System.out.println("What type of vehicle do you want to add?: [Truck|Tanker] ");
					String vehicleType = S.next();
					
					if(vehicleType.equalsIgnoreCase("Truck")) {
						
						depotObject.addTruck(newTruck());

					} else if(vehicleType.equalsIgnoreCase("Tanker")) {
						
						depotObject.addTanker(newTanker());
						
					} else {
						System.out.println("Invalid vehicle type");
					}
					
					break;
					
				}
				
				case "4":
				case "R":{
					
					break;
				}
				
				case "5":
				case "G":{
					System.out.println("Please enter the vehicle's registration number: ");
					depotObject.getVehicle(S.next().toUpperCase());
				}
				
				case "6":
				case "N":{
                //Add New Driver
				}
			}
			
		} while(!choice.equalsIgnoreCase("L"));
		
	}else if (userType.equalsIgnoreCase("Driver")){
		do {
			System.out.println("\n __________________________");
			System.out.println("|     eDepot driver  Menu  |");
			System.out.println("|--------------------------|");
			System.out.println("|1 - [V]iew work schedule  |");
			System.out.println("|2 - [G]et Vehicle Details |");
			System.out.println("|L - Logout                |");
			System.out.println(" --------------------------");
			System.out.print("\nPick: ");
			
			choice = S.next().toUpperCase();
			
			switch(choice) {
			
			case "1":
			case "V":{
				depotObject.viewWorkschdule(userName);
				break;
				
			}
								
				case "2":
				case "G":{
					System.out.println("Please enter the vehicle's registration number: ");
					depotObject.getVehicle(S.next().toUpperCase());
				}
			}
			
		} while(!choice.equalsIgnoreCase("L"));
		
		}
	}
	

//	 Basic add vehicles (Truck or Tanker) with no error checking. Will be improved later on.
	
	public static Truck newTruck() {
		
		String Make, Model, regNo;
		Integer Weight, cargoCapacity;
		
		System.out.print("Please type the vehicle make: ");
		Make = S.next();
		
		System.out.print("\nPlease type the vehicle model: ");
		Model = S.next();
		
		System.out.print("\nPlease type the vehicle weight(KG): ");
		Weight = S.nextInt();
		
		System.out.print("\nPlease type the vehicle registration number: ");
		//ASSUMPUTION - Only UK REGISTRATION NUMBERS validation two upper case characters followed by two numbers followed by three upper case characters
		String regRegex ="(?<Current>^[A-Z]{2}[0-9]{2}[A-Z]{3}$)|(?<Prefix>^[A-Z][0-9]{1,3}[A-Z]{3}$)|(?<Suffix>^[A-Z]{3}[0-9]{1,3}[A-Z]$)|(?<DatelessLongNumberPrefix>^[0-9]{1,4}[A-Z]{1,2}$)|(?<DatelessShortNumberPrefix>^[0-9]{1,3}[A-Z]{1,3}$)|(?<DatelessLongNumberSuffix>^[A-Z]{1,2}[0-9]{1,4}$)|(?<DatelessShortNumberSufix>^[A-Z]{1,3}[0-9]{1,3}$)";
		Boolean inputValid = true;
			do {				
				System.out.print("\nPlease enter the end date of the schedule(dd/mm/yyyy): ");
				regNo = S.next();			
				Boolean validReg = regNo.matches(regRegex);		
				if(validReg) {
					inputValid=false;
				} else {
					
					System.err.print("\nInvalid vehicle registration number- please make sure reg number is upper case");		
				}
			} while(inputValid);
			
		
			
		System.out.println("\nPlease type the maximum cargo capacity: ");
		cargoCapacity = S.nextInt();
		
		Truck newTruck = new Truck(Make, Model, Weight, regNo, cargoCapacity);
		
		return newTruck;
		
	}
	
	public static Tanker newTanker() {
		
		String Make, Model, regNo, liquidType;
		Integer Weight, liquidCapacity;
		
		System.out.print("Please type the vehicle make: ");
		Make = S.next();
		
		System.out.print("\nPlease type the vehicle model: ");
		Model = S.next();
		
		System.out.print("\nPlease type the vehicle weight(KG): ");
		Weight = S.nextInt();
		
		System.out.print("\nPlease type the vehicle registration number: ");
		//ASSUMPUTION - Only UK REGISTRATION NUMBERS validation two upper case characters followed by two numbers followed by three upper case characters
		String regRegex ="(?<Current>^[A-Z]{2}[0-9]{2}[A-Z]{3}$)|(?<Prefix>^[A-Z][0-9]{1,3}[A-Z]{3}$)|(?<Suffix>^[A-Z]{3}[0-9]{1,3}[A-Z]$)|(?<DatelessLongNumberPrefix>^[0-9]{1,4}[A-Z]{1,2}$)|(?<DatelessShortNumberPrefix>^[0-9]{1,3}[A-Z]{1,3}$)|(?<DatelessLongNumberSuffix>^[A-Z]{1,2}[0-9]{1,4}$)|(?<DatelessShortNumberSufix>^[A-Z]{1,3}[0-9]{1,3}$)";
		Boolean inputValid = true;
			do {				
				System.out.print("\nPlease enter the end date of the schedule(dd/mm/yyyy): ");
				regNo = S.next();			
				Boolean validReg = regNo.matches(regRegex);		
				if(validReg) {
					inputValid=false;
				} else {
					
					System.err.print("\nInvalid vehicle registration number- please make sure reg number is upper case");		
				}
			} while(inputValid);
			
		
		System.out.print("\nPlease type the vehicle liquid type: ");
		liquidType = S.next();
		
		System.out.println("\nPlease type the maximum cargo capacity: ");
		liquidCapacity = S.nextInt();
		
		Tanker newTanker = new Tanker(Make, Model, Weight, regNo, liquidType, liquidCapacity);
		
		return newTanker;
		
	}
	
//	 Basic add work schedule 
	
	public static WorkSchedule newSchedule() {		
		
		String vehicleRegNo, driverUsername, clientName;
		Date startDate = null, endDate = null;
		
		//Checks if registration number exists in the system
		boolean valid=false;

	do{
			System.out.print("\nPlease enter the vehicle registration number: ");
			vehicleRegNo = S.next();			
			File file = new File("src/txt/Trucks.txt");
			try {
			    Scanner scanner = new Scanner(file);
			    int lineNo = 0;
			    while (scanner.hasNextLine()) {
			        String line = scanner.nextLine();
			        lineNo++;
			        if(line.contains(vehicleRegNo)) { 			        	
			            valid=true; //Vehicle does exist in the system
			        }
			    }
			} catch(FileNotFoundException e) { 
			}
	}while(!valid);

		
		System.out.print("\nPlease enter the driver's username: ");
		driverUsername = S.next();

		
		System.out.print("\nPlease enter Client name: ");
		clientName = S.next();
		
		boolean inputValid = false;
		String dateRegex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$"; 
		
		do {
			
			System.out.print("\nPlease enter the start date of the schedule(dd/mm/yyyy): ");
			String temp = S.next();
			
			Boolean validDate = temp.matches(dateRegex);
			
			if(validDate) {
				
				DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
				
				try {
					
					startDate = sourceFormat.parse(temp);
					inputValid = true;
					
				} catch (Exception e) {
					
					System.out.println(e);
				}

				
			} else {
				
				System.err.print("\nInvalid start date");
				
			}
			
		} while(!inputValid);
		
		do {
			
			System.out.print("\nPlease enter the end date of the schedule(dd/mm/yyyy): ");
			String temp = S.next();
			
			Boolean validDate = temp.matches(dateRegex);
			
			if(validDate) {
				
				DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
				
				try {
					
					endDate = sourceFormat.parse(temp);
					if (endDate.before(startDate)){
						System.err.println("End date can't be before start date");
					}else{
					inputValid = false;					
					}
				} catch (Exception e) {
					
					System.out.println(e);

				}
				
			} else {
				
				System.err.print("\nInvalid end date");
				
			}
			
		} while(inputValid);
		
		WorkSchedule newSchedule = new WorkSchedule(vehicleRegNo, driverUsername, clientName, startDate, endDate);
		System.out.println("New work schedule added");
		System.out.printf("%s %s", startDate, endDate);
	
		return newSchedule;
		
	}
	
}
