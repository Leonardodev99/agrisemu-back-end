package com.semulea.agrisemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.employer.dto.DepartmentDTO;
import com.semulea.agrisemu.entties.employers.Department;
import com.semulea.agrisemu.entties.employers.Employer;
import com.semulea.agrisemu.repositories.DepartmentRepository;
import com.semulea.agrisemu.repositories.EmployerRepository;
import com.semulea.agrisemu.services.exceptions.DepartmentAlreadyExistsException;
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

		List<Department> existingDepartments = employer.getDepartments();
		for(Department department : existingDepartments) {
			if(department.getName().equalsIgnoreCase(departmentDTO.getName())) {
				throw new DepartmentAlreadyExistsException("Department "+ departmentDTO.getName() + " already exists for employer " +employer.getName());
			}
		}
		
		Department obj = new Department();
		obj.setName(departmentDTO.getName());
		obj.setNumberWorkers(departmentDTO.getNumberWorkers());
		obj.setEmail(departmentDTO.getEmail());
		obj.setLeader(departmentDTO.getLeader());
		obj.setPhone(departmentDTO.getPhone());
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
