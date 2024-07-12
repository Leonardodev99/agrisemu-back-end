package com.semulea.agrisemu.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.dto.UserDTO;
import com.semulea.agrisemu.dto.UserMinDTO;
import com.semulea.agrisemu.entties.User;
import com.semulea.agrisemu.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<UserMinDTO> findAll() {
		List<User> result = repository.findAll();
		return result.stream().map(x -> new UserMinDTO(x)).toList();
		
	}
	
	public UserMinDTO findById(Long id) {
		 User result = repository.findById(id).get();
		 return new UserMinDTO(result);
		
	}
	
	public UserDTO insert(UserDTO userDTO) {
		User user = new User(userDTO);
		User savedUser = repository.save(user);
		 return  new UserDTO(savedUser);
	}

}
