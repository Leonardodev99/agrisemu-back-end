package com.semulea.agrisemu.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String phone;
	private String password;
	
	public UserDTO(User entity) {
		BeanUtils.copyProperties(entity, this);
	}

}
