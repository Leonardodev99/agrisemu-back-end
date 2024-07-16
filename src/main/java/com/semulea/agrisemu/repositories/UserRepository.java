package com.semulea.agrisemu.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semulea.agrisemu.entties.UserWorker;

@Repository
public interface UserRepository extends JpaRepository<UserWorker, Long>{
	
	Optional<UserWorker> findByEmail(String email);
	Optional<UserWorker> findByUsername(String username);

}
