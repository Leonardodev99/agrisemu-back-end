package com.semulea.agrisemu.employer.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.Contract;
import com.semulea.agrisemu.entties.employers.Worker;

public class ContractDTO {
	
	private Long id;
	private String initialDate;
	private String finalDate;
	private Double valuePerHour;
	private Long hoursPerDay;
	private Double extraHoursValue;
	private Double additionalValue;
	private Long workerId;
	
	private Worker worker;
	
	public ContractDTO() {
		
	}
	
	public ContractDTO(Contract entity) {
		BeanUtils.copyProperties(entity, this);
		if(entity.getWorker() != null) {
			this.workerId = entity.getWorker().getId();
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
				.withZone(ZoneId.systemDefault());
		this.initialDate = formatter.format(entity.getInitialDate());
		this.finalDate = formatter.format(entity.getFinalDate());

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

	public Double getValuePerHour() {
		return valuePerHour;
	}

	public void setValuePerHour(Double valuePerHour) {
		this.valuePerHour = valuePerHour;
	}

	public Long getHoursPerDay() {
		return hoursPerDay;
	}

	public void setHoursPerDay(Long hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}

	public Double getExtraHoursValue() {
		return extraHoursValue;
	}

	public void setExtraHoursValue(Double extraHoursValue) {
		this.extraHoursValue = extraHoursValue;
	}

	public Double getAdditionalValue() {
		return additionalValue;
	}

	public void setAdditionalValue(Double additionalValue) {
		this.additionalValue = additionalValue;
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
	public Instant getInitialDateAsInstant() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime localDateTime = LocalDateTime.parse(this.initialDate, formatter);
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
	}
	public Instant getFinalDateAsInstant() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime localDateTime = LocalDateTime.parse(this.finalDate, formatter);
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
	}
	
}
