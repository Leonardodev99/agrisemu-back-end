package com.semulea.agrisemu.dto;

import com.semulea.agrisemu.entties.UserWorker;

public class UserWorkerMinDTO {
	
	private Long id;
	private String username;
	private String email;
	
	public UserWorkerMinDTO() {
		
	}

	public UserWorkerMinDTO(UserWorker entity) {
	
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
