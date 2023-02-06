package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name = "ActiveSession")
public class CurrentUserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@Column(name = "id", unique = true)
	@Email
	private String email;
	
	private String uuid;
	private LocalDateTime localDateTime;
	
	public CurrentUserSession() {
		
	}
	
	public CurrentUserSession(String email, String uuid, LocalDateTime localDateTime) {
		super();
		this.email = email;
		this.uuid = uuid;
		this.localDateTime = localDateTime;
	}

	public CurrentUserSession(Integer userId, String email, String uuid, LocalDateTime localDateTime) {
		super();
		this.userId = userId;
		this.email = email;
		this.uuid = uuid;
		this.localDateTime = localDateTime;
	}
	
	
	
}
