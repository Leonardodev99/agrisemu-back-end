package com.semulea.agrisemu.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.employer.dto.EmployerDTO;
import com.semulea.agrisemu.entties.employers.CompanySector;
import com.semulea.agrisemu.entties.employers.Employer;
import com.semulea.agrisemu.repositories.CompanySectorRepository;
import com.semulea.agrisemu.repositories.EmployerRepository;
import com.semulea.agrisemu.resources.exceptions.EmailAlreadyExistsException;
import com.semulea.agrisemu.resources.exceptions.PhoneAlreadyExistsException;
import com.semulea.agrisemu.services.exceptions.NameEmployerAlreadyExistsException;
import com.semulea.agrisemu.services.exceptions.NifAlreadyExistsException;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

@Service
public class EmployerService {
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private CompanySectorRepository companySectorRepository;
	
	public List<EmployerDTO> findAll() {
		List<Employer> result = employerRepository.findAll();
		
		return result.stream().map(EmployerDTO::new).toList();
	}
	
	public EmployerDTO findById(Long id) {
		Employer employer = employerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		return new EmployerDTO(employer);
	}
	
	public EmployerDTO insert(EmployerDTO employer) {
		
		if(employerRepository.findByName(employer.getName()).isPresent()) {
			throw new NameEmployerAlreadyExistsException("Name already exists");
		}
		
		if(employerRepository.findByEmail(employer.getEmail()).isPresent()) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		if(employerRepository.findByPhone(employer.getPhone()).isPresent()) {
			throw new PhoneAlreadyExistsException("Phone already exists");
		}
		if(employerRepository.findByNif(employer.getNif()).isPresent()) {
			throw new NifAlreadyExistsException("NIF already exists");
		}
		Instant registrationDate = Instant.now();
		Employer obj = new Employer(employer);
		obj.setRegistrationDate(registrationDate);
		
		CompanySector companySector = companySectorRepository.findById(employer.getCompanySectorId())
				.orElseThrow(() -> new ResourceNotFoundException("Company sector not found"));
		
		obj.getCompanySectors().add(companySector);
		companySector.getEmployers().add(obj);
		
		Employer savedEmployer = employerRepository.save(obj);
		companySectorRepository.save(companySector);
		return new EmployerDTO(savedEmployer);
	}
	
	public EmployerDTO update(Long id, EmployerDTO employer) {
		Employer existsEmployer = employerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		if(employer.getName() != null && !employer.getName().isEmpty()) {
			if(employerRepository.findByName(employer.getName()).isPresent()) {
				throw new NameEmployerAlreadyExistsException("Name already exists");
			}
			existsEmployer.setName(employer.getName());
		}
		if(employer.getEmail() != null && !employer.getEmail().isEmpty()) {
			if(employerRepository.findByEmail(employer.getEmail()).isPresent()) {
				throw new EmailAlreadyExistsException("Email already exists");
			}
			existsEmployer.setEmail(employer.getEmail());
		}
		if(employer.getPhone() != null && !employer.getPhone().isEmpty()) {
			if(employerRepository.findByPhone(employer.getPhone()).isPresent()) {
				throw new PhoneAlreadyExistsException("Phone already exists");
			}
			existsEmployer.setPhone(employer.getPhone());
		}
		if(employer.getNif() != null && !employer.getNif().isEmpty()) {
			if(employerRepository.findByNif(employer.getNif()).isPresent()) {
				throw new NifAlreadyExistsException("NIF already exists");
			}
			existsEmployer.setNif(employer.getNif());
		} 
		if(employer.getAddress() != null && !employer.getAddress().isEmpty()) {
			existsEmployer.setAddress(employer.getAddress());
		}
		if(employer.getNumberDepartment() != null) {
			existsEmployer.setNumberDepartment(employer.getNumberDepartment());
		}
		
		Instant registrationDate = Instant.now();
		
		if(employer.getRegistrationDate() != null) {
			existsEmployer.setRegistrationDate(registrationDate);
		}
		Employer updatedEmployer = employerRepository.save(existsEmployer);
		
		return new EmployerDTO(updatedEmployer);
	}
	
	public void delete(Long id) {
		if(!employerRepository.existsById(id)) {
			throw new ResourceNotFoundException(id);
		}
		employerRepository.deleteById(id);
	}

}
