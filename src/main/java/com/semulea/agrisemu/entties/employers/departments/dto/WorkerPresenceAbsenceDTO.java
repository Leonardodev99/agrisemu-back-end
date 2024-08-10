package com.semulea.agrisemu.entties.employers.departments.dto;

import java.util.List;

public class WorkerPresenceAbsenceDTO {
	
	private  String workerName;
	private int presences;
	private int absences;
	private List<String> presenceDates;
	private List<String> absenceDates;
	
	public WorkerPresenceAbsenceDTO() {
		
	}

	public WorkerPresenceAbsenceDTO(String workerName, int presences, int absences, List<String> presenceDates, List<String> absenceDates) {
		
		this.workerName = workerName;
		this.presences = presences;
		this.absences = absences;
		this.presenceDates = presenceDates;
		this.absenceDates = absenceDates;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public int getPresences() {
		return presences;
	}

	public void setPresences(int presences) {
		this.presences = presences;
	}

	public int getAbsences() {
		return absences;
	}

	public void setAbsences(int absences) {
		this.absences = absences;
	}

	public List<String> getPresenceDates() {
		return presenceDates;
	}

	public void setPresenceDates(List<String> presenceDates) {
		this.presenceDates = presenceDates;
	}

	public List<String> getAbsenceDates() {
		return absenceDates;
	}

	public void setAbsenceDates(List<String> absenceDates) {
		this.absenceDates = absenceDates;
	}
	
}
