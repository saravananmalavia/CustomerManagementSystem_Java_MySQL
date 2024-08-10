package cs.cms.dto;

public class Customer{

	private int customerId;
	private String customerName;
	private String mobileNumber;
	private String email;
	private String address;
	
	
	
	public Customer(String customerName, String mobileNumber, String email, String address) {
		
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.address = address;
	}
	
	
	public Customer(int customerId, String customerName, String mobileNumber, String email, String address) {
		
		this.customerId = customerId;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.address = address;
	}
	
	

	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;		
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;		
	}
	
	
	
	
}
