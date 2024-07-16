package com.semulea.agrisemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.dto.UserAdminDTO;
import com.semulea.agrisemu.entties.UserAdmin;
import com.semulea.agrisemu.repositories.UserAdminRepository;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

@Service
public class UserAdminService {
	
	@Autowired
	private UserAdminRepository adminRepository;
	
	public List<UserAdminDTO> findAll () {
		List<UserAdmin> result = adminRepository.findAll();
		return result.stream().map(x -> new UserAdminDTO(x)).toList();
	}
	
	public UserAdminDTO findById(Long id) {
		UserAdmin result = adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserAdminDTO(result);
	}
	
	public UserAdminDTO insert(UserAdminDTO userAdminDTO) {
		UserAdmin obj = new UserAdmin(userAdminDTO);
		UserAdmin savedUser = adminRepository.save(obj);
		return new UserAdminDTO(savedUser);
	}
}
