package com.semulea.agrisemu.entties.employers.departments.dto;

public class JustifyAbsenceDTO {
	
	private Long presenceId;
	private String justification;
	
	public JustifyAbsenceDTO() {
		
	}

	public JustifyAbsenceDTO(Long presenceId, String justification) {
		
		this.presenceId = presenceId;
		this.justification = justification;
	}

	public Long getPresenceId() {
		return presenceId;
	}

	public void setPresenceId(Long presenceId) {
		this.presenceId = presenceId;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
}
