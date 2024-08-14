package com.semulea.agrisemu.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.departments.Presence;
import com.semulea.agrisemu.entties.employers.departments.dto.PayrollDTO;
import com.semulea.agrisemu.entties.employers.departments.enums.StateAbsence;
import com.semulea.agrisemu.repositories.PresenceRepository;
import com.semulea.agrisemu.repositories.WorkerRepository;

@Service
public class PayrollService {
	
	@Autowired
	private PresenceRepository presenceRepository;
	
	@Autowired
	private WorkerRepository workerRepository;
	
	public List<PayrollDTO> generatePayroll(Instant start, Instant end) {
		List<Worker> workers = workerRepository.findAll();
		List<PayrollDTO> payrolls = new ArrayList<>();
		
		for(Worker worker : workers) {
			List<Presence> presences = presenceRepository.findPresencesByWorkerAndPeriod(worker, start, end);
			
			 int totalAbsences = (int) presences.stream().filter(p -> !p.getPresence()).count();
	            int justifiedAbsences = (int) presences.stream()
	                .filter(p -> !p.getPresence() && p.getStateAbsence() == StateAbsence.JUSTIFIED)
	                .count();
	            
			int nonJustifiedAbsences = totalAbsences - justifiedAbsences;
			Double netSalary = worker.getNetSalary();
			
			Double salaryToReceive = nonJustifiedAbsences > 0 ? netSalary - nonJustifiedAbsences : netSalary;
			
			List<String> absenceDates = presences.stream()
					.filter(p -> !p.getPresence())
					.map(p -> {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault());
						return formatter.format(p.getBusinessDay()) + " (" + p.getStateAbsence() + ")";
					})
					.collect(Collectors.toList());
			PayrollDTO payrollDTO = new PayrollDTO(worker.getName(), netSalary, justifiedAbsences, nonJustifiedAbsences, salaryToReceive, absenceDates);
			payrolls.add(payrollDTO);
		}
		
		return payrolls;
	}

}
