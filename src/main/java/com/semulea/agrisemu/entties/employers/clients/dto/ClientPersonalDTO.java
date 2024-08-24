package com.semulea.agrisemu.entties.employers.clients.dto;

import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.clients.ClientPersonal;

public class ClientPersonalDTO {
	
	private Long id;
	private String name;
	private String email;
	private String phone;
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
