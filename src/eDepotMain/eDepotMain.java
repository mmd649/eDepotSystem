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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import eDepot.Depot;
import eDepot.Tanker;
import eDepot.Truck;
import eDepot.WorkSchedule;

public class eDepotMain {

	static ExecutorService es = Executors.newFixedThreadPool(2);
	
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

				if(depotObject.logOn(userName, passWord)) {

					// if true, display manager menu
					if(depotObject.checkUserJobType(depotObject.getIndex(userName))) {

						Menu("Manager");

					} else {

						Menu("Driver");

					}

				} else {

					System.out.println("Login failed. Username entered was not found.");

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

				
		String choice = "";

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
				System.out.println("|6 - [E]dit Drivers        |");
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
						
						String vehicleRegNo = "", newDepot= "";
						boolean validReg = false;
						
						do {
						
							System.out.println("Please enter the vehicle's registration number: ");
							String tempRegNo = S.next();
							
							if(depotObject.searchVehicle(tempRegNo)) {
								
								tempRegNo = vehicleRegNo;
								validReg = true;
								
							}
							
						} while(!validReg);
						
						System.out.println("Please enter the depot you want this vehicle to be transffered to: ");
						newDepot = S.next();
						
						depotObject.transferVehicle(vehicleRegNo, newDepot);
						
						break;
					}
	
					case "5":
					case "G":{
						
						System.out.println("Please enter the vehicle's registration number: ");
						depotObject.getVehicle(S.next().toUpperCase());
						break;
					}
	
					case "6":
					case "E":{
						editDriver();
						break;
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
					
/*					Runnable r1 = new Runnable(){
						public void run() {
	
						}
					};
					es.submit(r1);*/
					break;
					}


				case "2":
				case "G":{
					
					System.out.println("Please enter the vehicle's registration number: ");
					depotObject.getVehicle(S.next().toUpperCase());
					
/*					Runnable r2 = new Runnable(){
						public void run() {
					
					
						}
					};
					es.submit(r2);*/
					}
				}

			} while(!choice.equalsIgnoreCase("L"));

		}
		
//		es.shutdown(); // method shutDown will thusly gracefully wait
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



		System.out.println("\nPlease type the maximum cargo capacity(KG): ");
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

	public static void editDriver(){
		
		do {
			System.out.println("\n __________________________");
			System.out.println("|     eDepot driver  Menu  |");
			System.out.println("|--------------------------|");
			System.out.println("|1 - [A]dd new driver      |");
			System.out.println("|2 - [D]elete driver       |");
			System.out.println("|B - Back                  |");
			System.out.println(" --------------------------");
			System.out.print("\nPick: ");

			choice = S.next().toUpperCase();

			switch(choice) {

				case "1":
				case "A":{
					addNewDriver();
					break;
				}
	
	
				case "2":
				case "D":{
					break;
				}
			}

		} while(!choice.equalsIgnoreCase("B"));
	}
	public static void addNewDriver() {	
		String userName, password, type,depotAssigned;
		//Checks if driver exists in the system; drivers cannot have the same user name
		boolean valid=false;
	
		do{
			
			System.out.println("\nPlease enter the user name: ");
			userName = S.next();			
			int found=0;
			
			File file = new File("src/txt/Users.txt");
			
			try {
				
				Scanner scanner = new Scanner(file);
				int lineNo = 0;
				
				while (scanner.hasNextLine()) {
					
					String line = scanner.nextLine();
					lineNo++;
					
					if(line.contains(userName)) { 	
						
						valid=false; //driver does exist in the system
						found=3;
						
					}
				}
				
				if (found==3){
					
					valid = false;
					
					System.err.println("Driver username already exists please enter another username");
				}else{
					
					valid=true;
					
				}
				
				scanner.close();
				
			} catch(FileNotFoundException e) { 
				
			}
		}while(!valid);
		
		System.out.print("\nPlease enter a password for account: ");
		password = S.next();
		
		valid = false;
		
		do{
			
			System.out.print("\nPlease specify account type [M]anager or [D]river:  ");
			type = S.next();
			
			if (type.equalsIgnoreCase("M")){
				
				type="manager";
				valid=true;
				
			}
			
			if (type.equalsIgnoreCase("D")){
				
				type="driver";
				valid=true;
				
			}
			
		}while(!valid);

		System.out.print("\nPlease specify driver depot is assigned to: ");
		depotAssigned = S.next();
		
		try(FileWriter fw = new FileWriter("src/txt/users.txt", true);
				
			BufferedWriter bw = new BufferedWriter(fw);			
			PrintWriter out = new PrintWriter(bw)) {		
			out.println("n "+ userName+" "+password+" "+type+" "+depotAssigned);
			System.out.println("\n----New user added-----");
			
		} catch (IOException e) {
			
			System.err.println("unable to add new user");
			
		}

		
	}
	
	//	 Basic add work schedule 

	public static WorkSchedule newSchedule() {		

		String vehicleRegNo, driverUsername, clientName;
		Date startDate = null, endDate = null;

		//Checks if registration number exists in the system
		boolean valid = false;

		do{
			
			System.out.print("\nPlease enter the vehicle registration number: ");
			vehicleRegNo = S.next();			
			
			if(depotObject.searchVehicle(vehicleRegNo)) {
				
				valid = true;
				
			}
			
		}while(!valid);


		System.out.print("\nPlease enter the driver's username: ");
		driverUsername = S.next();


		System.out.print("\nPlease enter Client name: ");
		clientName = S.next();

		boolean validStart = false, validEnd = false;
		String dateRegex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$"; 

		do {

			System.out.print("\nPlease enter the start date of the schedule(dd/mm/yyyy): ");
			String temp = S.next();

			Boolean validDate = temp.matches(dateRegex);

			if(validDate) {

				DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

				try {

					startDate = sourceFormat.parse(temp);
				LocalDateTime localDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		        localDateTime = localDateTime.plusHours(48);
			        Date twoDaysAfter = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
					
			        //checks if the start date is 48hours in advanced.
					if(startDate.after(twoDaysAfter)) {
						
						validStart = true;
					}

				} catch (Exception e) {

					System.out.println(e);
				}


			} else {

				System.err.print("\nInvalid start date");

			}

		} while(!validStart);

		do {

			System.out.print("\nPlease enter the end date of the schedule(dd/mm/yyyy): ");
			String temp = S.next();

			Boolean validDate = temp.matches(dateRegex);

			if(validDate) {

				DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");

				try {

					endDate = sourceFormat.parse(temp);
					
					LocalDateTime localDateTime = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			        localDateTime = localDateTime.plusHours(72);
			        Date threeDaysAfterStart = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
			        
			        //End date should be within 3 days of start date.
					if(endDate.after(new Date()) && endDate.before(threeDaysAfterStart)) {
						
						validEnd = true;
						
					} else if (endDate.before(startDate)){
						
						System.err.println("End date can't be before start date");
						
					}else{
						
						validEnd = false;					
					}
				} catch (Exception e) {

					System.out.println(e);

				}

			} else {

				System.err.print("\nInvalid end date");

			}

		} while(!validEnd);

		WorkSchedule newSchedule = new WorkSchedule(vehicleRegNo, driverUsername, clientName, startDate, endDate);
		System.out.println("New work schedule added");
		System.out.printf("%s %s", startDate, endDate);

		return newSchedule;

	}

}
