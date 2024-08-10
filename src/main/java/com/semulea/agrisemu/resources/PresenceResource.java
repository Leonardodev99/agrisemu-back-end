package com.semulea.agrisemu.resources;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semulea.agrisemu.entties.employers.departments.dto.JustifyAbsenceDTO;
import com.semulea.agrisemu.entties.employers.departments.dto.PresenceDTO;
import com.semulea.agrisemu.entties.employers.departments.dto.WorkerPresenceAbsenceDTO;
import com.semulea.agrisemu.services.PresenceService;

@RestController
@RequestMapping(value = "/presences")
public class PresenceResource {
    
    @Autowired
    private PresenceService presenceService;
    
    
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
    
    @GetMapping("/report")
    public ResponseEntity<List<WorkerPresenceAbsenceDTO>> getPresencesAbsencesReport(@RequestParam String startDate, @RequestParam String endDate) {
        
    	 Instant startInstant = convertToInstant(startDate);
         Instant endInstant = convertToInstant(endDate);
    	
         List<WorkerPresenceAbsenceDTO> report = presenceService.getPresenceAbsenceReportForAllworker(startInstant, endInstant);
        
        return ResponseEntity.ok(report);
    
    }
    
    private Instant convertToInstant(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }
    
    @PostMapping("/justify")
    public ResponseEntity<PresenceDTO> justifyAbsence(@RequestBody JustifyAbsenceDTO justifyAbsenceDTO) {
    	PresenceDTO justifiedPresence = presenceService.justifyAbsence(justifyAbsenceDTO);
    	return ResponseEntity.ok(justifiedPresence);
    }
}

