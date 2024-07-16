package com.semulea.agrisemu.dto;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.UserWorker;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserWorkerDTO {
	
	private Long id;
	
	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	private String username;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	
	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	
	public UserWorkerDTO() {
		
	}

	public UserWorkerDTO(UserWorker entity) {
		BeanUtils.copyProperties(entity, this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
