package com.semulea.agrisemu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semulea.agrisemu.entties.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
