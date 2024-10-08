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

import com.semulea.agrisemu.dto.UserAdminDTO;
import com.semulea.agrisemu.services.UserAdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/useradmins")
public class UserAdminResource {
	
	@Autowired
	private UserAdminService adminService;
	
	@GetMapping
	public ResponseEntity<List<UserAdminDTO>> findAll() {
		List<UserAdminDTO> list = adminService.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserAdminDTO> findById(@PathVariable Long id) {
		UserAdminDTO obj = adminService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<UserAdminDTO> insert(@Valid @RequestBody UserAdminDTO obj) {
		obj = adminService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		adminService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserAdminDTO> update(@PathVariable Long id, @RequestBody UserAdminDTO obj) {
		obj = adminService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	

}
