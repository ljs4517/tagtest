package Model;

public class Customer {
	private String customerNumber;
	private String name;
	private String gender;
	private String birthday;
	private String joinDate;
	private String phoneNumber;
	public Customer(String customerNumber, String name, String gender, String birthday, String joinDate,
			String phoneNumber) {
		super();
		this.customerNumber = customerNumber;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.joinDate = joinDate;
		this.phoneNumber = phoneNumber;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}