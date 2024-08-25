package com.semulea.agrisemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.entties.employers.clients.ClientPersonal;
import com.semulea.agrisemu.entties.employers.clients.dto.ClientPersonalDTO;
import com.semulea.agrisemu.repositories.ClientPersonalRepository;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

@Service
public class ClientPersonalService {
	
	
	@Autowired
	ClientPersonalRepository clientPersonalRepository;
	
	public List<ClientPersonalDTO> findAll() {
		List<ClientPersonal> result = clientPersonalRepository.findAll();
		return result.stream().map(x -> new ClientPersonalDTO(x)).toList();
	}
	
	public ClientPersonalDTO findById(Long id) {
		ClientPersonal result = clientPersonalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new ClientPersonalDTO(result);
	}
	
	public ClientPersonalDTO insert(ClientPersonalDTO clientPersonalDTO) {
		ClientPersonal clientPersonal = new ClientPersonal(clientPersonalDTO);
		ClientPersonal savedClientPersonal = clientPersonalRepository.save(clientPersonal);
		return new  ClientPersonalDTO(savedClientPersonal);
	}
	
	public ClientPersonalDTO update(Long id, ClientPersonalDTO clientPersonalDTO) {
		ClientPersonal existsClientPersonal = clientPersonalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		if(clientPersonalDTO.getEmail() != null && !clientPersonalDTO.getEmail().isEmpty()) {
			
			existsClientPersonal.setEmail(clientPersonalDTO.getEmail());
		}
		
		if(clientPersonalDTO.getPhone() != null && !clientPersonalDTO.getPhone().isEmpty()) {
			

			existsClientPersonal.setPhone(clientPersonalDTO.getPhone());
		}
		
		if(clientPersonalDTO.getName() !=null && !clientPersonalDTO.getName().isEmpty()) {
			existsClientPersonal.setName(clientPersonalDTO.getName());
		}
		
		ClientPersonal updatedClientPersonal = clientPersonalRepository.save(existsClientPersonal);
		return new ClientPersonalDTO(updatedClientPersonal);
	}
	
	public void delete(Long id) {
		if(!clientPersonalRepository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		clientPersonalRepository.deleteById(id);
	}
	

}
