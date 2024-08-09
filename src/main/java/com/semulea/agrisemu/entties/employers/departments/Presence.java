package com.semulea.agrisemu.entties.employers.departments;

import java.time.Instant;

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
public class Presence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Instant businessDay;
	private Boolean presence;
	
	private Integer stateAbsence;
	
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
	
}
