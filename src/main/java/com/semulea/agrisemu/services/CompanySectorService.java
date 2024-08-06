package com.semulea.agrisemu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.employer.dto.CompanySectorDTO;
import com.semulea.agrisemu.entties.employers.CompanySector;
import com.semulea.agrisemu.repositories.CompanySectorRepository;

@Service
public class CompanySectorService {
	
	
	@Autowired
	private CompanySectorRepository companySectorRepository;
	
	public List<CompanySectorDTO> findAll() {
		List<CompanySector> list = companySectorRepository.findAll();
		return list.stream().map(x -> new CompanySectorDTO(x) ).toList();
	}
	
	public CompanySectorDTO findById( Long id) {
		CompanySector obj = companySectorRepository.findById(id).get();
		return new CompanySectorDTO(obj);
	}
	
	public CompanySectorDTO insert(CompanySectorDTO obj) {
		CompanySector companySector = new CompanySector(obj);
		CompanySector savedCompanySector = companySectorRepository.save(companySector);
		return new CompanySectorDTO(savedCompanySector);
	}
	
	public CompanySectorDTO update(Long id, CompanySectorDTO obj) {
		CompanySector existObj = companySectorRepository.findById(id).get();
		CompanySector updatedCompanySector = companySectorRepository.save(existObj);
		return new CompanySectorDTO(updatedCompanySector);
	}
	
	public void deleteById(Long id) {
		CompanySector existObj = companySectorRepository.findById(id).get();
		companySectorRepository.delete(existObj);
	}

}
