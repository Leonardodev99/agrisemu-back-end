package com.semulea.agrisemu.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.User;
import com.semulea.agrisemu.validation.PhoneFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Email is mandatory")
	@Email
	private String email;
	
	@NotBlank(message = "Phone is mandatory")
	@PhoneFormat
	private String phone;
	
	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, message = "password must be at least 6 characters long ")
	private String password;
	
	public UserDTO(User entity) {
		BeanUtils.copyProperties(entity, this);
	}

}
