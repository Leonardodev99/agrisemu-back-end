package com.semulea.agrisemu.employer.dto;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.enums.Sex;
import com.semulea.agrisemu.entties.employers.enums.StatusCivic;
import com.semulea.agrisemu.entties.employers.enums.TypeContract;
import com.semulea.agrisemu.entties.employers.enums.WorkerLevel;
import com.semulea.agrisemu.validation.BiValidation;
import com.semulea.agrisemu.validation.EnumValidation;
import com.semulea.agrisemu.validation.PhoneFormat;
import com.semulea.agrisemu.validation.ValidDateOfBirth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

@AllArgsConstructor

public class WorkerDTO {

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

	@NotBlank(message = "Date of birth is mandatory")
	@ValidDateOfBirth
	private String dateOfBirth;

	@NotBlank(message = "Nationality is mandatory")
	@Size(min = 4, max = 50, message = "Nationality must be between 4 and 50 characters")
	private String nationality;

	@EnumValidation(enumClass = Sex.class, message = "Invalid sex value")
	private String sex;

	@EnumValidation(enumClass = StatusCivic.class, message = "Invalid status civic value")
	private String statusCivic;

	@NotBlank(message = "Nationality is mandatory")
	@Size(min = 4, max = 50, message = "Nationality must be between 3 and 50 characters")
	private String education;

	@EnumValidation(enumClass = WorkerLevel.class, message = "Invalid worker level value")
	private String level;

	@EnumValidation(enumClass = TypeContract.class, message = "Invalid type contract value")
	private String typeContract;

	private Double basySalary;

	private Double irt;

	private Double grossSalary;

	private Integer numberContract;

	private Double totalValueContract;

	private Double netSalary;
	
	private Long durationContract;

	private Set<DepartmentDTO> departmentsDTO = new HashSet<>();

	@NotNull(message = "Id of department not be null")
	private Long departmentId;

	private List<ContractDTO> contracts = new ArrayList<>();

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
		this.sex = entity.getSex().toString();
		this.statusCivic = entity.getStatusCivic().toString();
		this.education = entity.getEducation();
		this.level = entity.getLevel().toString();
		this.typeContract = entity.getTypeContract().toString();
		this.basySalary = entity.getBasySalary();
		this.irt = entity.getIrt();
		this.grossSalary = entity.getGrossSalary();
		this.numberContract = entity.getNumberContract();
		this.totalValueContract = entity.getTotalValueContract();
		this.netSalary = entity.getNetSalary();
		this.durationContract = entity.getDurationContract();

		this.departmentsDTO = entity.getDepartments().stream().map(DepartmentDTO::new).collect(Collectors.toSet());

		this.contracts = entity.getContracts().stream().map(ContractDTO::new).collect(Collectors.toList());
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

	public String getSex() {
		return sex;
	}

	public String getStatusCivic() {
		return statusCivic;
	}

	public String getEducation() {
		return education;
	}

	public String getLevel() {
		return level;
	}

	public String getTypeContract() {
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

	public Set<DepartmentDTO> getDepartmentsDTO() {
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

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setStatusCivic(String statusCivic) {
		this.statusCivic = statusCivic;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setWorkerLevel(String level) {
		this.level = level;
	}

	public void setTypeContract(String typeContract) {
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

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public List<ContractDTO> getContracts() {
		return contracts;
	}

	public Integer getNumberContract() {
		return numberContract;
	}

	public void setNumberContract(Integer numberContract) {
		this.numberContract = numberContract;
	}

	public Double getTotalValueContract() {
		return totalValueContract;
	}

	public void setTotalValueContract(Double totalValueContract) {
		this.totalValueContract = totalValueContract;
	}

	public Double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(Double netSalary) {
		this.netSalary = netSalary;
	}

	public Long getDurationContract() {
		return durationContract;
	}

	public void setDurationContract(Long durationContract) {
		this.durationContract = durationContract;
	}

}
