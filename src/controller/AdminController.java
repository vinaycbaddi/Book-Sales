package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import dao.BookDAO;
import dao.CustomerDAO;
import dao.DisplayCustomer;
import dao.Wishlist;
import model.Book;

public class AdminController {
	
//	Admin method after admin logs in
	public void admin() throws IOException, SQLException {
		Scanner in = new Scanner(System.in);
		BookDAO bookdao = new BookDAO();
		CustomerDAO customerdao = new CustomerDAO();
		Wishlist wishlist = new Wishlist();
		Book book = null;
		boolean status = true;
		boolean check = true;
		boolean test = true;
		
		do {
			System.out.println("____________________________________________");
			System.out.println("");
			System.out.println("1.Insert a book");
			System.out.println("2.Delete a book");
			System.out.println("3.Books section");
			System.out.println("4.Customer section");
			System.out.println("5.Exit");
			System.out.println("____________________________________________");
			System.out.println("\nWhich Action You want to Perform?(1-6)");
			System.out.println("____________________________________________");
			System.out.println("");
			int choice = in.nextInt();
			switch (choice) {
			
//			Inserting a new book
			case 1:
				System.out.println("____________________________________________");
				System.out.println("");
				System.out.println("INSERT BOOK");
				System.out.println("--------------------------------------------");
				System.out.println("Enter book id :");
				long bookId = in.nextLong();
				System.out.println("Enter book name :");
				String bookName = in.next();
				System.out.println("Enter book genre :");
				String bookGenre = in.next();
				System.out.println("Enter book price :");
				double bookPrice = in.nextDouble();
				System.out.println("Enter the book quantity :");
				int bookQuantity = in.nextInt();
				System.out.println("____________________________________________");
				book = new Book(bookId, bookName, bookGenre, bookPrice, bookQuantity);
				bookdao.insertBook(book);
				break;
				
//			Deleting a existing book
			case 2:
				System.out.println("____________________________________________");
				System.out.println("");
				System.out.println("DELETE BOOK");
				System.out.println("--------------------------------------------");
				System.out.println("Enter the book id :");
				long bookId1 = in.nextLong();
				System.out.println("____________________________________________");
				book = new Book(bookId1);
				bookdao.deleteBook(book);
				break;
				
//			Book Section
			case 3:
				bookdao.bookSection();
				break;
				
//			Customer section
			case 4:
				do {
					System.out.println("____________________________________________");
					System.out.println("");
					System.out.println("1.Customer Order Details");
					System.out.println("2.Customer Details");
					System.out.println("3.Wishlist");
					System.out.println("4.Go back");
					System.out.println("____________________________________________");
					int option = in.nextInt();
					
					switch(option){
					
//					get all customer orders
					case 1:
						customerdao.getAllOrders();
						break;
						
//					get all registered customers
					case 2:
						customerdao.getAllCustomer();
						break;
//					customer wishlist
					case 3:
						do {
							System.out.println("____________________________________________");
							System.out.println("");
							System.out.println("1.Go to wishlist");
							System.out.println("2.Update wishlist");
							System.out.println("3.Go back");
							System.out.println("____________________________________________");
							int temp = in.nextInt();
							
							switch(temp) {
							
//							display all items in wishlist
							case 1:
								System.out.println("_____________________________________________________________________________________________");
								System.out.println("Customer_Name\t" + "Customer_Phone\t" + "Customer_Email\t\t\t"+"Book_Name\t"+"Book_Quantity");
								System.out.println("---------------------------------------------------------------------------------------------");
								wishlist.showAllWishlist();
								System.out.println("_____________________________________________________________________________________________");
								System.out.println("");
								break;
								
//							Update to customer for the book availability 
							case 2:
								System.out.println("____________________________________________");
								System.out.println("");
								System.out.println("INSERT BOOK");
								System.out.println("--------------------------------------------");
								System.out.println("Enter book id :");
								long bookId2 = in.nextLong();
								System.out.println("Enter book name :");
								String bookName2 = in.next();
								System.out.println("Enter book genre :");
								String bookGenre2 = in.next();
								System.out.println("Enter book price :");
								double bookPrice2 = in.nextDouble();
								System.out.println("Enter the book quantity :");
								int bookQuantity2 = in.nextInt();
								System.out.println("____________________________________________");
								book = new Book(bookId2, bookName2, bookGenre2, bookPrice2, bookQuantity2);
								bookdao.insertBook(book);
								System.out.println("");
								break;
								
//							return back
							case 3:
								test = false;
								break;
							}
						}while(test);
						break;
					case 4:
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
}
