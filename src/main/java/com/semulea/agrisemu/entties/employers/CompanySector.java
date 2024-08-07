package com.semulea.agrisemu.entties.employers;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.semulea.agrisemu.employer.dto.CompanySectorDTO;
import com.semulea.agrisemu.entties.employers.enums.CompanyType;
import com.semulea.agrisemu.entties.employers.enums.EconomicActivity;
import com.semulea.agrisemu.entties.employers.enums.MarketSector;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "business_sectors")
public class CompanySector implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private Integer economicActivity;
	
	@NotNull
	private Integer companyType;
	
	@NotNull
	private Integer marketSector;
	
	@Transient
	private Set<Employer> employers = new HashSet<>();
	
	public CompanySector() {
		
	}

	public CompanySector(Long id, EconomicActivity economicActivity, CompanyType companyType,
			MarketSector marketSector) {
	
		this.id = id;
		setEconomicActivity(economicActivity);
		setCompanyType(companyType);
		setMarketSector(marketSector);
	}
	
	public CompanySector(CompanySectorDTO entity) {
		BeanUtils.copyProperties(entity, this);
		setCompanyType(CompanyType.valueOf(entity.getCompanyType()));
		setEconomicActivity(EconomicActivity.valueOf(entity.getEconomicActivity()));
		setMarketSector(MarketSector.valueOf(entity.getMarketSector()));
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
	
	public Set<Employer> getEmployers() {
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
		CompanySector other = (CompanySector) obj;
		return Objects.equals(id, other.id);
	}
	
}
