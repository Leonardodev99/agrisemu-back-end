package com.semulea.agrisemu.resources;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.semulea.agrisemu.employer.dto.WorkerDTO;
import com.semulea.agrisemu.services.WorkerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
	
	@Autowired
	private WorkerService workerService;
	
	@GetMapping
	public ResponseEntity<List<WorkerDTO>> findAll() {
		List<WorkerDTO> list = workerService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<WorkerDTO> findById(@PathVariable Long id) {
		WorkerDTO worker = workerService.findById(id);
		return ResponseEntity.ok().body(worker);
	}
	
	@PostMapping
	public ResponseEntity<WorkerDTO> insert(@Valid @RequestBody WorkerDTO workerDTO) {
		workerDTO = workerService.insert(workerDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(workerDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(workerDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<WorkerDTO> update(@PathVariable Long id, @RequestBody WorkerDTO workerDTO) {
		workerDTO = workerService.update(id, workerDTO);
		return ResponseEntity.ok().body(workerDTO);
	}
	
	@DeleteMapping(value = "/{workerId}")
	public ResponseEntity<Void> delete(@PathVariable Long workerId, @RequestParam(defaultValue = "true") boolean deleteFromAllDepartments) {
		workerService.delete(workerId, deleteFromAllDepartments);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{workerId}/departments/{departmentId}")
	public ResponseEntity<Void> deleteFromDepartment(@PathVariable Long workerId, @PathVariable Long departmentId) {
		workerService.deleteFromDepartment(workerId, departmentId);
		return ResponseEntity.noContent().build();
	}

}
