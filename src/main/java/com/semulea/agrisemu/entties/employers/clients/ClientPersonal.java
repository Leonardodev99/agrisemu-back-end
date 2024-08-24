package com.semulea.agrisemu.entties.employers.clients;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.clients.dto.ClientPersonalDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "personal_clients")
public class ClientPersonal implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String address;
	
	public ClientPersonal() {
		
	}

	public ClientPersonal(Long id, String name, String email, String phone, String address) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	public ClientPersonal(ClientPersonalDTO entity) {
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
		ClientPersonal other = (ClientPersonal) obj;
		return Objects.equals(id, other.id);
	}
}
