package dao;

import java.util.List;

import model.Order;

// Method to display customer orders
public class DisplayOrder {
	
//	Assign a particular width/size to the Order field parameters in order to display in proper table format	
	public void displayOrder(Order o) {
		String custName = o.getCustName();
		for (int i = custName.length(); i < 16; i++) {
			custName = custName + " ";
		}
		String custEmail = o.getCustPhone();
		for (int i = custEmail.length(); i < 16; i++) {
			custEmail = custEmail + " ";
		}
		String id = "";
		id = id + o.getBookId();
		for (int i = id.length(); i < 8; i++) {
			id = id + " ";
		}
		String bookName = o.getBookName();
		for (int i = bookName.length(); i < 16; i++) {
			bookName = bookName + " ";
		}
		String quantity = "";
		quantity = quantity + o.getBookQuantity();
		for (int i = quantity.length(); i < 16; i++) {
			quantity = quantity + " ";
		}
		String price = "";
		price = price + o.getBookPrice();
		for (int i = price.length(); i < 16; i++) {
			price = price + " ";
		}
		System.out.println(custName + custEmail + id + bookName + quantity + price);
	}

	public void displayAllOrder(Order order, List<Order> list1) {
		for (Order o : list1) {
			String custName = o.getCustName();
			for (int i = custName.length(); i < 16; i++) {
				custName = custName + " ";
			}
			String custEmail = o.getCustPhone();
			for (int i = custEmail.length(); i < 16; i++) {
				custEmail = custEmail + " ";
			}
			String id = "";
			id = id + o.getBookId();
			for (int i = id.length(); i < 8; i++) {
				id = id + " ";
			}
			String bookName = o.getBookName();
			for (int i = bookName.length(); i < 16; i++) {
				bookName = bookName + " ";
			}
			String quantity = "";
			quantity = quantity + o.getBookQuantity();
			for (int i = quantity.length(); i < 16; i++) {
				quantity = quantity + " ";
			}
			String price = "";
			price = price + o.getBookPrice();
			for (int i = price.length(); i < 16; i++) {
				price = price + " ";
			}
			System.out.println(custName + custEmail + id + bookName + quantity + price);
		}
	}
}
