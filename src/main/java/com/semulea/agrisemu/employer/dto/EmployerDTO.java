package com.semulea.agrisemu.employer.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.Employer;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployerDTO {
	
	private Long id;
	private String name;
	private Long nif;
	private String address;
	private String phone;
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

	public List<DepartmentDTO> getDepartments() {
		return departments;
	}

	public Integer getNumberDepartment() {
		return numberDepartment;
	}

	public void setNumberDepartment(Integer numberDepartment) {
		this.numberDepartment = numberDepartment;
	}


}
