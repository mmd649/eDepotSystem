package eDepotMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import eDepot.Depot;
import eDepot.Tanker;
import eDepot.Truck;

public class eDepotMain {
	
	private final static Scanner S = new Scanner(System.in);
	
	static Depot depotObject = new Depot();
	
	public static void main(String[] args){
		
		String userName;
		String passWord;
		
	
		String choice = "";
		
		do {
			System.out.println("\n __________________________");
			System.out.println("|     eDepot Main Menu     |");
			System.out.println("|--------------------------|");
			System.out.println("|1 - Login                 |");
//			System.out.println("|2 - [S]et up Work Schedule|");
//			System.out.println("|3 - [A]dd Vehicle         |");
//			System.out.println("|4 - [R]e-Assign Vehicle   |");
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
					
					if(depotObject.checkUserJobType(index)){
						
					}else{
						
					}
					
					break;
					
				}
				
				case "2":
				case "S":{
					
					depotObject.setupWorkSchedule();
					
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
			}
			
		} while(!choice.equalsIgnoreCase("Q"));
		
		System.out.print("Application Closed.");
		depotObject.saveAllChanges();
		S.close();
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
		regNo = S.next();
		
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
		regNo = S.next();
		
		System.out.print("\nPlease type the vehicle liquid type: ");
		liquidType = S.next();
		
		System.out.println("\nPlease type the maximum cargo capacity: ");
		liquidCapacity = S.nextInt();
		
		Tanker newTanker = new Tanker(Make, Model, Weight, regNo, liquidType, liquidCapacity);
		
		return newTanker;
		
	}
	
}
