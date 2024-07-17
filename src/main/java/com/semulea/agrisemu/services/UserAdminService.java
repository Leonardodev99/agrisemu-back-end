package com.semulea.agrisemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.dto.UserAdminDTO;
import com.semulea.agrisemu.entties.UserAdmin;
import com.semulea.agrisemu.repositories.UserAdminRepository;
import com.semulea.agrisemu.resources.exceptions.EmailAlreadyExistsException;
import com.semulea.agrisemu.resources.exceptions.PhoneAlreadyExistsException;
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
		
		if(adminRepository.findByEmail(userAdminDTO.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		if(adminRepository.findByPhone(userAdminDTO.getPhone()).isPresent()) {
			throw new PhoneAlreadyExistsException("Phone already exists");
		}
		UserAdmin obj = new UserAdmin(userAdminDTO);
		UserAdmin savedUser = adminRepository.save(obj);
		return new UserAdminDTO(savedUser);
	}
	
	public void delete(Long id) {
		
		if(!adminRepository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		adminRepository.deleteById(id);
	}
	
	public UserAdminDTO update(Long id, UserAdminDTO userAdminDTO) {
		UserAdmin existingUser = adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		if (userAdminDTO.getName() != null) {
            existingUser.setName(userAdminDTO.getName());;
        }
        if (userAdminDTO.getEmail() != null) {
            existingUser.setEmail(userAdminDTO.getEmail());
        }
        if (userAdminDTO.getPhone() != null) {
            existingUser.setPhone(userAdminDTO.getPhone());;
        }
        if(userAdminDTO.getPassword() != null) {
        	existingUser.setPassword(userAdminDTO.getPassword());
        }
        
        if(adminRepository.findByEmail(userAdminDTO.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
        if(adminRepository.findByPhone(userAdminDTO.getPhone()).isPresent()) {
			throw new PhoneAlreadyExistsException("Phone already exists");
		}
		
		UserAdmin updatedUser = adminRepository.save(existingUser);
		return new UserAdminDTO(updatedUser);
	}
}
