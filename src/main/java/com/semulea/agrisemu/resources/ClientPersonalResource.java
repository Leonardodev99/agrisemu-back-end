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

import com.semulea.agrisemu.entties.employers.clients.dto.ClientPersonalDTO;
import com.semulea.agrisemu.services.ClientPersonalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value ="/clientpersonal")
public class ClientPersonalResource {
	
	@Autowired
	private ClientPersonalService clientPersonalService;
	
	@GetMapping
	public ResponseEntity<List<ClientPersonalDTO>> findAll() {
		List<ClientPersonalDTO> list = clientPersonalService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientPersonalDTO> findById(@PathVariable Long id) {
		ClientPersonalDTO client = clientPersonalService.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@PostMapping
	public ResponseEntity<ClientPersonalDTO> insert(@Valid @RequestBody ClientPersonalDTO clientPersonal) {
		clientPersonal = clientPersonalService.insert(clientPersonal);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(clientPersonal.getId()).toUri();
		return ResponseEntity.created(uri).body(clientPersonal);
		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientPersonalDTO> update(@PathVariable Long id, @RequestBody ClientPersonalDTO clientPersonal) {
		clientPersonal = clientPersonalService.update(id, clientPersonal);
		return ResponseEntity.ok().body(clientPersonal);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		clientPersonalService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
