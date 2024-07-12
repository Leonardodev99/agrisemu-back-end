package com.semulea.agrisemu.dto;

import com.semulea.agrisemu.entties.User;

public class UserMinDTO {
	
	private Long id;
	private String username;
	private String email;
	
	public UserMinDTO() {
		
	}

	public UserMinDTO(User entity) {
	
		id = entity.getId();
		username = entity.getUsername();
		email = entity.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	

	
	
	

}
