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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.semulea.agrisemu.employer.dto.ContractDTO;
import com.semulea.agrisemu.services.ContractService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/contracts")
public class ContractResource {
	
	@Autowired
	private ContractService contractService;
	
	@GetMapping
	public ResponseEntity<List<ContractDTO>> findAll() {
		List<ContractDTO> list = contractService.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContractDTO> findById(@PathVariable Long id) {
		ContractDTO obj = contractService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@PostMapping
	public ResponseEntity<ContractDTO> insert(@Valid @RequestBody ContractDTO contractDTO) {
		contractDTO = contractService.insert(contractDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(contractDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(contractDTO);
	}
	@PutMapping(value ="/{id}")
	public ResponseEntity<ContractDTO> update(@PathVariable Long id, @RequestBody ContractDTO obj) {
		obj = contractService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		contractService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
