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
	private Double perHour;
	private Long hoursPerDay;
	private Long extraHours;
	private Double additionalValue;
	private Integer numberContract;
	
	
	@ManyToOne
	@JoinColumn(name = "worker_id")
	private Worker worker;
	
	public Contract() {
		
	}

	public Contract(Long id, Instant initialDate, Instant finalDate, Double perHour, Long hoursPerDay, Long extraHours,
			Double additionalValue, Integer numberContract, Worker worker) {
		this.id = id;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.perHour = perHour;
		this.hoursPerDay = hoursPerDay;
		this.extraHours = extraHours;
		this.additionalValue = additionalValue;
		this.numberContract = numberContract;
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
}
