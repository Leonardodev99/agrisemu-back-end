package com.semulea.agrisemu.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.dto.UserWorkerDTO;
import com.semulea.agrisemu.dto.UserWorkerMinDTO;
import com.semulea.agrisemu.entties.UserWorker;
import com.semulea.agrisemu.repositories.UserWorkerRepository;
import com.semulea.agrisemu.resources.exceptions.EmailAlreadyExistsException;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

@Service
public class UserWorkerService {
	
	@Autowired
	private UserWorkerRepository repository;
	
	public List<UserWorkerMinDTO> findAll() {
		List<UserWorker> result = repository.findAll();
		return result.stream().map(x -> new UserWorkerMinDTO(x)).toList();
		
	}
	
	public UserWorkerDTO findById(Long id) {
		
		UserWorker result = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		 return new UserWorkerDTO(result);
		
	}
	
	
	public UserWorkerDTO insert(UserWorkerDTO userDTO) {
		if(repository.findByEmail(userDTO.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		UserWorker user = new UserWorker(userDTO);
		UserWorker savedUser = repository.save(user);
		 return  new UserWorkerDTO(savedUser);
	}
	
	public void delete(Long id) {
		
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		
		repository.deleteById(id);
		 
	}
	
	public UserWorkerDTO update(Long id, UserWorkerDTO userDTO) {
		UserWorker existingUser = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		if (userDTO.getUsername() != null && !userDTO.getUsername().isEmpty()) {
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
        	
        	if(repository.findByEmail(userDTO.getEmail()).isPresent()) {
				throw new EmailAlreadyExistsException("Email already exists!");
			}
        	existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(userDTO.getPassword());
        }
     
		UserWorker updatedUser = repository.save(existingUser);
		
		return new UserWorkerDTO(updatedUser);
	}

}
