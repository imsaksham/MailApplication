package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.UserNotFoundException;
import com.masai.model.User;
import com.masai.model.UserDTO;
import com.masai.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("users")
	public ResponseEntity<User> registerUserOnEmail(@RequestBody User user) {
		User savedUsers = userService.registerUser(user);
		
		return new ResponseEntity<User>(savedUsers, HttpStatus.CREATED);
	}
	
	@GetMapping("users/{username}")
	public ResponseEntity<User> getUser(@PathVariable("username") String username) throws UserNotFoundException {

		User user = userService.getUserByUsername(username);
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/customuserdetails/{email}")
	public ResponseEntity<UserDTO> getUserFirstNameAndMobileNumber(@PathVariable("email") String email) throws UserNotFoundException {
		
		UserDTO result =  userService.getFirstNameAndMobileNumber(email);
		
		return new ResponseEntity<UserDTO>(result, HttpStatus.OK);
	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<User> updateUser(@RequestBody User user, @RequestParam(required = false) String key) {
		
		User updatedUser = userService.updateUser(user, key);
		
		return new ResponseEntity<User>(updatedUser, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteuser")
	public String deleteUser(@RequestBody User user, @RequestParam(required = false) String key) {
		
		return userService.deleteUser(user, key);
	}
}
