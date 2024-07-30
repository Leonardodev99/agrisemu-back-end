package com.semulea.agrisemu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semulea.agrisemu.entties.employers.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long>{

}
