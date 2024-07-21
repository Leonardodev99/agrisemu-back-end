package com.semulea.agrisemu.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semulea.agrisemu.entties.employers.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
	
	Optional<Employer> findByEmail(String email);
	Optional<Employer> findByPhone(String phone);
	Optional<Employer> findByNif(String nif);
	Optional<Employer> findByName(String name);
	boolean existsByPhone(String phone);

}
