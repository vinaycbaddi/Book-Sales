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
import model.WishlistModel;
import utility.ConnectionManager;

public class BookDAO {
	Scanner in = new Scanner(System.in);
	boolean status = true;
	List<Book> list = new ArrayList<Book>();
	Book book = null;
	Book displayBook = null;
	DisplayBook display = new DisplayBook();
	UpdateBook updatebook = new UpdateBook();
	Email email = new Email();
	Wishlist wishlist = new Wishlist();
	WishlistModel model = null;
	List<WishlistModel> list2 = new ArrayList<WishlistModel>();
	
//	Method to insert a new book
	public void insertBook(Book book) throws IOException, SQLException {
		try {
			do {
				Connection con = ConnectionManager.getConnection();
				String sql = "INSERT INTO BOOKS(BOOK_ID,BOOK_NAME,BOOK_GENRE,BOOK_PRICE,BOOK_QUANTITY)VALUES(?,?,?,?,?)";
				PreparedStatement st = con.prepareStatement(sql);
				st.setLong(1, book.getBookId());
				st.setString(2, book.getBookName());
				st.setString(3, book.getBookGenre());
				st.setDouble(4, book.getBookPrice());
				st.setInt(5, book.getBookQuantity());
				int temp = st.executeUpdate();
				if (temp == 0) {
					continue;
				} else {
					System.out.println("INSERTION COMPLETED");
					con.close();
					Connection con1 = ConnectionManager.getConnection();
					String sql1 = "SELECT * FROM BOOKS";
					PreparedStatement st1 = con1.prepareStatement(sql1);
					ResultSet rs1 = st1.executeQuery(sql1);
					while(rs1.next()) {
						Connection con2 = ConnectionManager.getConnection();
						String sql2 = "SELECT * FROM WISHLIST";
						PreparedStatement st2 = con2.prepareStatement(sql2);
						ResultSet rs2 = st2.executeQuery(sql2);
						while(rs2.next()) {
//							check if there is a book which is in the wishlist and books
							if(rs1.getString(2).equals(rs2.getString(4))) {
								model = new WishlistModel(rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4));
								list2.add(model);
								
//								Update to customer for the availability of the requested book
								email.wishEmail(model.getCustEmail(),model.getCustPhone(),model.getCustName(),model.getBookName());
								wishlist.deleteWishlist(list2);
							}
						}
						con2.close();
					}
					con1.close();
					status = false;
				}
			} while (status);
		} catch (Exception e) {
			System.out.println("There already exists a book with the given id");
			System.out.println("Please enter a valid input");

		}
	}

	
//	Method to delete a existing book
	public void deleteBook(Book book) throws IOException, SQLException {
		do {
			try {

				Connection con = ConnectionManager.getConnection();
				String sql = "DELETE FROM BOOKS WHERE BOOK_ID=?";
				PreparedStatement st = con.prepareStatement(sql);
				st.setLong(1, book.getBookId());
				int temp = st.executeUpdate();
				if (temp == 0) {
					System.out.println("Enter a valid input");
				} else {
					status = false;
					System.out.println("DELETION COMPLETED");
				}
				con.close();

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (status);
	}

	
//	Method returns all the book details stored in the database
	public void bookDetails() throws IOException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sql = "SELECT * FROM BOOKS";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);
		long bookId;
		String bookName;
		String bookGenre;
		double bookPrice;
		int bookQuantity;
		System.out.println("______________________________________________________________________");
		System.out.println("Book_Id\t" + "Book_Name\t" + "Book_Genre\t" + "Book_Price\t" + "Book_Quantity");
		System.out.println("----------------------------------------------------------------------");
		while (rs.next()) {
			bookId = rs.getLong(1);
			bookName = rs.getString(2);
			bookGenre = rs.getString(3);
			bookPrice = rs.getDouble(4);
			bookQuantity = rs.getInt(5);
			displayBook = new Book(bookId, bookName, bookGenre, bookPrice, bookQuantity);
			list.add(displayBook);
			
//		display in table format with defined spacing
			display.display(displayBook, list);
			list.remove(displayBook);
		}
		System.out.println("______________________________________________________________________");
		System.out.println("");
		con.close();
	}

	
//	Update the quantity of available books
	public void updateQuantity() throws IOException, SQLException {
		do {
			System.out.println("____________________________________________");
			System.out.println("");
			System.out.println("1.Add book and update book quantity");
			System.out.println("2.Remove book and update book quantity");
			System.out.println("3.Go back to menu");
			System.out.println("____________________________________________");
			System.out.println("");
			int choice = in.nextInt();
			switch (choice) {

//			increase the book quantity specified by the admin
			case 1:
				System.out.println("____________________________________________");
				System.out.println("");
				System.out.println("Enter the book id you want to add and update :");
				long bookId = in.nextLong();
				System.out.println("Enter the quantity :");
				int bookQuantity = in.nextInt();
				System.out.println("____________________________________________");
				book = new Book(bookId, bookQuantity);
				updatebook.addBook(book);
				break;

//			decrease the book quantity specified by the admin
			case 2:
				System.out.println("____________________________________________");
				System.out.println("");
				System.out.println("Enter the book id you want to remove and update :");
				long bookId1 = in.nextLong();
				System.out.println("Enter the quantity :");
				int bookQuantity1 = in.nextInt();
				System.out.println("____________________________________________");
				book = new Book(bookId1, bookQuantity1);
				updatebook.removeBook(book);
				break;

//			return back to book section
			case 3:
				bookSection();
				break;
			}
		} while (status);
	}

	
//	Book detail manipulation
	public void bookSection() throws IOException, SQLException {
		do {
			System.out.println("____________________________________________");
			System.out.println("");
			System.out.println("1.Book details");
			System.out.println("2.Update book quantity");
			System.out.println("3.Update book price");
			System.out.println("4.Exit");
			System.out.println("____________________________________________");
			System.out.println("");
			int choice = in.nextInt();
			switch (choice) {

//			get all book details stored in the database
			case 1:
				bookDetails();
				break;

//			call the updateQuantity() method to perform book quantity updation
			case 2:
				updateQuantity();
				break;

//			call the updateQuantity() method to perform book price updation
			case 3:
				System.out.println("____________________________________________");
				System.out.println("");
				System.out.println("Enter the book id you want to update the price :");
				long id = in.nextLong();
				System.out.println("Enter the new price to update :");
				double price = in.nextInt();
				System.out.println("____________________________________________");
				System.out.println("");
				book = new Book(id, price);
				updatebook.updatePrice(book);
				break;
			case 4:
//			return back
				status = false;
				break;
			}
		} while (status);
	}
}
