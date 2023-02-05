package com.masai.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.model.CurrentUserSession;
import com.masai.model.LoginDTO;
import com.masai.model.User;
import com.masai.repository.UserDao;
import com.masai.repository.UserSessionDao;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserDao uDao;
	
	@Autowired
	private UserSessionDao sDao;
	
	@Override
	public String logIntoAccount(LoginDTO loginDto) throws LoginException {
		
		User existingUser = uDao.findByUsername(loginDto.getUsername());
		
		if (existingUser == null) {
			throw new LoginException("Please enter a valid username");
		}
		
		
		CurrentUserSession opt = sDao.findByEmail(existingUser.getEmail());
		
		if (opt != null) {
			throw new LoginException("User already logged in");
		}
		
		if (existingUser.getPassword().equals(loginDto.getPassword())) {
			
			String key = UUID.randomUUID().toString().replace("-", "").substring(0, 4);
			
			CurrentUserSession activeSession = new CurrentUserSession(existingUser.getEmail(), key, LocalDateTime.now());
			
			sDao.save(activeSession);
			
			return activeSession.toString();
		}
		else {
			throw new LoginException("Please enter a valid password");
		}
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		CurrentUserSession activeSession = sDao.findByUuid(key);
		
		if (activeSession == null) {
			throw new LoginException("User not logged in");
		}
		
		sDao.delete(activeSession);
		
		return "Successfully Logged out!";
	}

}
