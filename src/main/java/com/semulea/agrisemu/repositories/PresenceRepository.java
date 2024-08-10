package com.semulea.agrisemu.repositories;
import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.departments.Presence;

public interface PresenceRepository extends JpaRepository<Presence, Long> {
    List<Presence> findAllByWorker(Worker worker);
    
    @Query("SELECT p FROM Presence p WHERE p.worker = :worker AND p.businessDay BETWEEN :start AND :end")
    List<Presence> findPresencesByWorkerAndPeriod(@Param("worker") Worker worker, @Param("start") Instant start, @Param("end") Instant end);
    
    @Query("SELECT p FROM Presence p WHERE p.businessDay BETWEEN :start AND :end")
    List<Presence> findPresencesByPeriod(@Param("start") Instant start, @Param("end") Instant end);
}

