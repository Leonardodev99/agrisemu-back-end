package com.semulea.agrisemu.entties.employers.departments.dto;

public class PresenceCountDTO {
	
	private int presences;
	private int absences;
	
	public PresenceCountDTO() {
		
	}

	public PresenceCountDTO(int presences, int absences) {
		
		this.presences = presences;
		this.absences = absences;
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

}
