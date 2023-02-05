package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.model.Email;

public interface EmailService {
	
	public Email sendMail(Email email, String key);
	
	public List<Email> sendMails(Email email, String key);
	
	public List<Email> getEmailsByUser(String sendFrom, String key);
	
	public String deleteEmailByUser(String sendTo, String sendFrom, Integer id, String key);
	
	public String deleteEmailsByUser(String sendTo, String sendFrom, String key);
	
	public String deleteAllUserEmails(String sendTo, String key);
	
	public String starredEmail(String sendTo, Integer id, String starred);
}
