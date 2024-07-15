package com.semulea.agrisemu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semulea.agrisemu.entties.UserAdmin;

@Repository
public interface UserAdminRepository extends JpaRepository<UserAdmin, Long> {

}
