package com.semulea.agrisemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.dto.UserAdminDTO;
import com.semulea.agrisemu.entties.UserAdmin;
import com.semulea.agrisemu.repositories.UserAdminRepository;

@Service
public class UserAdminService {
	
	@Autowired
	private UserAdminRepository adminRepository;
	
	public List<UserAdminDTO> findAll () {
		List<UserAdmin> result = adminRepository.findAll();
		return result.stream().map(x -> new UserAdminDTO(x)).toList();
	}
}
