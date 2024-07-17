package com.semulea.agrisemu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.semulea.agrisemu.entties.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
