package com.semulea.agrisemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.dto.UserAdminDTO;
import com.semulea.agrisemu.entties.UserAdmin;
import com.semulea.agrisemu.repositories.UserAdminRepository;

@Service
public class UserAdminService {
	
	@Autowired
	private UserAdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserAdmin saveAdmin (UserAdmin admin) {
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		return adminRepository.save(admin);
	}
	
	public List<UserAdminDTO> findAll () {
		List<UserAdmin> result = adminRepository.findAll();
		return result.stream().map(x -> new UserAdminDTO(x)).toList();
	}

}
