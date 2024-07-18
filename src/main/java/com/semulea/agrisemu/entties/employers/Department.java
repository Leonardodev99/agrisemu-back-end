package com.semulea.agrisemu.entties.employers;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.employer.dto.DepartmentDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "departments")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer numberWorkers;
	
	
	@ManyToOne
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	public Department(DepartmentDTO departmentDTO) {
		BeanUtils.copyProperties(departmentDTO, this);
	}

}
