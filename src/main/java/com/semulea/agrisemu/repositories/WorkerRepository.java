package com.semulea.agrisemu.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semulea.agrisemu.entties.employers.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long>{
	
	Optional<Worker> findByEmail(String email);
	Optional<Worker> findByPhone(String phone);
	Optional<Worker> findByBi(String bi);

}
