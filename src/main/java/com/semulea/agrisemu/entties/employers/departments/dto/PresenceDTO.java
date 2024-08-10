package com.semulea.agrisemu.entties.employers.departments.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.departments.Presence;

public class PresenceDTO {
	
	private Long id;
	private String businessDay;
	private Boolean presence;
	private String absenceJustification;
	
	private String stateAbsence;
	
	public PresenceDTO() {
		
	}
	
	public PresenceDTO(Presence entity) {
		BeanUtils.copyProperties(entity, this);
		this.stateAbsence = entity.getStateAbsence().toString();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
	                .withZone(ZoneId.systemDefault());
	        this.businessDay = formatter.format(entity.getBusinessDay());
	        this.absenceJustification = entity.getAbsenceJustification();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessDay() {
		return businessDay;
	}

	public void setBusinessDay(String businessDay) {
		this.businessDay = businessDay;
	}

	public Boolean getPresence() {
		return presence;
	}

	public void setPresence(Boolean presence) {
		this.presence = presence;
	}

	public String getStateAbsence() {
		return stateAbsence;
	}

	public void setStateAbsence(String stateAbsence) {
		this.stateAbsence = stateAbsence;
	}
	 public String getAbsenceJustification() {
		return absenceJustification;
	}

	public void setAbsenceJustification(String absenceJustification) {
		this.absenceJustification = absenceJustification;
	}

	public Instant getBusinessDayAsInstant() {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	        LocalDateTime localDateTime = LocalDateTime.parse(this.businessDay, formatter);
	        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
	}
}
