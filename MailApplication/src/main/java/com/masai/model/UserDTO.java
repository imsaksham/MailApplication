package com.masai.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDTO {

	@NotNull
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name must not contain numbers or special characters")
	private String firstName;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must have 10 digits")
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
