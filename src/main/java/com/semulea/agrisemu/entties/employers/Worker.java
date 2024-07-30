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
import com.semulea.agrisemu.employer.dto.WorkerDTO;
import com.semulea.agrisemu.entties.employers.enums.Sex;
import com.semulea.agrisemu.entties.employers.enums.StatusCivic;
import com.semulea.agrisemu.entties.employers.enums.TypeContract;
import com.semulea.agrisemu.entties.employers.enums.WorkerLevel;
import com.semulea.agrisemu.validation.BiValidation;
import com.semulea.agrisemu.validation.PhoneFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "workers", uniqueConstraints = {
	    @UniqueConstraint(columnNames = "bi"),
	    @UniqueConstraint(columnNames = "phone"),
	    @UniqueConstraint(columnNames = "email")})
public class Worker implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;
	
	@NotBlank(message = "Name is mandatory")
	@BiValidation
	private String bi;
	
	@NotBlank(message = "Phone is mandatoy")
	@PhoneFormat
	private String phone;
	
	private String email;
	
	@NotBlank(message = "Address is mandatory")
	@Size(min = 4, max = 50, message = "Address must be between 4 and 50 characters")
	private String address;
	
	private Instant dateOfBirth;
	
	@NotBlank(message = "Nationality is mandatory")
	@Size(min = 4, max = 50, message = "Nationality must be between 4 and 50 characters")
	private String nationality;
	
	private Integer sex;
	
	private Integer statusCivic;
	
	@NotBlank(message = "Nationality is mandatory")
	@Size(min = 4, max = 50, message = "Nationality must be between 3 and 50 characters")
	private String education;
	
	private Integer level;
	
	private Integer typeContract;
	
	
	private Double basySalary;
	
	@NotNull(message = "IRT not be null")
	private Double irt;
	
	@NotNull(message = "Gross salary not be null")
	private Double grossSalary;
	
	private Integer numberContract = 0;
	
	@ManyToMany
	@JoinTable(name = "worker_department", 
	joinColumns = @JoinColumn(name = "worker_id"),
	inverseJoinColumns = @JoinColumn(name = "department_id"))
	private Set<Department> departments = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "worker")
	private List<Contract> contracts = new ArrayList<>();
	
	public Worker() {
		
	}

	public Worker(Long id, String name, String bi,String phone,String email, String address, Instant dateOfBirth, String nationality, Sex sex,StatusCivic statusCivic,
			String education, WorkerLevel level, TypeContract typeContract, Double basySalary, Double irt,
			Double grossSalary, Integer numberContract) {
		this.id = id;
		this.name = name;
		this.bi = bi;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.nationality = nationality;
		setSex(sex);
		setStatusCivic(statusCivic);
		this.education = education;
		setWorkerLevel(level);
		setTypeContract(typeContract);
		this.basySalary = basySalary;
		this.irt = irt;
		this.grossSalary = grossSalary;
		this.numberContract = numberContract;
	}
	
	public Worker(WorkerDTO entity) {
		BeanUtils.copyProperties(entity, this);
		setSex(entity.getSex());
		setStatusCivic(entity.getStatusCivic());
		setWorkerLevel(entity.getLevel());
		setTypeContract(entity.getTypeContract());
		this.numberContract = entity.getNumberContract();
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

	public String getBi() {
		return bi;
	}

	public void setBi(String bi) {
		this.bi = bi;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Instant getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Instant dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Sex getSex() {
		return Sex.valueOf(sex);
	}

	public void setSex(Sex sex) {
		if(sex != null) {
			this.sex = sex.getCode();
		}
	}
	public StatusCivic getStatusCivic() {
		return StatusCivic.valueOf(statusCivic);
	}

	public void setStatusCivic(StatusCivic statusCivic) {
		
		if(statusCivic != null) {
			this.statusCivic = statusCivic.getCode();
		}
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public WorkerLevel getLevel() {
		return WorkerLevel.valueOf(level);
	}

	public void setWorkerLevel(WorkerLevel level) {
		if(level != null) {
			this.level = level.getCode();
		}
	}

	public TypeContract getTypeContract() {
		
		return TypeContract.valueOf(typeContract);
	}

	public void setTypeContract(TypeContract typeContract) {
		if(typeContract != null) {
			this.typeContract = typeContract.getCode();
		}
	}

	public Double getBasySalary() {
		return basySalary;
	}

	public void setBasySalary(Double basySalary) {
		this.basySalary = basySalary;
	}

	public Double getIrt() {
		return irt;
	}

	public void setIrt(Double irt) {
		this.irt = irt;
	}

	public Double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public Set<Department> getDepartments() {
		return departments;
	}
	
	public List<Contract> getContracts() {
		return contracts;
	}
	

	public Integer getNumberContract() {
		return numberContract;
	}

	public void setNumberContract(Integer numberContract) {
		this.numberContract = numberContract;
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
		Worker other = (Worker) obj;
		return Objects.equals(id, other.id);
	}
	public void incrementNumberContract() {
		this.numberContract = (this.numberContract != null) ? this.numberContract :0;
		this.numberContract++;
	}
	public void decrementNumberContract() {
		this.numberContract = (this.numberContract != null) ? this.numberContract :0;
		 if(this.numberContract > 0) {
			this.numberContract--;
		}
	}
	public void updateBasySalary() {
		if(contracts != null && !contracts.isEmpty()) {
			this.basySalary = contracts.stream()
					.mapToDouble(Contract::basySalaryPerMonth)
					.sum();
		} else {
			this.basySalary = 0.0;
		}
	}
}
