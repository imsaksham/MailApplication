package com.masai.service;


import com.masai.model.User;
import com.masai.model.UserDTO;

public interface UserService {

	public User registerUser(User user);
	public User getUserByUsername(String email);
	public UserDTO getFirstNameAndMobileNumber(String email);
	public User updateUser(User user, String key);
	public String deleteUser(String username, String key);
	
}
