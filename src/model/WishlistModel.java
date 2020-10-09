package model;

public class WishlistModel {
	private String custName;
	private String custPhone;
	private String custEmail;
	private String bookName;
	private int bookQuantity;
	
	public WishlistModel() {
		
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getBookQuantity() {
		return bookQuantity;
	}
	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}
	public WishlistModel(String custName, String custPhone, String custEmail, String bookName, int bookQuantity) {
		super();
		this.custName = custName;
		this.custPhone = custPhone;
		this.custEmail = custEmail;
		this.bookName = bookName;
		this.bookQuantity = bookQuantity;
	}
	public WishlistModel(String custName, String custPhone, String custEmail, String bookName) {
		super();
		this.custName = custName;
		this.custPhone = custPhone;
		this.custEmail = custEmail;
		this.bookName = bookName;
	}

	
}
