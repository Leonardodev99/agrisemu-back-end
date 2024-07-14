package com.semulea.agrisemu.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.dto.UserDTO;
import com.semulea.agrisemu.dto.UserMinDTO;
import com.semulea.agrisemu.entties.User;
import com.semulea.agrisemu.repositories.UserRepository;
import com.semulea.agrisemu.resources.exceptions.EmailAlreadyExistsException;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<UserMinDTO> findAll() {
		List<User> result = repository.findAll();
		return result.stream().map(x -> new UserMinDTO(x)).toList();
		
	}
	
	public UserMinDTO findById(Long id) {
		
		User result = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		 return new UserMinDTO(result);
		
	}
	
	
	public UserDTO insert(UserDTO userDTO) {
		if(repository.findByEmail(userDTO.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		User user = new User(userDTO);
		User savedUser = repository.save(user);
		 return  new UserDTO(savedUser);
	}
	
	public void delete(Long id) {
		
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		
		repository.deleteById(id);
		 
	}
	
	public UserDTO update(Long id, UserDTO userDTO) {
		User existingUser = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		if (userDTO.getUsername() != null) {
            existingUser.setUsername(userDTO.getUsername());
        }
        if (userDTO.getEmail() != null) {
            existingUser.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null) {
            existingUser.setPassword(userDTO.getPassword());
        }
		
		User updatedUser = repository.save(existingUser);
		
		return new UserDTO(updatedUser);
	}

}
