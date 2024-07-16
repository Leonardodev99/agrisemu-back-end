package com.semulea.agrisemu.entties;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.dto.UserAdminDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_admins", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class UserAdmin implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	public UserAdmin(UserAdminDTO userAdminDTO) {
		BeanUtils.copyProperties(userAdminDTO, this);
	}

}
