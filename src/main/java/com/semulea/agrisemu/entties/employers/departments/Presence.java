package com.semulea.agrisemu.entties.employers.departments;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.departments.dto.PresenceDTO;
import com.semulea.agrisemu.entties.employers.departments.enums.StateAbsence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "presences")
public class Presence implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant businessDay;
	private Boolean presence;
	
	private Integer stateAbsence;
	
	private String absenceJustification;
	
	@ManyToOne
	@JoinColumn(name = "worker_id", nullable = false)
	private Worker worker;
	
	public Presence() {
		
	}

	public Presence(Long id, Instant businessDay, Boolean presence,StateAbsence stateAbsence, Worker worker) {
	
		this.id = id;
		this.businessDay = businessDay;
		this.presence = presence;
		setStateAbsence(stateAbsence);
		this.worker = worker;
	}
	
	public Presence(PresenceDTO entity) {
		BeanUtils.copyProperties(entity, this);
		setStateAbsence(StateAbsence.valueOf(entity.getStateAbsence()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getBusinessDay() {
		return businessDay;
	}

	public void setBusinessDay(Instant businessDay) {
		this.businessDay = businessDay;
	}

	public Boolean getPresence() {
		return presence;
	}

	public void setPresence(Boolean presence) {
		this.presence = presence;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public StateAbsence getStateAbsence() {
		
			return StateAbsence.valueOf(stateAbsence);
	}

	public void setStateAbsence(StateAbsence stateAbsence) {
		if(stateAbsence != null) {
			this.stateAbsence = stateAbsence.getCode();
		}
	}

	public String getAbsenceJustification() {
		return absenceJustification;
	}

	public void setAbsenceJustification(String absenceJustification) {
		this.absenceJustification = absenceJustification;
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
		Presence other = (Presence) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
