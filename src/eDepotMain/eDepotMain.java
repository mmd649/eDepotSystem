package eDepotMain;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import eDepot.Depot;

public class eDepotMain {
	
	private final static Scanner S = new Scanner(System.in);
	
	static Depot depotOBJ = new Depot();
	
	public static void main(String[] args) {
		
		String userName;
		String passWord;
		
	
		String choice = "";
		
		do {
			
			System.out.println("\n __________________________");
			System.out.println("|     eDepot Main Menu     |");
			System.out.println("|--------------------------|");
			System.out.println("|1 - [V]iew Work Schedule  |");
			System.out.println("|2 - [S]et up Work Schedule|");
			System.out.println("|3 - [A]dd Vehicle         |");
			System.out.println("|4 - [R]e-Assign Vehicle   |");
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
					
					Depot.logOn(userName, passWord);
					//depotOBJ.logOn(userName, passWord);
					
					
					break;
					
				}
				
				case "2":
				case "A":{
					
					break;
					
				}
				
				case "3":
				case "R":{
					
					break;
					
				}
			}
			
		} while(!choice.equalsIgnoreCase("Q"));
		
		System.out.print("Application Closed.");
		S.close();
	}
	
	
	
	

}
