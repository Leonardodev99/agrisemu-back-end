package com.semulea.agrisemu.entties;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.dto.UserDTO;
import com.semulea.agrisemu.validation.PhoneFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "phone")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Email is mandatory")
	@Email
	private String email;
	
	@NotBlank(message = "Phone is mandatory")
	@PhoneFormat
	private String phone;
	
	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, message = "Password must be et least 6 characters long")
	private String password;
	
	public User(UserDTO userDTO) {
		BeanUtils.copyProperties(userDTO, this);
	}

}
