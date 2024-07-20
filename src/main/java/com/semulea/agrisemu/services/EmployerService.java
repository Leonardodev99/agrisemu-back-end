package com.semulea.agrisemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.employer.dto.EmployerDTO;
import com.semulea.agrisemu.entties.employers.Employer;
import com.semulea.agrisemu.repositories.EmployerRepository;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

@Service
public class EmployerService {
	
	@Autowired
	private EmployerRepository employerRepository;
	
	public List<EmployerDTO> findAll() {
		List<Employer> result = employerRepository.findAll();
		
		return result.stream().map(EmployerDTO::new).toList();
	}
	
	public EmployerDTO findById(Long id) {
		Employer employer = employerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		return new EmployerDTO(employer);
	}
	
	public EmployerDTO insert(EmployerDTO employer) {
		Employer obj = new Employer(employer);
		Employer savedEmployer = employerRepository.save(obj);
		return new EmployerDTO(savedEmployer);
	}
	
	public EmployerDTO update(Long id, EmployerDTO employer) {
		Employer existsEmployer = employerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		Employer updatedEmployer = employerRepository.save(existsEmployer);
		
		return new EmployerDTO(updatedEmployer);
	}
	
	public void delete(Long id) {
		employerRepository.deleteById(id);
	}

}
