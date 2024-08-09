package com.semulea.agrisemu.entties.employers.departments.dto;

import jakarta.validation.constraints.NotNull;

public class AbsenceDTO {
	
	@NotNull(message = "startDate cannot be null")
    private String startDate;

    @NotNull(message = "endDate cannot be null")
    private String endDate;

    public AbsenceDTO() {
    }

    public AbsenceDTO(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
