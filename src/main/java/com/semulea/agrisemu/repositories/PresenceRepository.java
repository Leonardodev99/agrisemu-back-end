package com.semulea.agrisemu.repositories;
import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.semulea.agrisemu.entties.employers.Worker;
import com.semulea.agrisemu.entties.employers.departments.Presence;

public interface PresenceRepository extends JpaRepository<Presence, Long> {
    List<Presence> findAllByWorker(Worker worker);
    int countAbsencesByWorkerAndBusinessDayBetween(Worker worker, Instant start, Instant end);
}

