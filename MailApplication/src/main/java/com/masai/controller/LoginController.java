package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.LoginDTO;
import com.masai.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> logInCustomer(@RequestBody @Valid LoginDTO loginDto) {
		
		String result =  loginService.logIntoAccount(loginDto);
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public String logOutCustomer(String key) {
		
		return loginService.logOutFromAccount(key);
	}
}
