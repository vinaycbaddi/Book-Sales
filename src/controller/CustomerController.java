package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import businesslogic.CustomerRegistration;
import dao.CustomerDAO;
import model.Customer;
import utility.ConnectionManager;

public class CustomerController {
	
//	Method of customer section
	public void customer() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		CustomerRegistration custregister = new CustomerRegistration();
		CustomerDAO customerdao = new CustomerDAO();
		Customer cust = null;
		boolean status = true;
		
		do {
			System.out.println("____________________________________________");
			System.out.println("");
			System.out.println("1.New Customer");
			System.out.println("2.Existing Customer");
			System.out.println("3.Exit");
			System.out.println("____________________________________________");
			int choice = in.nextInt();
			switch (choice) {
			
//			New customer registration
			case 1:
				custregister.registerCustomer();
				break;
				
//			Existing customer registration 
			case 2:
				Connection con = ConnectionManager.getConnection();
				String sql = "SELECT * FROM CUSTOMER";
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery(sql);
				System.out.println("____________________________________________");
				System.out.println("");
				System.out.println("Enter the email :");
				String custEmail = in.next();
				System.out.println("Enter the password :");
				String custPassword = in.next();
				System.out.println("____________________________________________");
				while (rs.next()) {
//					Customer credential validation
					if (custEmail.equals(rs.getString(2)) && custPassword.equals(rs.getString(3))) {
						String name = rs.getString(1);
						String email = rs.getString(2);
						String password = rs.getString(3);
						String phone = rs.getString(4);
						cust = new Customer(name, email, password, phone);
						
//						go to customer section
						customerdao.customer1(cust);
						status = false;
					}
					else {
						System.out.println("Enter valid input");
					}
				}
				break;
				
//			Return back
			case 3:
				status = false;
				break;
			}
		} while (status);
	}
}
