package com.semulea.agrisemu.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.semulea.agrisemu.entties.employers.CompanySector;

public interface CompanySectorRepository extends JpaRepository<CompanySector, Long>{
	@Query("SELECT cs FROM CompanySector cs WHERE cs.economicActivity = :economicActivity AND cs.companyType = :companyType AND cs.marketSector = :marketSector")
    Optional<CompanySector> findExistingSector(
        @Param("economicActivity") Integer economicActivity,
        @Param("companyType") Integer companyType,
        @Param("marketSector") Integer marketSector
    );
}
