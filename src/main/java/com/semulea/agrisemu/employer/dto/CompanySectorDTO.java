package com.semulea.agrisemu.employer.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.entties.employers.CompanySector;
import com.semulea.agrisemu.entties.employers.enums.CompanyType;
import com.semulea.agrisemu.entties.employers.enums.EconomicActivity;
import com.semulea.agrisemu.entties.employers.enums.MarketSector;

public class CompanySectorDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer economicActivity;
	private Integer companyType;
	private Integer marketSector;
	
	
	private Set<EmployerDTO> employers = new HashSet<>();
	
	public CompanySectorDTO() {
		
	}

	public CompanySectorDTO(CompanySector entity) {
		BeanUtils.copyProperties(entity, this);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EconomicActivity getEconomicActivity() {
		return EconomicActivity.valueOf(economicActivity);
	}

	public void setEconomicActivity(EconomicActivity economicActivity) {
		
		if(economicActivity != null) {
			this.economicActivity = economicActivity.getCode();
		}
	}

	public CompanyType getCompanyType() {
		return CompanyType.valueOf(companyType);
	}

	public void setCompanyType(CompanyType companyType) {
		if(companyType != null) {
			this.companyType = companyType.getCode();
		}
	}

	public MarketSector getMarketSector() {
		return MarketSector.valueOf(marketSector);
	}

	public void setMarketSector(MarketSector marketSector) {
		if(marketSector != null) {
			this.marketSector = marketSector.getCode();
		}
		
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
