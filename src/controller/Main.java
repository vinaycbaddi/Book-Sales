package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.ConnectionEvent;

import utility.ConnectionManager;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		AdminController admin = new AdminController();
		CustomerController customer = new CustomerController();
		String username;
		String pass;
		boolean status = true;

		do {
			System.out.println("____________________________________________");
			System.out.println("");
			System.out.println("1.Customer");
			System.out.println("2.Admin");
			System.out.println("3.Exit");
			System.out.println("____________________________________________");
			System.out.println("");
			System.out.println("Enter your choice : ");
			System.out.println("____________________________________________");
			int choice = in.nextInt();
			switch (choice) {
			
//			Calling customer method to access customer section
			case 1:
				customer.customer();
				break;
				
//			Calling Admin method to access Admin section after validation
			case 2:
				do {
					System.out.println("____________________________________________");
					System.out.println("");
					System.out.println("Enter username :");
					username = in.next();
					System.out.println("Enter password :");
					pass = in.next();
					System.out.println("____________________________________________");
					if (username.equals("Admin") && pass.equals("Admin@1234")) {
						admin.admin();
						break;
					} else {
						System.out.println("____________________________________________");
						System.out.println("");
						System.out.println("Enter valid input");
					}
				} while (status);
				break;
				
//			Terminate the program
			case 3:
				System.out.println("Thank you vist again");
				System.exit(0);
				break;
			}
		} while (true);
	}

}
