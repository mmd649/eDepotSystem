package eDepot;

import java.util.ArrayList;
import java.util.Scanner;

public class Depot {
	
	private ArrayList<String> userName = new ArrayList<>();
	private ArrayList<String> passWord = new ArrayList<>();
	private ArrayList<String> jobType = new ArrayList<>();
	
	public Depot(){
		
		loadUsers();
	}
	
	public void logOn(String user, String pass) {
		// gets input from uses
		if(userName.contains(user) == true && passWord.contains(pass) == true){
			System.out.print("Welcome ");
		}else{
			System.out.print("Sorry username does not match or records!");
		}
	}
	
		public void loadUsers() {
			try {
				Scanner s = new Scanner(Depot.class.getResourceAsStream("/txt/users.txt"));
				
				while(s.hasNext()){
					String[] line = s.nextLine().split(" ");
					
					userName.add(line[0]);
					passWord.add(line[1]);
					jobType.add(line[2]);
				}
			} catch (Exception e){
				System.out.println(e);
			}
		}
		
	public Vehicle getVehicle() {
		return null;
	}
	
	public Driver getDriver() {
		return null;
	}
	
	public void setupWorkSchedule() {
		
	}

}
