package com.masai.model;

public class UserDTO {

	private String firstName;
	private String mobileNumber;
	
	public UserDTO() {
		
	}

	public UserDTO(String firstName, String mobileNumber) {
		super();
		this.firstName = firstName;
		this.mobileNumber = mobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
}
