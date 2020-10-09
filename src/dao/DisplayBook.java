package dao;

import java.util.List;

import model.Book;

// Method to display the book details
public class DisplayBook {
	public void display(Book book, List<Book> list) {

//		Assign a particular width/size to the book field parameters in order to display in proper table format
		for (Book b : list) {
			String id = "";
			id = id + b.getBookId();
			for (int i = id.length(); i < 8; i++) {
				id = id + " ";
			}
			String name = b.getBookName();
			for (int i = name.length(); i < 16; i++) {
				name = name + " ";
			}
			String genre = b.getBookGenre();
			for (int i = genre.length(); i < 16; i++) {
				genre = genre + " ";
			}
			String price = "";
			price = price + b.getBookPrice();
			for (int i = price.length(); i < 16; i++) {
				price = price + " ";
			}
			String quantity = "";
			quantity = quantity + b.getBookQuantity();
			for (int i = quantity.length(); i < 16; i++) {
				quantity = quantity + " ";
			}
			System.out.println(id + name + genre + price + quantity);
		}
	}
}
