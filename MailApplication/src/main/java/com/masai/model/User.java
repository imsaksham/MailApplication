package com.masai.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
public class User {
	
//	{
//		"email": "saksham@gmail.com",
//	    "firstName": "Saksham",
//	    "lastName": "Singh",
//	    "mobileNumber": "7987126495",
//	    "dateOfBirth": "1998-11-12",
//		"username": "imsaksham9",
//	    "password": "sak@123"
//	}
	
	@Id
	@Column(name = "email", unique = true)
	@Email
	private String email;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$", message = "First name must not contain numbers or special characters")
	private String firstName;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must not contain numbers or special characters")
	private String lastName;
	
	@NotNull
	@NotBlank
	@Size(min = 3, max = 20)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Please input a valid username which contain lowercase character and length should be minimum 3 and maximum 20")
	private String username;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must have 10 digits")
	private String mobileNumber;
	
	@NotNull
	@PastOrPresent
	private LocalDate dateOfBirth;
	
	@NotNull
	@NotBlank
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,12}$", message = "Password must be alphanumeric and must contain 6-12 characters having at least one special character, one upper case, one lowercase, and one digit.")
	private String password;
	
//	@OneToMany(mappedBy = "user")
//	private List<Email> emails;
}
