package com.semulea.agrisemu.dto;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.UserAdmin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserAdminDTO {
	
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	public UserAdminDTO (UserAdmin entity) {
		
		BeanUtils.copyProperties(entity, this);
	}
}
