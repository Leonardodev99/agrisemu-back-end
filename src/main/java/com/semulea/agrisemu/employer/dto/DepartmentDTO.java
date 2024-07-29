package com.semulea.agrisemu.employer.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.Department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class DepartmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	@Size(min = 2, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;
	
	@NotNull(message = "Number workers not be null")
	private Integer numberWorkers;
	
	
	@NotNull(message = "Id of employer not be null")
	private Long employerId;
	
	private EmployerDTO employerDTO;
	
	private Set<WorkerDTO> workersDTO = new HashSet<>();
	
	
	public DepartmentDTO(Long id,String name,Integer numberWorkers, Long employerId, EmployerDTO employerDTO) {
		this.id = id;
		this.name = name;
		this.numberWorkers = numberWorkers;
		this.employerId = employerId;
		this.employerDTO = employerDTO;
	}

	
	public DepartmentDTO(Department entity) {
		BeanUtils.copyProperties(entity, this);
		this.employerId = entity.getEmployer().getId();
	}


	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setNumberWorkers(Integer numberWorkers) {
		this.numberWorkers = numberWorkers;
	}


	public void setEmployerId(Long employerId) {
		this.employerId = employerId;
	}


	public void setEmployerDTO(EmployerDTO employerDTO) {
		this.employerDTO = employerDTO;
	}
	
}
