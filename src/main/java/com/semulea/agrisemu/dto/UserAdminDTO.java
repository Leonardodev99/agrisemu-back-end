package com.semulea.agrisemu.dto;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.UserAdmin;
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
public class UserAdminDTO {
	
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	private String name;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	
	@NotBlank(message = "Phone is mandatoy")
	@PhoneFormat
	private String phone;
	
	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;
	
	public UserAdminDTO (UserAdmin entity) {
		
		BeanUtils.copyProperties(entity, this);
	}
}
