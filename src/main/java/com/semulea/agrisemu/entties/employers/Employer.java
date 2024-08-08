package com.semulea.agrisemu.entties.employers;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.semulea.agrisemu.employer.dto.EmployerDTO;
import com.semulea.agrisemu.validation.Nif;
import com.semulea.agrisemu.validation.PhoneFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "employers", uniqueConstraints = { @UniqueConstraint(columnNames = "name"),
		@UniqueConstraint(columnNames = "nif"), @UniqueConstraint(columnNames = "phone"),
		@UniqueConstraint(columnNames = "email") })
public class Employer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	private Integer numberDepartment = 0;
	
	private Instant registrationDate;

	@JsonIgnore
	@OneToMany(mappedBy = "employer")
	private List<Department> departments = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany(mappedBy = "employers")
	private Set<CompanySector> companySectors = new HashSet<>();

	public Employer(Long id, String name, String nif, String address, String phone, String email,
			Integer numberDepartment, Instant registrationDate) {
		this.id = id;
		this.name = name;
		this.nif = nif;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.numberDepartment = numberDepartment;
		this.registrationDate = registrationDate;
	}

	public Employer(EmployerDTO employerDTO) {
		BeanUtils.copyProperties(employerDTO, this);
	}

	
	 public List<Department> getDepartments() { return departments; }
	 

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
	
	public Instant getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Instant registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	public Set<CompanySector> getCompanySectors() {
		return companySectors;
	}

	
	public void incrementNumberDepartment() {
		this.numberDepartment = (this.numberDepartment != null) ? this.numberDepartment :0;
		this.numberDepartment++;
	}
	public void decrementNumberDepartment() {
		this.numberDepartment = (this.numberDepartment != null) ? this.numberDepartment :0;
		 if(this.numberDepartment > 0) {
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
