package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.exception.UserAlreadyExistException;
import com.masai.exception.UserNotFoundException;
import com.masai.model.CurrentUserSession;
import com.masai.model.User;
import com.masai.model.UserDTO;
import com.masai.repository.UserDao;
import com.masai.repository.UserSessionDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	private UserSessionDao userSessionDao;
	
	@Override
	public User registerUser(User user) {
		
		Optional<User> opt =  userDao.findById(user.getEmail());
		
		if (opt.isPresent()) {
			throw new UserAlreadyExistException("User already exist with this email id: " +user.getEmail());
		}
		else {
			User saveUser = userDao.save(user);
			
			return saveUser;
		}
	}

	@Override
	public User getUserByUsername(String username) throws UserNotFoundException {
		User user = userDao.findByUsername(username);
		
		if (user == null) {
			throw new UserNotFoundException("User not found with username: " +username);
		}
		
		return user;
	}

	@Override
	public UserDTO getFirstNameAndMobileNumber(String email) throws UserNotFoundException {
		UserDTO customUserDetails = userDao.getFirstNameAndMobileNumberByEmail(email);
		
		if (customUserDetails == null) {
			throw new UserNotFoundException("User is not available with this email id: " +email);
		}
		
		return customUserDetails;
	}
	
	@Override
	public User updateUser(User user, String key) {
		
		CurrentUserSession activeSession = userSessionDao.findByUuid(key);
		
		if (activeSession == null) {
			throw new LoginException("Please provide a valid key to update your email account");
		}
		
		Optional<User> opt = userDao.findById(user.getEmail());
		
		if (opt.isPresent()) {
			User updatedUser = userDao.save(user);
			
			return updatedUser;
		}
		else {
			throw new UserNotFoundException("User is not present with this email id: " +user.getEmail());
		}
	}

	@Override
	public String deleteUser(String username, String key) {
		
		CurrentUserSession activeSession = userSessionDao.findByUuid(key);
		
		if (activeSession == null) {
			throw new LoginException("Please provide a valid key to delete your email account");
		}
		
		User user = userDao.findByUsername(username);
		
		if (user != null) {
			userDao.deleteById(username);
			
			return "Your account has been deleted successfully";
		}
		else {
			throw new UserNotFoundException("User is not present with this email id: " +username);
		}
	}

}
