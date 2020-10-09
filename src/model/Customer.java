package model;

// Customer Parameters
public class Customer {
	private String custName;
	private String custEmail;
	private String custPassword;
	private String custPhone;
	
	public Customer() {
		
	}
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getPassword() {
		return custPassword;
	}

	public void setPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public Customer(String custName, String custEmail, String custPassword, String custPhone) {
		super();
		this.custName = custName;
		this.custEmail = custEmail;
		this.custPassword = custPassword;
		this.custPhone = custPhone;
	}

	public Customer(String custEmail) {
		super();
		this.custEmail = custEmail;
	}

}
