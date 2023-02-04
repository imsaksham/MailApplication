package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserEmail")
public class Email {
	
	// {
    //     "sendFrom": "saksham@gmail.com",
    //     "sendTo": "himanshu@gmail.com",
    //     "subject": "Demo",
    //     "message": "Hey! Himanshu, This is just a demo",
    //     "date": "2023-02-03"
	// }
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "email", unique = false)
	private String sendFrom;
	
	@Column(name = "recipient", unique = false)
	private String sendTo;
	
	@ElementCollection
	@Column(name = "recipients")
	private List<String> recipients;
	
//	{
//        "sendFrom": "saksham@gmail.com",
//        "sendTo": "himanshu@gmail.com",
//        "recipients": ["himanshu@gmail.com", "rahul@gmail.com"],
//        "subject": "Demo",
//        "message": "Hey! This is an another demo",
//        "date": "2023-02-03"
//	}
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "message")
	private String message;
	
	@LastModifiedDate
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "starred")
	private Boolean starred;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_email", nullable = true, referencedColumnName = "email")
//	private User user;
	
	

	public Email(String sendFrom, List<String> recipients, String subject, String message, LocalDate date) {
		super();
		this.sendFrom = sendFrom;
		this.recipients = recipients;
		this.subject = subject;
		this.message = message;
		this.date = date;
	}



	public Email(String sender, String sendTo, String subject, String message, LocalDate date) {
		super();
		this.sendFrom = sender;
		this.sendTo = sendTo;
		this.subject = subject;
		this.message = message;
		this.date = date;
	}



	public String getSendFrom() {
		return sendFrom;
	}



	public void setSendFrom(String sendFrom) {
		this.sendFrom = sendFrom;
	}



	public String getSendTo() {
		return sendTo;
	}



	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}



	public List<String> getRecipients() {
		return recipients;
	}



	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public LocalDate getDate() {
		return date;
	}



	public void setDate(LocalDate date) {
		this.date = date;
	}



	public Boolean getStarred() {
		return starred;
	}



	public void setStarred(Boolean starred) {
		this.starred = starred;
	}



//	public User getUser() {
//		return user;
//	}
//
//
//
//	public void setUser(User user) {
//		this.user = user;
//	}
	
	
}
