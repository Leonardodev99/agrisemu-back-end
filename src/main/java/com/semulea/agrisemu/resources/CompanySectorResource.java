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

import com.semulea.agrisemu.employer.dto.CompanySectorDTO;
import com.semulea.agrisemu.services.CompanySectorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/companysectors")
public class CompanySectorResource {
	
	@Autowired
	private CompanySectorService companySectorService;
	
	@GetMapping
	public ResponseEntity<List<CompanySectorDTO>> findAll() {
		List<CompanySectorDTO> list = companySectorService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<CompanySectorDTO> findById(@PathVariable Long id) {
		CompanySectorDTO obj = companySectorService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<CompanySectorDTO> insert(@Valid @RequestBody CompanySectorDTO obj) {
		obj = companySectorService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CompanySectorDTO> update(@PathVariable Long id, @RequestBody CompanySectorDTO obj) {
		obj = companySectorService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}") 
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		companySectorService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
