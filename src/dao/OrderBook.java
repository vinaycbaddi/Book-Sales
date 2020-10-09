package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

import model.Book;
import model.Customer;
import model.Order;
import utility.ConnectionManager;

public class OrderBook {
	Scanner in = new Scanner(System.in);
	boolean status = true;
	Order order = null;
	DisplayOrder displayorder = new DisplayOrder();
	UpdateBook updatebook = new UpdateBook();
	Billpdf bill = new Billpdf();
	Email email = new Email();

	
//	Method to order a book 
	public void orderbook(Customer cust, Book book) throws IOException, SQLException {
		do {
			try {
				Connection con = ConnectionManager.getConnection();
				String sql = "INSERT INTO BOOKORDER(CUSTOMER_NAME,CUSTOMER_PHONE,BOOK_ID,BOOK_NAME,BOOK_QUANTITY,BOOK_PRICE)VALUES(?,?,?,?,?,?)";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, cust.getCustName());
				st.setString(2, cust.getCustPhone());
				st.setLong(3, book.getBookId());
				st.setString(4, book.getBookName());
				updatebook.removeBook(book);
				int q = book.getBookQuantity();
				double p = book.getBookPrice();
				double total = q * p;
				st.setInt(5, q);
				st.setDouble(6, total);
				int temp = st.executeUpdate();
				if (temp == 1) {
					status = false;
					System.out.println("Order Successfull!");
					
//					Create a pdf format bill receipt and send email to customer
					System.out.print("Do you want bill(Press : Y/N) : ");
					String yn = in.next();
					if (yn.equals("Y") || yn.equals("y")) {
						try {
							bill.billpdf(cust.getCustPhone(), cust.getCustName(), cust.getCustEmail(), book.getBookId(),
									book.getBookName(), q, total);
						} catch (DocumentException e) {

							e.printStackTrace();
						} catch (Exception e) {

							e.printStackTrace();
						}
						Customer customer = new Customer(cust.getCustEmail());
						Email email = new Email();
						email.email("vinaycbaddi@gmail.com", customer.getCustEmail(),cust.getCustPhone(), cust.getCustName(), book.getBookId(),
								book.getBookName(), q, total);
						
//					If no bill receipt required, send only email
					} else {
						Customer customer = new Customer(cust.getCustEmail());
						Email email = new Email();
						email.email("vinaycbaddi@gmail.com", customer.getCustEmail(),cust.getCustPhone(), cust.getCustName(), book.getBookId(),
								book.getBookName(), q, total);
						System.out.println("SENT SUCESSFULLY");
					}
					System.out.println("____________________________________________");
					System.out.println("");
					con.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (status);
	}

	
//	Method to get order details of the customer and display
	public void orderDetail(Customer cust) {
		do {
			try {
				Connection con = ConnectionManager.getConnection();
				String sql = "SELECT * FROM BOOKORDER";
				PreparedStatement st = con.prepareStatement(sql);
				ResultSet rs = st.executeQuery(sql);
				System.out.println(
						"____________________________________________________________________________________");
				System.out.println("Customer_Name\t" + "Customer_Phone\t" + "Book_Id\t" + "Book_Name\t"
						+ "Book_Quantity\t" + "Book_Price");
				System.out.println(
						"------------------------------------------------------------------------------------");
				while (rs.next()) {
//					check for customer phone and return order detail
					if (cust.getCustPhone().equals(rs.getString(2))) {
						order = new Order(rs.getString(1), rs.getString(2), rs.getLong(3), rs.getString(4),
								rs.getInt(5), rs.getDouble(6));
						
//						display in table format with defined spacing
						displayorder.displayOrder(order);
						status = false;
					}
				}
				System.out.println(
						"____________________________________________________________________________________");
				System.out.println("");

				con.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (status);
	}

}
