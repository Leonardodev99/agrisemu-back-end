package com.semulea.agrisemu.resources;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semulea.agrisemu.employer.dto.WorkerDTO;
import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.departments.dto.AbsenceDTO;
import com.semulea.agrisemu.entties.employers.departments.dto.PresenceDTO;
import com.semulea.agrisemu.services.PresenceService;
import com.semulea.agrisemu.services.WorkerService;

@RestController
@RequestMapping(value = "/presences")
public class PresenceResource {
    
    @Autowired
    private PresenceService presenceService;
    
    @Autowired
    private WorkerService workerService;
    
    @PostMapping("/{workerId}/register")
    public ResponseEntity<PresenceDTO> registerPresence(@PathVariable Long workerId,
                                                        @RequestBody PresenceDTO presenceDTO) {
        
     
    	  Instant businessDayInstant = presenceDTO.getBusinessDayAsInstant();
    	
    	PresenceDTO createdPresence = presenceService.registerPresence(
            workerId,
            businessDayInstant, 
            presenceDTO.getPresence(),
            presenceDTO.getStateAbsence()
        );
        
        return ResponseEntity.ok(createdPresence);
    }
    
    @PostMapping("/{workerId}/absences")
    public ResponseEntity<Integer> countAbsencesByWorkerAndPeriod(@PathVariable Long workerId,
                                                                  @RequestBody AbsenceDTO absencedto) {
        
    	 Instant startInstant = convertToInstant(absencedto.getStartDate());
         Instant endInstant = convertToInstant(absencedto.getEndDate());
    	
    	WorkerDTO workerDTO = workerService.findById(workerId);
        Worker worker = new Worker(workerDTO);
        
        int absences = presenceService.countAbsencesByWorkerAndPeriod(worker, startInstant, endInstant);
        return ResponseEntity.ok(absences);
    }
    
    private Instant convertToInstant(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }
}

