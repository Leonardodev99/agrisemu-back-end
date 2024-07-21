package com.semulea.agrisemu.employer.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.Employer;
import com.semulea.agrisemu.validation.Nif;
import com.semulea.agrisemu.validation.PhoneFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployerDTO {
	
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;
	
	@NotBlank(message = "NF is mandatory")
	@Size(max = 10, message = "NF must have 10 digits in number")
	@Nif
	private String nif;
	
	@NotBlank(message = "Address is mandatory")
	@Size(min = 3, max = 400, message = "Address must be between 3 and 400 characters")
	private String address;
	
	@NotBlank(message = "Phone is mandatoy")
	@PhoneFormat
	private String phone;
	
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;
	
	private Integer numberDepartment;
	
	private List<DepartmentDTO> departments = new ArrayList<>();

	public EmployerDTO(Employer entity) {
		BeanUtils.copyProperties(entity, this);
		this.departments = entity.getDepartments().stream()
				.map(DepartmentDTO::new)
				.collect(Collectors.toList());
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

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
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

	public List<DepartmentDTO> getDepartments() {
		return departments;
	}

	public Integer getNumberDepartment() {
		return numberDepartment;
	}

	public void setNumberDepartment(Integer numberDepartment) {
		this.numberDepartment = numberDepartment;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
