package eDepotMain;

import java.util.Scanner;

public class eDepotMain {
	
	private final static Scanner S = new Scanner(System.in);
	
	public void main(String[] args) {
		
		String userName;
		String passWord;
		
		String choice = "";
		
		do {
			
			System.out.println("\n----------------------------");
			System.out.println("|     eDepot Main Menu     |");
			System.out.println("----------------------------");
			System.out.println("1 - [V]iew Work Schedule");
			System.out.println("2 - [A]ssign Work Schedule");
			System.out.println("3 - [R]e-Assign Vehicle");
			System.out.println("Q - Quit");
			System.out.print("\nPick: ");
			
			switch(choice) {
			
				case "1":
				case "V":{
					
					System.out.print("\nPlease enter your username: ");
					userName = S.next();
					System.out.print("Please enter your password: ");
					passWord = S.next();
					
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
		
		S.close();
	}
	

}
