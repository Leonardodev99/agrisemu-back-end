package com.semulea.agrisemu.employer.dto;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.enums.Sex;
import com.semulea.agrisemu.entties.employers.enums.StatusCivic;
import com.semulea.agrisemu.entties.employers.enums.TypeContract;
import com.semulea.agrisemu.entties.employers.enums.WorkerLevel;

import lombok.AllArgsConstructor;


@AllArgsConstructor

public class WorkerDTO {
	
	private Long id;
	private String name;
	private String bi;
	private String phone;
	private String email;
	private String address;
	private String dateOfBirth;
	private String nationality;
	private Sex sex;
	private StatusCivic statusCivic;
	private String education;
	private WorkerLevel level;
	private TypeContract typeContract;
	private Double basySalary;
	private Double irt;
	private Double grossSalary;
	
	private List<DepartmentDTO> departmentsDTO = new ArrayList<>();

	public WorkerDTO() {
		
	}
	
	public WorkerDTO(Worker entity) {
		 	this.id = entity.getId();
	        this.name = entity.getName();
	        this.bi = entity.getBi();
	        this.phone = entity.getPhone();
	        this.email = entity.getEmail();
	        this.address = entity.getAddress();
	        
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        this.dateOfBirth = formatter.format(entity.getDateOfBirth().atZone(ZoneId.systemDefault()).toLocalDate());
	        
	        this.nationality = entity.getNationality();
	        this.sex = entity.getSex();
	        this.statusCivic = entity.getStatusCivic();
	        this.education = entity.getEducation();
	        this.level = entity.getLevel();
	        this.typeContract = entity.getTypeContract();
	        this.basySalary = entity.getBasySalary();
	        this.irt = entity.getIrt();
	        this.grossSalary = entity.getGrossSalary();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBi() {
		return bi;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public Sex getSex() {
		return sex;
	}

	public StatusCivic getStatusCivic() {
		return statusCivic;
	}

	public String getEducation() {
		return education;
	}

	public WorkerLevel getLevel() {
		return level;
	}

	public TypeContract getTypeContract() {
		return typeContract;
	}

	public Double getBasySalary() {
		return basySalary;
	}

	public Double getIrt() {
		return irt;
	}

	public Double getGrossSalary() {
		return grossSalary;
	}

	public List<DepartmentDTO> getDepartmentsDTO() {
		return departmentsDTO;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBi(String bi) {
		this.bi = bi;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setStatusCivic(StatusCivic statusCivic) {
		this.statusCivic = statusCivic;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setWorkerLevel(WorkerLevel level) {
		this.level = level;
	}

	public void setTypeContract(TypeContract typeContract) {
		this.typeContract = typeContract;
	}

	public void setBasySalary(Double basySalary) {
		this.basySalary = basySalary;
	}

	public void setIrt(Double irt) {
		this.irt = irt;
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}




}
