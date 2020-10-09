package model;

// Book Parameters
public class Book {
	private long bookId;
	private String bookName;
	private String bookGenre;
	private double bookPrice;
	private int bookQuantity;
	public Book() {
		
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

	public String getBookGenre() {
		return bookGenre;
	}

	public void setBookGenre(String bookGenre) {
		this.bookGenre = bookGenre;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(int bookQuantity) {
		this.bookQuantity = bookQuantity;
	}

	public Book(long bookId, String bookName, String bookGenre, double bookPrice, int bookQuantity) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookGenre = bookGenre;
		this.bookPrice = bookPrice;
		this.bookQuantity = bookQuantity;
	}

	public Book(long bookId) {
		super();
		this.bookId = bookId;
	}

	public Book(long bookId, int bookQuantity) {
		super();
		this.bookId = bookId;
		this.bookQuantity = bookQuantity;
	}

	public Book(long bookId, double bookPrice) {
		super();
		this.bookId = bookId;
		this.bookPrice = bookPrice;
	}

	public Book(String bookName, int bookQuantity) {
		super();
		this.bookName = bookName;
		this.bookQuantity = bookQuantity;
	}

}
