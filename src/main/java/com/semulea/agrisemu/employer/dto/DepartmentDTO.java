package com.semulea.agrisemu.employer.dto;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.Department;
import com.semulea.agrisemu.entties.employers.Employer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Integer numberWorkers;
	private Long employerId;
	
	private Employer employer;
	
	public DepartmentDTO(Department entity) {
		BeanUtils.copyProperties(entity, this);
		this.employerId = entity.getEmployer().getId();
	}

}
