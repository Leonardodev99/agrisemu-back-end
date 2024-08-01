package com.semulea.agrisemu.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.employer.dto.ContractDTO;
import com.semulea.agrisemu.entties.employers.Contract;
import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.repositories.ContractRepository;
import com.semulea.agrisemu.repositories.WorkerRepository;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

@Service
public class ContractService {
	
	@Autowired
	private ContractRepository contractRepository;
	
	@Autowired
	private WorkerRepository workerRepository;

	public List<ContractDTO> findAll() {
		List<Contract> list = contractRepository.findAll();
		return list.stream().map(x -> new ContractDTO(x)).toList();
	}
	public ContractDTO findById(Long id) {
		Contract contract = contractRepository.findById(id).get();
		return new ContractDTO(contract);
	}
	public ContractDTO insert(ContractDTO contractDTO) {
		
		Worker worker = workerRepository.findById(contractDTO.getWorkerId())
				.orElseThrow(() -> new ResourceNotFoundException(contractDTO.getWorkerId()));
		
		Instant initialDate = Instant.now();
		Instant finalDate = contractDTO.getFinalDateAsInstant();
		if(!finalDate.isAfter(initialDate)) {
			throw new ResourceNotFoundException("final date must be after the  Initail date");
		}
		
		Contract contract = new Contract(contractDTO);
		contract.setInitialDate(initialDate);
        contract.setFinalDate(finalDate);
        contract.setWorker(worker);
        contract.setValuePerHour(contractDTO.getValuePerHour());
        contract.setHoursPerDay(contractDTO.getHoursPerDay());
        contract.setExtraHoursValue(contractDTO.getExtraHoursValue());
        contract.setAdditionalValue(contractDTO.getAdditionalValue());
        contract.setTaxIrt(contractDTO.getTaxIrt());
       
        worker.incrementNumberContract();
        worker.getContracts().add(contract);
        worker.updateBasySalary();
        worker.updateGrossSalary();
        worker.updateIrt();
        worker.updateNetSalary();
        worker.updateTotalValueContract();
        workerRepository.save(worker);
		
		Contract savedContract = contractRepository.save(contract);
		return new ContractDTO(savedContract);
	}
	public ContractDTO update(Long id, ContractDTO obj) {
		Contract existsContract = contractRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		if(obj.getAdditionalValue() != null) {
			existsContract.setAdditionalValue(obj.getAdditionalValue());
		}
		if(obj.getTaxIrt() != null) {
			existsContract.setTaxIrt(obj.getTaxIrt());
		}
		if(obj.getExtraHoursValue() != null) {
			existsContract.setExtraHoursValue(obj.getExtraHoursValue());
		}
		Instant initialDate = Instant.now();
	        Instant finalDate = obj.getFinalDateAsInstant();

	        if (!finalDate.isAfter(initialDate)) {
	            throw new ResourceNotFoundException("Final date must be after the initial date.");
	        }
		
		if(obj.getHoursPerDay() != null) {
			existsContract.setHoursPerDay(obj.getHoursPerDay());
		}
		if(obj.getValuePerHour() != null) {
			existsContract.setValuePerHour(obj.getValuePerHour());
		}
		
		Contract updatedContract = contractRepository.save(existsContract);
		
		Worker worker = existsContract.getWorker();
		worker.updateBasySalary();
		worker.updateGrossSalary();
		worker.updateIrt();
		worker.updateNetSalary();
		worker.updateTotalValueContract();
		workerRepository.save(worker);
		return new ContractDTO(updatedContract);
	}
	public void delete(Long id) {
		Contract existsContract = contractRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		Worker worker = existsContract.getWorker();
			
		worker.decrementNumberContract();
		worker.getContracts().remove(existsContract);
		worker.updateBasySalary();
		worker.updateGrossSalary();
		worker.updateIrt();
		worker.updateNetSalary();
		worker.updateTotalValueContract();
		workerRepository.save(worker);
		
		contractRepository.deleteById(id);
	} 

}
