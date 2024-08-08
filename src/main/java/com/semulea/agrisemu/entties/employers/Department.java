package com.semulea.agrisemu.entties.employers;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.semulea.agrisemu.employer.dto.DepartmentDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "departments")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	@Size(min = 2, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;
	
	private String leader;
	
	private String phone;
	
	private String email;

	private Integer numberWorkers = 0;
	
	@ManyToOne
	@JoinColumn(name = "employer_id")
	private Employer employer;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "departments")
	private Set<Worker> workers = new HashSet<>();
	
	public Department(Long id, String name,Integer numberWorkers, Employer employer, String leader, String phone, String email) {
		this.id = id;
		this.name = name;
		this.numberWorkers = numberWorkers;
		this.employer = employer;
		this.leader = leader;
		this.phone = phone;
		this.email = email;
	}
	public Department(DepartmentDTO departmentDTO) {
		BeanUtils.copyProperties(departmentDTO, this);
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
	public void setEmployer(Employer employer) {
		this.employer = employer;
	}
	public void setLeader(String leader) {
		this.leader = leader;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void incrementNumberWorkers() {
		this.numberWorkers = (this.numberWorkers != null) ? this.numberWorkers :0;
		this.numberWorkers++;
	}
	public void decrementNumberWorkers() {
		this.numberWorkers = (this.numberWorkers != null) ? this.numberWorkers :0;
		 if(this.numberWorkers > 0) {
			this.numberWorkers--;
		}
	}

}
