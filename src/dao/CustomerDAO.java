package dao;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Book;
import model.Customer;
import model.Order;
import utility.ConnectionManager;

public class CustomerDAO {
	Scanner in = new Scanner(System.in);
	BookDAO bookdao = new BookDAO();
	Book book = null;
	Customer cust = null;
	OrderBook orderbook = new OrderBook();
	List<Customer> list = new ArrayList<Customer>();
	List<Order> list1 = new ArrayList<Order>();
	Order order = null;
	boolean status = true;
	boolean check = true;
	DisplayCustomer displaycustomer = new DisplayCustomer();
	DisplayOrder displayorder = new DisplayOrder();
	Wishlist wishlist = new Wishlist();
	int flag = 0;

//	Method to call customer section after login
	public void customer1(Customer cust) throws IOException, SQLException {
		System.out.println("");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("WELCOME TO BOOK STORE " + cust.getCustName().toUpperCase());
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		do {
			System.out.println("____________________________________________");
			System.out.println("");
			System.out.println("1.Book Details");
			System.out.println("2.Order Book");
			System.out.println("3.Your Orders");
			System.out.println("4.Wishlist");
			System.out.println("5.Exit");
			System.out.println("____________________________________________");
			int choice = in.nextInt();

			switch (choice) {

//			get all book details stored in the database
			case 1:
				bookdao.bookDetails();
				break;

//			Order book
			case 2:
				System.out.println("____________________________________________");
				System.out.println("");
				System.out.println("Enter the book id :");
				long id = in.nextLong();
				System.out.println("Enter book quantity :");
				int quantity = in.nextInt();
				System.out.println("____________________________________________");
				Connection con = ConnectionManager.getConnection();
				String sql = "SELECT * FROM BOOKS";
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery(sql);
				while (rs.next()) {
					flag = 0;

//					check for particular book id and quantity specified by the customer 
					if (id == rs.getLong(1) && quantity <= rs.getInt(5)) {
						flag = 1;
						System.out.print("Confirm Your Order(Press : Y/N) : ");
						String temp = in.next();
						if (temp.equals("Y") || temp.equals("y")) {
							book = new Book(id, rs.getString(2), rs.getString(3), rs.getDouble(4), quantity);
							orderbook.orderbook(cust, book);
							break;
						}
					}
				}
				if (flag == 0) {
					System.out.println("Couldn't perform the operation");
				}
				con.close();
				break;

//			Get customer order details
			case 3:
				orderbook.orderDetail(cust);
				break;

			case 4:
				do {
					System.out.println("____________________________________________");
					System.out.println("");
					System.out.println("1.Add to wishlist");
					System.out.println("2.Go to wishlist");
					System.out.println("3.Go back");
					System.out.println("____________________________________________");
					int option = in.nextInt();
					switch(option) {
					case 1:
						System.out.println("____________________________________________");
						System.out.println("");
						System.out.println("Enter the book name to add to wishlist ");
						String bookName = in.next();
						System.out.println("Enter the quantity");
						int bookQuantity = in.nextInt();
						System.out.println("____________________________________________");
						book = new Book(bookName, bookQuantity);
						wishlist.addWishlist(cust, book);
						break;
					case 2:
						System.out.println("_____________________________________________________________________________________________");
						System.out.println("Customer_Name\t" + "Customer_Phone\t" + "Customer_Email\t\t\t"+"Book_Name\t"+"Book_Quantity");
						System.out.println("---------------------------------------------------------------------------------------------");
						
//						display in table format with defined spacing
						wishlist.showWishlist(cust);
						System.out.println("_____________________________________________________________________________________________");
						System.out.println("");
					case 3:
						check = false;
						break;
					}
				}while(check);
				
				break;
				
//			return back
			case 5:
				status = false;
				break;
			}
		} while (status);
	}

//	Method to get all customer details stored in the database and display
	public void getAllCustomer() throws IOException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sql = "SELECT * FROM CUSTOMER";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);
		System.out.println("_______________________________________________________________");
		System.out.println("Customer_Name\t" + "Customer_Email\t\t\t" + "Customer_Phone");
		System.out.println("---------------------------------------------------------------");
		while (rs.next()) {
			String custName = rs.getString(1);
			String custEmail = rs.getString(2);
			String custPassword = rs.getString(3);
			String custPhone = rs.getString(4);
			cust = new Customer(custName, custEmail, custPassword, custPhone);
			list.add(cust);
			
//			display in table format with defined spacing
			displaycustomer.displayCustomer(cust, list);
			list.remove(cust);
		}
		System.out.println("_______________________________________________________________");
		System.out.println("");
		con.close();
	}

//	Method to get all customer orders and display
	public void getAllOrders() throws IOException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sql = "SELECT * FROM BOOKORDER";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);
		System.out.println("____________________________________________________________________________________");
		System.out.println("Customer_Name\t" + "Customer_Phone\t" + "Book_Id\t" + "Book_Name\t" + "Book_Quantity\t"
				+ "Book_Price");
		System.out.println("------------------------------------------------------------------------------------");
		while (rs.next()) {
			String custName = rs.getString(1);
			String custPhone = rs.getString(2);
			long bookId = rs.getLong(3);
			String bookName = rs.getString(4);
			int bookQuantity = rs.getInt(5);
			double bookPrice = rs.getDouble(6);
			order = new Order(custName, custPhone, bookId, bookName, bookQuantity, bookPrice);
			list1.add(order);
			
//			display in table format with defined spacing
			displayorder.displayAllOrder(order, list1);
			list1.remove(order);
		}
		System.out.println("____________________________________________________________________________________");
		System.out.println("");
		con.close();
	}

}
