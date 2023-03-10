package com.masai.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
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
	
	@ElementCollection(fetch = FetchType.EAGER)
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
	private String starred;
	
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

	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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



	public String getStarred() {
		return starred;
	}



	public void setStarred(String starred) {
		this.starred = starred;
	}



	@Override
	public String toString() {
		return "Email [id=" + id + ", sendFrom=" + sendFrom + ", sendTo=" + sendTo + ", recipients=" + recipients
				+ ", subject=" + subject + ", message=" + message + ", date=" + date + ", starred=" + starred + "]";
	}

	
	
	
}
