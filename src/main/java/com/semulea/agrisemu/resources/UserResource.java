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

import com.semulea.agrisemu.dto.UserDTO;
import com.semulea.agrisemu.dto.UserMinDTO;
import com.semulea.agrisemu.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserMinDTO>> findAll(){
		List<UserMinDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
		UserDTO obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	@PostMapping
	private ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
			service.delete(id);
			return ResponseEntity.noContent().build();

	}
	
	@PutMapping(value = "/{id}")
	 public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
