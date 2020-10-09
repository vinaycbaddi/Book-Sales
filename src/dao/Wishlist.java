package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Book;
import model.Customer;
import model.WishlistModel;
import utility.ConnectionManager;

public class Wishlist {
	DisplayWishlist display = new DisplayWishlist();
	WishlistModel wishlist = null;
	
//	Method to add a book to wishlist requested by the customer
	public void addWishlist(Customer cust, Book book) throws IOException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sql = "INSERT INTO WISHLIST(CUSTOMER_NAME,CUSTOMER_PHONE,CUSTOMER_EMAIL,BOOK_NAME,BOOK_QUANTITY)VALUES(?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, cust.getCustName());
		st.setString(2, cust.getCustPhone());
		st.setString(3, cust.getCustEmail());
		st.setString(4, book.getBookName());
		st.setInt(5, book.getBookQuantity());
		int temp = st.executeUpdate();
		if (temp == 1) {
			System.out.println("Item added to wishlist");
		} else {
			System.out.println("Couldn't perform the operation");
		}
	}
	
//	Display the items in the wishlist of particular customer
	public void showWishlist(Customer cust) throws IOException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sql = "SELECT * FROM WISHLIST";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			if(rs.getString(3).equals(cust.getCustEmail())==true) {
				wishlist = new WishlistModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				display.displayWishlist(wishlist);
			}
		}
		con.close();
	}
	
//	Display the all items in the wishlist
	public void showAllWishlist() throws IOException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sql = "SELECT * FROM WISHLIST";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
				wishlist = new WishlistModel(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));
				display.displayWishlist(wishlist);
		}
		con.close();
	}
	
//	Delete the item in the wishlist 
	public void deleteWishlist(List<WishlistModel>list1) throws IOException, SQLException {
		Connection con4 = ConnectionManager.getConnection();
			String sql = "DELETE FROM WISHLIST WHERE CUSTOMER_PHONE=?";
			PreparedStatement st = con4.prepareStatement(sql);
			for(WishlistModel o :list1) {
				System.out.println("1");
				st.setString(1, o.getCustPhone());
				st.executeUpdate();
				break;
			}
			list1.remove(0);
		con4.close();
	}
}
