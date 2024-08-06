package com.semulea.agrisemu.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semulea.agrisemu.employer.dto.CompanySectorDTO;
import com.semulea.agrisemu.entties.employers.CompanySector;
import com.semulea.agrisemu.repositories.CompanySectorRepository;
import com.semulea.agrisemu.services.exceptions.CompanySectorAlreadyExistsException;
import com.semulea.agrisemu.services.exceptions.ResourceNotFoundException;

@Service
public class CompanySectorService {
	
	
	@Autowired
	private CompanySectorRepository companySectorRepository;
	
	public List<CompanySectorDTO> findAll() {
		List<CompanySector> list = companySectorRepository.findAll();
		return list.stream().map(x -> new CompanySectorDTO(x) ).toList();
	}
	
	public CompanySectorDTO findById( Long id) {
		CompanySector obj = companySectorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		return new CompanySectorDTO(obj);
	}
	
	public CompanySectorDTO insert(CompanySectorDTO obj) {
		Optional<CompanySector> existingSector = companySectorRepository.findAll().stream()
				.filter(sector -> sector.getEconomicActivity().equals(obj.getEconomicActivity()) &&
						sector.getCompanyType().equals(obj.getCompanyType()) &&
						sector.getMarketSector().equals(obj.getMarketSector()))
				.findFirst();
		if(existingSector.isPresent()) {
			throw new CompanySectorAlreadyExistsException("The company sector already exists!");
		}
		
		CompanySector companySector = new CompanySector(obj);
		CompanySector savedCompanySector = companySectorRepository.save(companySector);
		return new CompanySectorDTO(savedCompanySector);
	}
	
	public CompanySectorDTO update(Long id, CompanySectorDTO obj) {
		CompanySector existObj = companySectorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
		
		if(obj.getCompanyType() != null) {
			existObj.setCompanyType(obj.getCompanyType());
		}
		if(obj.getEconomicActivity() != null) {
			existObj.setEconomicActivity(obj.getEconomicActivity());
		}
		if(obj.getMarketSector() !=null) {
			existObj.setMarketSector(obj.getMarketSector());
		}
		CompanySector updatedCompanySector = companySectorRepository.save(existObj);
		return new CompanySectorDTO(updatedCompanySector);
	}
	
	public void deleteById(Long id) {
		CompanySector existObj = companySectorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));;
		companySectorRepository.delete(existObj);
	}

}
