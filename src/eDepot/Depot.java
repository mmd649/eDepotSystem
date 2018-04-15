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

	public Depot() {
		loadUsers();
	}

	public static void logOn(String user, String pass) {
		// gets input from uses
		if (userName.contains(user) == true && passWord.contains(pass) == true) {
			System.out.print("Welcome ");
			Depot.checkUserJobType(user, pass);
		} else {
			System.err.print("Sorry username does not match our records!");
		}
	}
	public static void checkUserJobType(String driver, String manager){
		driver = "driver";
		manager = "manager";
		
		// checks if user is manager or driver.
		if(jobType.contains(manager)){
			setupWorkSchedule();
		}else{
			viewWorkschdule();
		}
	}
	public void loadUsers() {
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
}
