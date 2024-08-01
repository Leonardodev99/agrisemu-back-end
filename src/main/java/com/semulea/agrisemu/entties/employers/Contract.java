package com.semulea.agrisemu.entties.employers;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.employer.dto.ContractDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contracts")
public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant initialDate;
	private Instant finalDate;
	private Double valuePerHour;
	private Long hoursPerDay;
	private Double extraHoursValue;
	private Double additionalValue;
	private Double taxIrt;
	
	
	@ManyToOne
	@JoinColumn(name = "worker_id")
	private Worker worker;
	
	public Contract() {
		
	}

	public Contract(Long id, Instant initialDate, Instant finalDate, Double valuePerHour, Long hoursPerDay, Double extraHoursValue,
			Double additionalValue, Double taxIrt, Worker worker) {
		this.id = id;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.valuePerHour = valuePerHour;
		this.hoursPerDay = hoursPerDay;
		this.extraHoursValue = extraHoursValue;
		this.additionalValue = additionalValue;
		this.taxIrt = taxIrt;
		this.worker = worker;
	}
	
	public Contract(ContractDTO entity) {
		BeanUtils.copyProperties(entity, this);
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
	
	public Double getTaxIrt() {
		return taxIrt;
	}

	public void setTaxIrt(Double taxIrt) {
		this.taxIrt = taxIrt;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
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
		Contract other = (Contract) obj;
		return Objects.equals(id, other.id);
	}
	public Double basySalaryPerMonth() {
		return valuePerHour*hoursPerDay*30;
	}
	public Double grossSalaryPerMonth( ) {
		return (extraHoursValue + additionalValue) + basySalaryPerMonth();
	}
	public Double itrTax() {
		return (grossSalaryPerMonth() * taxIrt)/100.0;
	}
}
