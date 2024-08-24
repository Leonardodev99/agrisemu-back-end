package com.semulea.agrisemu.entties.employers.departments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class JustifyAbsenceDTO {
	
	@NotNull
	private Long presenceId;
	
	@NotBlank(message = "Field justification is mandatory")
	@Size(min = 4, max = 50, message = "Name must be between 3 and 50 characters")
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
