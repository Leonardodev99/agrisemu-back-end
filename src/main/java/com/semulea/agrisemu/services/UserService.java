package com.semulea.agrisemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.dto.UserDTO;
import com.semulea.agrisemu.entties.User;
import com.semulea.agrisemu.repositories.UserRepository;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> findAll() {
		List<User> result = userRepository.findAll();
		return result.stream().map(x -> new UserDTO(x)).toList();
	}
	
	public UserDTO findById(Long id) {
		User result = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserDTO(result);
	}
	
	public UserDTO insert(UserDTO userDTO) {
		User user = new User(userDTO);
		User savedUser = userRepository.save(user);
		return new UserDTO(savedUser);
	}
	
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	public UserDTO update(Long id, UserDTO userDTO) {
		User existsUser = userRepository.findById(id).get();
		User updatedUser = userRepository.save(existsUser);
		
		return new UserDTO(updatedUser);
	}
	

}
