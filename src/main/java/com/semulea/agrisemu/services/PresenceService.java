package com.semulea.agrisemu.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.departments.Presence;
import com.semulea.agrisemu.entties.employers.departments.dto.PresenceDTO;
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
		
		 /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	     Instant businessDayInstant = LocalDateTime.parse(businessDay, formatter).atZone(ZoneId.systemDefault()).toInstant();*/
		
		
		
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
	
	public int countAbsencesByWorkerAndPeriod(Worker worker, Instant start, Instant end) {
		return presenceRepository.countAbsencesByWorkerAndBusinessDayBetween(worker, start, end);
	}

}
