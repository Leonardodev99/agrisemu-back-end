package com.semulea.agrisemu.employer.dto;

import java.time.Instant;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.Contract;
import com.semulea.agrisemu.entties.employers.Worker;

public class ContractDTO {
	
	private Long id;
	private Instant initialDate;
	private Instant finalDate;
	private Double perHour;
	private Long hoursPerDay;
	private Long extraHours;
	private Double additionalValue;
	private Integer numberContract;
	private Long workerId;
	
	private Worker worker;
	
	public ContractDTO() {
		
	}
	
	
	
	public ContractDTO(Contract entity) {
		BeanUtils.copyProperties(entity, this);
		if(entity.getWorker() != null) {
			this.workerId = entity.getWorker().getId();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Instant initialDate) {
		this.initialDate = initialDate;
	}

	public Instant getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Instant finalDate) {
		this.finalDate = finalDate;
	}

	public Double getPerHour() {
		return perHour;
	}

	public void setPerHour(Double perHour) {
		this.perHour = perHour;
	}

	public Long getHoursPerDay() {
		return hoursPerDay;
	}

	public void setHoursPerDay(Long hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}

	public Long getExtraHours() {
		return extraHours;
	}

	public void setExtraHours(Long extraHours) {
		this.extraHours = extraHours;
	}

	public Double getAdditionalValue() {
		return additionalValue;
	}

	public void setAdditionalValue(Double additionalValue) {
		this.additionalValue = additionalValue;
	}

	public Integer getNumberContract() {
		return numberContract;
	}

	public void setNumberContract(Integer numberContract) {
		this.numberContract = numberContract;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}
	
}
