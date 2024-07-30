package com.semulea.agrisemu.services;

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
		
		Contract contract = new Contract(contractDTO);
		contract.setWorker(worker);
		Contract savedContract = contractRepository.save(contract);
		return new ContractDTO(savedContract);
	}
	public ContractDTO update(Long id, ContractDTO obj) {
		Contract existsContract = contractRepository.findById(id).get();
		
		Contract updatedContract = contractRepository.save(existsContract);
		return new ContractDTO(updatedContract);
	}
	public void delete(Long id) {
		contractRepository.deleteById(id);
	} 

}
