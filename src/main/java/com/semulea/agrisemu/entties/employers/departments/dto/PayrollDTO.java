package com.semulea.agrisemu.entties.employers.departments.dto;

import java.util.List;

public class PayrollDTO {
	
	private String workerName;
	private Double netSalary;
	private int totalAbasences;
	private int justifiedAbsences;
	private Double salaryToReceive;
	private Double dailySalary;
	private List<String> absenceDates;
	
	public PayrollDTO(String workerName, Double netSalary, int totalAbasences, int justifiedAbsences,
			Double salaryToReceive, List<String> absenceDates) {
		
		this.workerName = workerName;
		this.netSalary = netSalary;
		this.totalAbasences = totalAbasences;
		this.justifiedAbsences = justifiedAbsences;
		this.salaryToReceive = salaryToReceive;
		this.absenceDates = absenceDates;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public Double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(Double netSalary) {
		this.netSalary = netSalary;
	}

	public int getTotalAbasences() {
		return totalAbasences;
	}

	public void setTotalAbasences(int totalAbasences) {
		this.totalAbasences = totalAbasences;
	}

	public int getJustifiedAbsences() {
		return justifiedAbsences;
	}

	public void setJustifiedAbsences(int justifiedAbsences) {
		this.justifiedAbsences = justifiedAbsences;
	}

	public Double getSalaryToReceive() {
		return salaryToReceive;
	}

	public void setSalaryToReceive(Double salaryToReceive) {
		this.salaryToReceive = salaryToReceive;
	}

	public List<String> getAbsenceDates() {
		return absenceDates;
	}

	public void setAbsenceDates(List<String> absenceDates) {
		this.absenceDates = absenceDates;
	}

	public Double getDailySalary() {
		return dailySalary;
	}

	public void setDailySalary(Double dailySalary) {
		this.dailySalary = dailySalary;
	}
	
}
