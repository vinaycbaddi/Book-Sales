package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Book;
import model.Customer;
import utility.ConnectionManager;

// Method to display customer details
public class DisplayCustomer {
	
//	Assign a particular width/size to the customer field parameters in order to display in proper table format
	public void displayCustomer(Customer cust, List<Customer> list) throws IOException, SQLException {
		for (Customer c : list) {
			String name = c.getCustName();
			for (int i = name.length(); i < 16; i++) {
				name = name + " ";
			}
			String email = c.getCustEmail();
			for (int i = email.length(); i < 32; i++) {
				email = email + " ";
			}
			String phone = c.getCustPhone();
			for (int i = phone.length(); i < 16; i++) {
				phone = phone + " ";
			}
			System.out.println(name + email + phone);
		}
	}
}
