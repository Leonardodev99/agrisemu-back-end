package com.semulea.agrisemu.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.employer.dto.WorkerDTO;
import com.semulea.agrisemu.entties.employers.Department;
import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.departments.Presence;
import com.semulea.agrisemu.entties.employers.enums.StatusCivic;
import com.semulea.agrisemu.entties.employers.enums.TypeContract;
import com.semulea.agrisemu.entties.employers.enums.WorkerLevel;
import com.semulea.agrisemu.repositories.DepartmentRepository;
import com.semulea.agrisemu.repositories.WorkerRepository;
import com.semulea.agrisemu.resources.exceptions.EmailAlreadyExistsException;
import com.semulea.agrisemu.resources.exceptions.PhoneAlreadyExistsException;
import com.semulea.agrisemu.services.exceptions.BiAlreadyExistsException;
import com.semulea.agrisemu.services.exceptions.InvalidDateOfBirthException;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;
import com.semulea.agrisemu.services.exceptions.WorkerAlreadyInDepartmentException;

@Service
public class WorkerService {

	@Autowired
	private WorkerRepository workerRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	public List<WorkerDTO> findAll() {
		List<Worker> result = workerRepository.findAll();
		return result.stream().map(x -> new WorkerDTO(x)).toList();
	}

	public WorkerDTO findById(Long id) {
		Worker worker = workerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new WorkerDTO(worker);
	}

	public WorkerDTO insert(WorkerDTO workerDTO) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(workerDTO.getDateOfBirth(), formatter);
		Instant dateOfBirth = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
		
		LocalDate today = LocalDate.now();
		Period period = Period.between(localDate, today);
		if(period.getYears() < 18) {
			throw new InvalidDateOfBirthException("Worker must be 18 years or older!");
		}

		Worker worker = workerRepository.findByBi(workerDTO.getBi())
	            .or(() -> workerRepository.findByPhone(workerDTO.getPhone()))
	            .or(() -> workerRepository.findByEmail(workerDTO.getEmail()))
	            .orElse(null);
		
		if (worker == null) {
	        
	        worker = new Worker(workerDTO);
	        worker.setDateOfBirth(dateOfBirth);
	    } else {
	        
	        if (worker.getDepartments().stream().anyMatch(d -> d.getId().equals(workerDTO.getDepartmentId()))) {
	            throw new WorkerAlreadyInDepartmentException("Worker is already in this department!");
	        }
	    }
		
		 if(workerDTO.getDepartmentId() != null) {
		        Department department = departmentRepository.findById(workerDTO.getDepartmentId())
		                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
		        
		        worker.getDepartments().add(department);
		        department.incrementNumberWorkers();
		        departmentRepository.save(department);
		    }
		
		
		Worker saveWorker = workerRepository.save(worker);
		return new WorkerDTO(saveWorker);
	}

	public WorkerDTO update(Long id, WorkerDTO workerDTO) {
		Worker existingWorker = workerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

		if (workerDTO.getBi() != null && !workerDTO.getBi().isEmpty()) {

			if (workerRepository.findByBi(workerDTO.getBi()).isPresent()) {
				throw new BiAlreadyExistsException("BI already exists!");
			}
			existingWorker.setBi(workerDTO.getBi());
		}
		if (workerDTO.getPhone() != null && !workerDTO.getPhone().isEmpty()) {

			if (workerRepository.findByPhone(workerDTO.getPhone()).isPresent()) {
				throw new PhoneAlreadyExistsException("Phone already exists!");
			}
			existingWorker.setPhone(workerDTO.getPhone());
		}
		if (workerDTO.getEmail() != null && !workerDTO.getEmail().isEmpty()) {

			if (workerRepository.findByEmail(workerDTO.getEmail()).isPresent()) {
				throw new EmailAlreadyExistsException("Email already exists!");
			}
			existingWorker.setEmail(workerDTO.getEmail());
		}
		if (workerDTO.getAddress() != null && !workerDTO.getAddress().isEmpty()) {
			existingWorker.setAddress(workerDTO.getAddress());
		}
		
		if (workerDTO.getNationality() != null && !workerDTO.getNationality().isEmpty()) {
			existingWorker.setNationality(workerDTO.getNationality());
		}
		if (workerDTO.getStatusCivic() != null) {
            existingWorker.setStatusCivic(StatusCivic.valueOf(workerDTO.getStatusCivic()));
        }
		if (workerDTO.getEducation() != null && !workerDTO.getEducation().isEmpty()) {
			existingWorker.setEducation(workerDTO.getEducation());
		}
		 if (workerDTO.getLevel() != null) {
	            existingWorker.setWorkerLevel(WorkerLevel.valueOf(workerDTO.getLevel()));
	        }
		 
		 if (workerDTO.getTypeContract() != null) {
	            existingWorker.setTypeContract(TypeContract.valueOf(workerDTO.getTypeContract()));
	        }
		if (workerDTO.getBasySalary() != null) {
			existingWorker.setBasySalary(workerDTO.getBasySalary());
		}
		if (workerDTO.getIrt() != null) {
			existingWorker.setIrt(workerDTO.getIrt());
		}
		if (workerDTO.getGrossSalary() != null) {
			existingWorker.setGrossSalary(workerDTO.getGrossSalary());
		}
		
		Worker updatedWorker = workerRepository.save(existingWorker);
		return new WorkerDTO(updatedWorker);
	}
	
	public void delete(Long id) {
		Worker existingWorker = workerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		for(Department department : existingWorker.getDepartments()) {
			department.decrementNumberWorkers();
			departmentRepository.save(department);
		}
		
		workerRepository.deleteById(id);
	}
	
	
	public WorkerDTO updatePresences(Long workerId, List<Presence> presences) {
	    Worker worker = workerRepository.findById(workerId).orElseThrow(() -> new ResourceNotFoundException(workerId));

	    
	    worker.getPresences().clear();
	    worker.getPresences().addAll(presences);
	    workerRepository.save(worker);

	    return new WorkerDTO(worker);
	}

}
