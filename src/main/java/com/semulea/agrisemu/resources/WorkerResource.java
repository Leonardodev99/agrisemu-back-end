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

import com.semulea.agrisemu.employer.dto.WorkerDTO;
import com.semulea.agrisemu.services.WorkerService;

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
	public ResponseEntity<WorkerDTO> insert(@RequestBody WorkerDTO workerDTO) {
		workerDTO = workerService.insert(workerDTO);
		
		return ResponseEntity.ok().body(workerDTO);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<WorkerDTO> update(@PathVariable Long id, @RequestBody WorkerDTO workerDTO) {
		workerDTO = workerService.update(id, workerDTO);
		return ResponseEntity.ok().body(workerDTO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		workerService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
