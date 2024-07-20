package com.semulea.agrisemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.dto.UserDTO;
import com.semulea.agrisemu.entties.User;
import com.semulea.agrisemu.repositories.UserRepository;
import com.semulea.agrisemu.resources.exceptions.EmailAlreadyExistsException;
import com.semulea.agrisemu.resources.exceptions.PhoneAlreadyExistsException;
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
		
		if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		if(userRepository.findByPhone(userDTO.getPhone()).isPresent()) {
			throw new PhoneAlreadyExistsException("Phone already exists");
		}
		User user = new User(userDTO);
		User savedUser = userRepository.save(user);
		return new UserDTO(savedUser);
	}
	
	public void delete(Long id) {
		if(!userRepository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		userRepository.deleteById(id);
	}
	
	public UserDTO update(Long id, UserDTO userDTO) {
		User existsUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		if(userDTO.getEmail() != null) {
			existsUser.setEmail(userDTO.getEmail());
		}
		if(userDTO.getPassword() !=null) {
			existsUser.setPassword(userDTO.getPassword());
		}
		if(userDTO.getPhone() != null) {
			existsUser.setPhone(userDTO.getPhone());
		}
		User updatedUser = userRepository.save(existsUser);
		return new UserDTO(updatedUser);
	}
	

}
