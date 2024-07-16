package com.semulea.agrisemu.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.semulea.agrisemu.dto.UserAdminDTO;
import com.semulea.agrisemu.services.UserAdminService;

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
	
	

}
