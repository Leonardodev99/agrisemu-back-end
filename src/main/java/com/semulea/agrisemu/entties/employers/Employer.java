package com.semulea.agrisemu.entties.employers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.semulea.agrisemu.employer.dto.EmployerDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "employers", uniqueConstraints = {
		@UniqueConstraint(columnNames = "name"),
		@UniqueConstraint(columnNames = "nif"),
		@UniqueConstraint(columnNames = "phone"),
		@UniqueConstraint(columnNames = "email")
})
public class Employer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long nif;
	private String address;
	private String phone;
	private String email;
	private Integer numberDepartment;
	
	@JsonIgnore
	@OneToMany(mappedBy = "employer")
	private List<Department> departments = new ArrayList<>();
	
	public Employer(Long id, String name, Long nif, String address, String phone,String email,Integer numberDepartment) {
		this.id = id;
		this.name = name;
		this.nif = nif;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.numberDepartment = numberDepartment;
	}
	
	public Employer(EmployerDTO employerDTO) {
		BeanUtils.copyProperties(employerDTO, this);
	}
	
	public List<Department> getDepartments() {
		return departments;
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

	public Long getNif() {
		return nif;
	}

	public void setNif(Long nif) {
		this.nif = nif;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getNumberDepartment() {
		return numberDepartment;
	}

	public void setNumberDepartment(Integer numberDepartment) {
		this.numberDepartment = numberDepartment;
	}
	
	public void incrementNumberDepartment() {
		if(this.departments == null) {
			this.numberDepartment = 0;
		}
		this.numberDepartment++;
	}
	
	public void decrementNumberDepartment() {
		if( this.departments == null) {
			this.numberDepartment = 0;
		}else if(this.numberDepartment> 0) {
			
			this.numberDepartment--;
		}
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
		Employer other = (Employer) obj;
		return Objects.equals(id, other.id);
	}

}
