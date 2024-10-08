package com.semulea.agrisemu.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semulea.agrisemu.entties.UserAdmin;

@Repository
public interface UserAdminRepository extends JpaRepository<UserAdmin, Long> {
	
	Optional<UserAdmin> findByEmail(String email);
	Optional<UserAdmin> findByPhone(String phone);
	boolean existsByPhone(String phone);

}
