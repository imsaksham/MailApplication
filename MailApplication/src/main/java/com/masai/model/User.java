package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	private String email;
	
	private String firstName;
	private String lastName;
	private String username;
	private String mobileNumber;
	private LocalDate dateOfBirth;
	
	private String password;
	
//	@OneToMany(mappedBy = "user")
//	private List<Email> emails;
}
