package com.masai.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
