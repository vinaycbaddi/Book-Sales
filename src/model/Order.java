package model;

// Book Order Parameters
public class Order {
	private String custName;
	private String custPhone;
	private long bookId;
	private String bookName;
	private int bookQuantity;
	private double bookPrice;
	
	public Order() {
		
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

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
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

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Order(String custName, String custPhone, long bookId, String bookName, int bookQuantity, double bookPrice) {
		super();
		this.custName = custName;
		this.custPhone = custPhone;
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookQuantity = bookQuantity;
		this.bookPrice = bookPrice;
	}

}
