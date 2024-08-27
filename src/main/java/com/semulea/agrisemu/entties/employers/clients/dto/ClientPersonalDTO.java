package com.semulea.agrisemu.entties.employers.clients.dto;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.clients.ClientPersonal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientPersonalDTO {
	
	
	private Long id;
	
	@NotBlank(message = "Client name is mandatoy")
	@Size(min = 2, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;
	private String email;
	private String phone;
	
	@NotBlank(message = "Client address is mandatoy")
	@Size(min = 3, max = 200, message = "Address must be between 3 and 200 characters")
	private String address;
	
	public ClientPersonalDTO() {
		
	}
	
	public ClientPersonalDTO(ClientPersonal entity) {
		BeanUtils.copyProperties(entity, this);
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientPersonalDTO other = (ClientPersonalDTO) obj;
		return Objects.equals(id, other.id);
	}

}
