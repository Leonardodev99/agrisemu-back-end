package com.semulea.agrisemu.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semulea.agrisemu.employer.dto.EmployerDTO;
import com.semulea.agrisemu.services.EmployerService;

@RestController
@RequestMapping(value = "/employers")
public class EmployerResource {
	
	
	@Autowired
	private EmployerService employerService;
	
	@GetMapping
	public ResponseEntity<List<EmployerDTO>> findAll() {
		List<EmployerDTO> list = employerService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<EmployerDTO> findById(@PathVariable Long id) {
		EmployerDTO employer = employerService.findById(id);
		return ResponseEntity.ok().body(employer);
	}
	
	@PostMapping
	public ResponseEntity<EmployerDTO> insert(@RequestBody EmployerDTO employer) {
		employer = employerService.insert(employer);
		return ResponseEntity.ok().body(employer);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EmployerDTO> update(@PathVariable Long id, @RequestBody EmployerDTO employer) {
		employer = employerService.update(id, employer);
		return ResponseEntity.ok().body(employer);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		employerService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
