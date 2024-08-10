package com.semulea.agrisemu.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.departments.Presence;
import com.semulea.agrisemu.entties.employers.departments.dto.JustifyAbsenceDTO;
import com.semulea.agrisemu.entties.employers.departments.dto.PresenceDTO;
import com.semulea.agrisemu.entties.employers.departments.dto.WorkerPresenceAbsenceDTO;
import com.semulea.agrisemu.entties.employers.departments.enums.StateAbsence;
import com.semulea.agrisemu.repositories.PresenceRepository;
import com.semulea.agrisemu.repositories.WorkerRepository;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

@Service
public class PresenceService {
	
	@Autowired
	private PresenceRepository presenceRepository;
	
	@Autowired
	private WorkerRepository workerRepository;
	
	public PresenceDTO registerPresence(Long workerId, Instant businessDayInstant, Boolean present, String stateAbsence) {
		Worker worker  = workerRepository.findById(workerId)
				.orElseThrow(() -> new ResourceNotFoundException(workerId));
		
		Presence presence = new Presence();
		presence.setWorker(worker);
		presence.setBusinessDay(businessDayInstant);
		presence.setPresence(present);
		presence.setStateAbsence(StateAbsence.valueOf(stateAbsence));
	        
	    presence = presenceRepository.save(presence);
		return new PresenceDTO(presence);
	}
	
	
	public List<PresenceDTO> findPresencesByWorker(Worker worker) {
		List<Presence> presences = presenceRepository.findAllByWorker(worker);
		return presences.stream()
				.map(PresenceDTO::new)
				.collect(Collectors.toList());
	}

	public List<WorkerPresenceAbsenceDTO> getPresenceAbsenceReportForAllworker(Instant start, Instant end) {
		List<Presence> presences = presenceRepository.findPresencesByPeriod(start, end);
		
		Map<Worker, List<Presence>> groupedByWorker = presences.stream().collect(Collectors.groupingBy(Presence::getWorker));
		
		return groupedByWorker.entrySet().stream().map(entry -> {
			Worker worker = entry.getKey();
			List<Presence> workerPresences = entry.getValue();
			
			int presencesCount = (int) workerPresences.stream().filter(Presence::getPresence).count();
			int absencesCount = workerPresences.size() - presencesCount;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault());
			
			List<String> presenceDates = workerPresences.stream()
					.filter(Presence::getPresence)
					.map(p -> formatter.format(p.getBusinessDay()))
					.collect(Collectors.toList());
			
			List<String> absenceDates = workerPresences.stream()
					.filter(p -> !p.getPresence())
					.map(p -> {
						String formattedDate = formatter.format(p.getBusinessDay());
						String justificationState = (p.getAbsenceJustification() != null)
								? "JUSTIFIED"
								: "UNJUSTIFIED";
						return formattedDate + " (" + justificationState + ")";
					})
					 .collect(Collectors.toList());
			
			return new WorkerPresenceAbsenceDTO(
					worker.getName(),
					presencesCount,
					absencesCount,
					presenceDates,
					absenceDates
					);
		}).collect(Collectors.toList());
	}
	
	public PresenceDTO justifyAbsence(JustifyAbsenceDTO justifyAbsenceDTO) {
		Presence presence = presenceRepository.findById(justifyAbsenceDTO.getPresenceId())
				.orElseThrow(() -> new ResourceNotFoundException(justifyAbsenceDTO.getPresenceId()));
		if(!presence.getPresence()) {
			presence.setAbsenceJustification(justifyAbsenceDTO.getJustification());
			presence = presenceRepository.save(presence);
			
		} else {
			throw new IllegalArgumentException("A presença não pode ser justificada");
		}
		
		return new PresenceDTO(presence);
	}
}
