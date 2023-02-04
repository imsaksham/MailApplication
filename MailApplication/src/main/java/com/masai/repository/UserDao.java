package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.User;
import com.masai.model.UserDTO;

@Repository
public interface UserDao extends JpaRepository<User, String> {

	@Query("select u from User u where u.email=?1")
	public User findByEmail(String email);
	
	@Query("select u from User u where u.username=?1")
	public User findByUsername(String username);
	
	@Query("select new com.masai.model.UserDTO(u.firstName, u.mobileNumber) from User u where u.email=?1")
	public UserDTO getFirstNameAndMobileNumberByEmail(String email);

	
}
