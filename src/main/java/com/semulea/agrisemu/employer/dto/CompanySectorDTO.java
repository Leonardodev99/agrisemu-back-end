package com.semulea.agrisemu.employer.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.CompanySector;
import com.semulea.agrisemu.entties.employers.enums.CompanyType;
import com.semulea.agrisemu.entties.employers.enums.EconomicActivity;
import com.semulea.agrisemu.entties.employers.enums.MarketSector;
import com.semulea.agrisemu.validation.EnumValidation;

public class CompanySectorDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@EnumValidation(enumClass = EconomicActivity.class, message = "Invalid Economic activity value")
	private String economicActivity;
	
	@EnumValidation(enumClass = CompanyType.class, message = "Invalid company type value")
	private String companyType;
	
	@EnumValidation(enumClass = MarketSector.class, message = "Invalid market sector value")
	private String marketSector;
	
	
	private Set<EmployerDTO> employers = new HashSet<>();
	
	public CompanySectorDTO() {
		
	}

	public CompanySectorDTO(CompanySector entity) {
		BeanUtils.copyProperties(entity, this);
		this.companyType = entity.getCompanyType().toString();
		this.economicActivity = entity.getEconomicActivity().toString();
		this.marketSector = entity.getMarketSector().toString();
		this.employers = entity.getEmployers().stream().map(EmployerDTO::new).collect((Collectors.toSet()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEconomicActivity() {
		return economicActivity;
	}

	public void setEconomicActivity(String economicActivity) {
		
			this.economicActivity = economicActivity;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
			this.companyType = companyType;
	}

	public String getMarketSector() {
		return marketSector;
	}

	public void setMarketSector(String marketSector) {
	
			this.marketSector = marketSector;
	}
	
	public Set<EmployerDTO> getEmployers() {
		return employers;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanySectorDTO other = (CompanySectorDTO) obj;
		return Objects.equals(id, other.id);
	}
	
}
