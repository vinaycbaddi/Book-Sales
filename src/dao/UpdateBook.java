package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Book;
import utility.ConnectionManager;

public class UpdateBook {
	
//	Method for new Book insertion 
	public void addBook(Book book) throws IOException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE BOOKS SET BOOK_QUANTITY = ? WHERE BOOK_ID = ?";
		String sql1 = "SELECT * FROM BOOKS";
		PreparedStatement st1 = con.prepareStatement(sql1);
		ResultSet rs = st1.executeQuery(sql1);
		while (rs.next()) {
			if (rs.getLong(1) == book.getBookId()) {
				if (rs.getInt(5) >= 0) {
					int add = rs.getInt(5) + book.getBookQuantity();
					PreparedStatement st = con.prepareStatement(sql);
					st.setInt(1, add);
					st.setLong(2, book.getBookId());
					st.executeUpdate();
					System.out.println("UPDATION COMPLETED");
					System.out.println("");
				}
			}
		}
		con.close();
	}

	
//	Method to delete existing book 
	public void removeBook(Book book) throws IOException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE BOOKS SET BOOK_QUANTITY = ? WHERE BOOK_ID = ?";
		String sql1 = "SELECT * FROM BOOKS";
		PreparedStatement st1 = con.prepareStatement(sql1);
		ResultSet rs = st1.executeQuery(sql1);
		while (rs.next()) {
			if (rs.getLong(1) == book.getBookId()) {
				if (rs.getInt(5) >= 0) {
					int remove = rs.getInt(5) - book.getBookQuantity();
					if (remove >= 0) {
						PreparedStatement st = con.prepareStatement(sql);
						st.setInt(1, remove);
						st.setLong(2, book.getBookId());
						st.executeUpdate();
						System.out.println("UPDATION COMPLETED");
						System.out.println("");
					} else {
						System.out.println("Couldn't perform the operation");
						con.close();
						break;
					}
				}
			}
		}
		con.close();
	}

	
//	Method to update particular book price
	public void updatePrice(Book book) throws IOException, SQLException {
		Connection con = ConnectionManager.getConnection();
		String sql = "UPDATE BOOKS SET BOOK_PRICE = ? WHERE BOOK_ID = ?";
		PreparedStatement st = con.prepareStatement(sql);
		if (book.getBookPrice() >= 0) {
			st.setDouble(1, book.getBookPrice());
			st.setLong(2, book.getBookId());
			st.executeUpdate();
			System.out.println("UPDATION COMPLETED");
			System.out.println("");
			con.close();
		} else {
			System.out.println("Couldn't perform the operation");
			con.close();
		}
	}
}
