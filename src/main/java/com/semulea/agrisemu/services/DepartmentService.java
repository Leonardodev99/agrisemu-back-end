package com.semulea.agrisemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.employer.dto.DepartmentDTO;
import com.semulea.agrisemu.entties.employers.Department;
import com.semulea.agrisemu.entties.employers.Employer;
import com.semulea.agrisemu.repositories.DepartmentRepository;
import com.semulea.agrisemu.repositories.EmployerRepository;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	public List<DepartmentDTO> findAll() {
		List<Department> result = departmentRepository.findAll();
		return result.stream().map(x -> new DepartmentDTO(x)).toList();
	}
	
	public DepartmentDTO findById(Long id) {
		Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new DepartmentDTO(department);
	}
	
	public DepartmentDTO insert(DepartmentDTO departmentDTO) {
		
		Employer employer = employerRepository.findById(departmentDTO.getEmployerId())
				.orElseThrow(() -> new ResourceNotFoundException(" Employer not found"));

		Department obj = new Department();
		obj.setName(departmentDTO.getName());
		obj.setNumberWorkers(departmentDTO.getNumberWorkers());
		obj.setEmployer(employer);
		
		employer.incrementNumberDepartment();
		employerRepository.save(employer);
		
		Department savedDepartment = departmentRepository.save(obj);
		departmentDTO.setId(savedDepartment.getId());
		return departmentDTO;
	}
	
	public DepartmentDTO update(Long id, DepartmentDTO depart) {
		Department existsDepartment = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		if(existsDepartment.getName() != null) {
			existsDepartment.setName(depart.getName());
		}
		if(existsDepartment.getNumberWorkers() == 0) {
			existsDepartment.setNumberWorkers(depart.getNumberWorkers());
		}
		
		Department updateddDepartment = departmentRepository.save(existsDepartment);
		return new DepartmentDTO(updateddDepartment);	
	}
	
	public void delete(Long id) {
		
		Department existingDepartment = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		Employer employer = existingDepartment.getEmployer();
		
		employer.decrementNumberDepartment();
		employerRepository.save(employer);
		departmentRepository.deleteById(id);
		
	}

}
