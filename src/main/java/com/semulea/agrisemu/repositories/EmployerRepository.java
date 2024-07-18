package com.semulea.agrisemu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semulea.agrisemu.entties.employers.Employer;

public interface EmployerRepository extends JpaRepository<Employer, Long> {

}
