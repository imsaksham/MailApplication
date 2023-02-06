package com.masai.controller;


import java.util.List;

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

import com.masai.model.Email;
import com.masai.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping("/sendmail")
	public ResponseEntity<Email> sendMailToUser(@RequestBody Email email, @RequestParam(required = false) String key) {
		
		Email sendMessage = emailService.sendMail(email, key);
		
		return new ResponseEntity<Email>(sendMessage, HttpStatus.OK);
	}
	
	@PostMapping("/sendmails")
	public ResponseEntity<List<Email>> sendMailsToUser(@RequestBody Email email, @RequestBody(required = false) String key) {
		List<Email> sendMessage = emailService.sendMails(email, key);
		
		return new ResponseEntity<List<Email>>(sendMessage, HttpStatus.OK);
	}
	
	@GetMapping("mails/{receiver}")
	public ResponseEntity<List<Email>> getMessagesByUser(@PathVariable("receiver") String sendTo, @RequestParam(required = false) String key)  {
		
		List<Email> results = emailService.getEmailsByUser(sendTo, key);
		
		return new ResponseEntity<List<Email>>(results, HttpStatus.OK);
	}
	
	@DeleteMapping("usermails/{receiver}/{sender}/{id}")
	public ResponseEntity<String> deleteMessageByUser(@PathVariable("receiver") String sendTo, @PathVariable("sender") String sendFrom, @PathVariable("id") Integer id, @RequestParam(required = false) String key)  {
		
		String results = emailService.deleteEmailByUser(sendTo, sendFrom, id, key);
		
		return new ResponseEntity<String>(results, HttpStatus.OK);
	}
	
	@DeleteMapping("usermails1/{receiver}/{sender}")
	public ResponseEntity<String> deleteMessagesByUser(@PathVariable("receiver") String sendTo, @PathVariable("sender") String sendFrom, @RequestParam(required = false) String key)  {
		
		String results = emailService.deleteEmailsByUser(sendTo, sendFrom, key);
		
		return new ResponseEntity<String>(results, HttpStatus.OK);
	}
	
	@DeleteMapping("usermails2/{receiver}")
	public ResponseEntity<String> deleteAllUserMessages(@PathVariable("receiver") String sendTo, @RequestParam(required = false) String key)  {
		
		String results = emailService.deleteAllUserEmails(sendTo, key);
		
		return new ResponseEntity<String>(results, HttpStatus.OK);
	}
	
	@PutMapping("/starredmail/{receiver}/{id}/{starred}")
	public ResponseEntity<String> starredEmailByUser(@PathVariable("receiver") String sendTo, @PathVariable("id") Integer id, @PathVariable("starred") String starred) {
		
		String result = emailService.starredEmail(sendTo, id, starred);
		
		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}
}
