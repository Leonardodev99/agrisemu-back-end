package com.semulea.agrisemu.resources;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.semulea.agrisemu.entties.employers.departments.dto.PayrollDTO;
import com.semulea.agrisemu.services.PayrollService;

@RestController
@RequestMapping(value = "/payrolls")
public class PayrollResource {
	
	@Autowired
	PayrollService payrollService;
	
	@GetMapping("/generate")
	public ResponseEntity<List<PayrollDTO>> generatePayroll(@RequestParam String startDate,@RequestParam String endDate){
		Instant startInstant = convertToInstant(startDate);
		Instant endInstante = convertToInstant(endDate);
		
		List<PayrollDTO> payrolls = payrollService.generatePayroll(startInstant, endInstante);
		
		return ResponseEntity.ok(payrolls);
	}
	
	private Instant convertToInstant(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
	}

}
