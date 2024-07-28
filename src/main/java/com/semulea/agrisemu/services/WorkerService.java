package com.semulea.agrisemu.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.employer.dto.WorkerDTO;
import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.repositories.WorkerRepository;
import com.semulea.agrisemu.resources.exceptions.EmailAlreadyExistsException;
import com.semulea.agrisemu.resources.exceptions.PhoneAlreadyExistsException;
import com.semulea.agrisemu.services.exceptions.BiAlreadyExistsException;

@Service
public class WorkerService {

	@Autowired
	private WorkerRepository workerRepository;

	public List<WorkerDTO> findAll() {
		List<Worker> result = workerRepository.findAll();
		return result.stream().map(x -> new WorkerDTO(x)).toList();
	}

	public WorkerDTO findById(Long id) {
		Worker worker = workerRepository.findById(id).get();
		return new WorkerDTO(worker);
	}

	public WorkerDTO insert(WorkerDTO workerDTO) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate localDate = LocalDate.parse(workerDTO.getDateOfBirth(), formatter);
		Instant dateOfBirth = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
		
		Worker worker = new Worker(workerDTO);
		worker.setDateOfBirth(dateOfBirth);
		worker.setSex(workerDTO.getSex());
		worker.setStatusCivic(workerDTO.getStatusCivic());
		worker.setWorkerLevel(workerDTO.getLevel());
		worker.setTypeContract(workerDTO.getTypeContract());

		Worker saveWorker = workerRepository.save(worker);
		return new WorkerDTO(saveWorker);
	}

	public WorkerDTO update(Long id, WorkerDTO workerDTO) {
		Worker existingWorker = workerRepository.findById(id).get();

		if (workerDTO.getBi() != null && !workerDTO.getBi().isEmpty()) {

			if (workerRepository.findByEmail(workerDTO.getEmail()).isPresent()) {
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
		/*if (workerDTO.getDateOfBirth() != null && !workerDTO.getDateOfBirth().isEmpty()) {

			if (workerRepository.findByDateOfBirth(workerDTO.getDateOfBirth()).isPresent()) {
				throw new DateOfBirthAlreadyExistsException("Date already exists!");
			}
			existingWorker.setEmail(workerDTO.getEmail());
		}*/
		
		if (workerDTO.getNationality() != null && !workerDTO.getNationality().isEmpty()) {
			existingWorker.setNationality(workerDTO.getNationality());
		}
		if (workerDTO.getStatusCivic() != null) {
			existingWorker.setStatusCivic(workerDTO.getStatusCivic());
		}
		if (workerDTO.getEducation() != null && !workerDTO.getEducation().isEmpty()) {
			existingWorker.setEducation(workerDTO.getEducation());
		}
		if (workerDTO.getLevel() != null) {
			existingWorker.setWorkerLevel(workerDTO.getLevel());
		}
		if (workerDTO.getTypeContract() != null) {
			existingWorker.setTypeContract(workerDTO.getTypeContract());
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
		workerRepository.deleteById(id);
	}

}
