package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Email;
import com.masai.model.User;

@Repository
public interface EmailDao extends JpaRepository<Email, String> {

//	@Query("select e.sender, e.recipient, e.subject, e.message, e.date, e.starred from Email e where e.sender=?1")
//	public Email findBySenderEmail(String sender);
	
	public List<Email> findByRecipients(String sendTo);
	
	public List<Email> findBySendFrom(String sendFrom);
	
	public List<Email> findBySendTo(String sendTo);
	
}
