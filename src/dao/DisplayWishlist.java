package dao;

import model.Order;
import model.WishlistModel;

public class DisplayWishlist {
	public void displayWishlist(WishlistModel wishlist) {
		
		String custName = wishlist.getCustName();
		for (int i = custName.length(); i < 16; i++) {
			custName = custName + " ";
		}
		
		String custPhone = wishlist.getCustPhone();
		for (int i = custPhone.length(); i < 16; i++) {
			custPhone = custPhone + " ";
		}
		String custEmail = wishlist.getCustEmail();
		for (int i = custEmail.length(); i < 32; i++) {
			custEmail = custEmail + " ";
		}
		
		String bookName = wishlist.getBookName();
		for (int i = bookName.length(); i < 16; i++) {
			bookName = bookName + " ";
		}

		String bookQuantity = "";
		bookQuantity = bookQuantity + wishlist.getBookQuantity();
		for (int i = bookQuantity.length(); i < 5; i++) {
			bookQuantity = bookQuantity + " ";
		}
		System.out.println(custName + custPhone + custEmail + bookName + bookQuantity);
	}
}
