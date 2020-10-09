package businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import model.Customer;
import utility.ConnectionManager;

public class CustomerRegistration {

//	Customer registration
public void registerCustomer() {
	Scanner in = new Scanner(System.in);
	boolean status = true;
	Customer customer = null;
	Validation validate = new Validation();
	String custName;
	String custEmail;
	String custPassword;
	String custPhone;
	
	do {
		try {
			System.out.println("____________________________________________");
			System.out.println("");
			System.out.println("Enter the customer name");
			custName = in.next();
			System.out.println("Enter the customer email");
			custEmail = in.next();
			System.out.println("Enter the customer password");
			custPassword = in.next();
			System.out.println("Enter the customer phone");
			custPhone = in.next();
			System.out.println("____________________________________________");
			if(validate.checkUserDetails(custEmail, custPassword)) {
			customer = new Customer(custName, custEmail,custPassword,custPhone);
			Connection con = ConnectionManager.getConnection();
			String sql = "INSERT INTO CUSTOMER(CUSTOMER_NAME,CUSTOMER_EMAIL,CUSTOMER_PASSWORD,CUSTOMER_PHONE)VALUES(?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, custName);
			st.setString(2, custEmail);
			st.setString(3, custPassword);
			st.setString(4, custPhone);
			int temp = st.executeUpdate();
			if(temp==1) {
				status = false;
				System.out.println("REGISTRATION COMPLETED");
			}
			con.close();
			}
			else {
				System.out.println("Enter valid credentials");
				continue;
			}
		} catch (Exception e) {
			System.out.println("Customer already exists");
			System.out.println(e.getMessage());
		}
	}while(status);
}
}
