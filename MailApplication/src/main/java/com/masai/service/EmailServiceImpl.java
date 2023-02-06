package com.masai.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.exception.MessageNotFoundException;
import com.masai.exception.UserNotFoundException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Email;
import com.masai.model.User;
import com.masai.repository.EmailDao;
import com.masai.repository.UserDao;
import com.masai.repository.UserSessionDao;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailDao emailDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserSessionDao userSessionDao;

	User user = new User();

	@Override
	public Email sendMail(Email email, String key) {

		CurrentUserSession activeSession = userSessionDao.findByUuid(key);

		if (activeSession == null || !email.getSendFrom().equals(activeSession.getEmail())) {
			throw new LoginException(
					"Please login first and please provide a valid key to send mail with your email account");
		}

		User recipient = userDao.findByEmail(email.getSendTo());

		System.out.println("HI!");

		if (recipient == null) {
			throw new LoginException("Please provide a valid recipient email id.");
		}

		return emailDao.save(email);
	}

	@Override
	public List<Email> sendMails(Email email, String key) {

		CurrentUserSession activeSession = userSessionDao.findByEmail(email.getSendFrom());

		System.out.println("=======================================================================" + activeSession);

		if (activeSession == null || !email.getSendFrom().equals(activeSession.getEmail())) {

			throw new LoginException(
					"Please login first and please provide a valid key to send mail with your email account");
		}

		User user = userDao.findByEmail(email.getSendTo());

		if (user == null) {
			throw new UserNotFoundException("Please provide a valid recipient email id.");
		}

		List<Email> emails = new ArrayList<>();

		for (String rec : email.getRecipients()) {

			User findUser = userDao.findByEmail(rec);

			if (findUser == null) {

				throw new UserNotFoundException("User is not available with this email id: " + rec);
			}

			emails.add(emailDao.save(email));
		}

		return emails;
	}

	@Override
	public List<Email> getEmailsByUser(String sendTo, String key) {

		CurrentUserSession activeSession = userSessionDao.findByUuid(key);

		if (activeSession == null || !sendTo.equals(activeSession.getEmail())) {

			throw new LoginException("Please provide a valid email to get all the messages your account");
		}

		List<Email> results = emailDao.findBySendTo(sendTo);

		if (results.size() > 0) {

			return results;
		}

		throw new UserNotFoundException("Messages are empty");

//		List<Email> recipient = new ArrayList<>();
//		
//		for (String rec: email.getRecipients()) {
//			List<Email> recp = emailDao.findByRecipients(rec);
//			
//			if ()
//		}
	}

	@Override
	public String deleteEmailByUser(String sendTo, String sendFrom, Integer id, String key) { // receiver -- sender

		CurrentUserSession activeSession = userSessionDao.findByUuid(key);

		if (activeSession == null || !sendTo.equals(activeSession.getEmail())) {

			throw new LoginException("Please login first and provide a valid key to delete your messages");
		}

		List<Email> isEmailPresent = emailDao.findBySendFrom(sendFrom);

		if (isEmailPresent.size() <= 0) {
			throw new MessageNotFoundException("No messages from this account: " + sendFrom);
		}

		for (Email em : isEmailPresent) {
			if (id.equals(em.getId())) {
				System.out.println("=================================");
				emailDao.delete(em);
				return "Email deleted successfully" + "\n" + em.toString();

			}
			System.out.println("================================="+id);
		}

		throw new UserNotFoundException("Id is not correct: " + id);

	}

	@Override
	public String deleteEmailsByUser(String sendTo, String sendFrom, String key) {
		
		CurrentUserSession activeSession = userSessionDao.findByUuid(key);
		
		if (activeSession == null || !sendTo.equals(activeSession.getEmail())) {
			
			throw new LoginException("Please login first and provide a valid key to delete your messages");
		}
		
		List<Email> deleteEmail = emailDao.findBySendFrom(sendFrom);
		
		Boolean flag = false;
		
		if (deleteEmail.size() > 0) {
			for (Email em: deleteEmail) {
				
				if (em.getSendTo().equals(sendTo)) {
					emailDao.delete(em);
					flag = true;
				}
			}
		}
		
		if (flag == false) {
			throw new MessageNotFoundException("No messages from this account: " + sendFrom);
		}
		
		return "Email deleted successfully";
	}

	@Override
	public String deleteAllUserEmails(String sendTo, String key) {
		
		CurrentUserSession activeSession = userSessionDao.findByUuid(key);
		
		if (activeSession == null  || !sendTo.equals(activeSession.getEmail())) {
			
			throw new LoginException("Please login first and provide a valid key to delete your messages");
		}
		
		List<Email> deleteEmail = emailDao.findBySendTo(sendTo);
		
		Boolean flag = false;
		
		if (deleteEmail.size() > 0) {
			for (Email em: deleteEmail) {
				if (em.getSendTo().equals(sendTo)) {
					emailDao.delete(em);
					flag = true;
				}
			}
		}
		
		if (flag == false) {
			throw new UserNotFoundException("No messages from this account: " +sendTo);
		}
		
		return "Email deleted successfully";
	}

	@Override
	public String starredEmail(String sendTo, Integer id, String starred) {
		
		CurrentUserSession activeSession = userSessionDao.findByEmail(sendTo);
		
		if (activeSession == null  || !sendTo.equals(activeSession.getEmail())) {
			
			throw new LoginException("Please login first and provide a valid key to update your email account");
		}
		
		List<Email> findUser = emailDao.findBySendTo(sendTo);
		
		if (findUser.size() > 0) {
			for (Email em: findUser) {
				if (em.getId().equals(id)) {
					em.setStarred(starred);
					emailDao.save(em);
					return em.toString();
				}
			}
		}
		
		throw new UserNotFoundException("Recipient is not available with this email id: " +id);
	}

}
