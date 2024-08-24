package com.semulea.agrisemu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semulea.agrisemu.entties.employers.clients.ClientPersonal;

public interface ClientPersonalRepository extends JpaRepository<ClientPersonal, Long> {

}
