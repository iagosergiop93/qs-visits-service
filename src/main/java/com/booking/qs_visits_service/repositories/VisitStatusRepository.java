package com.booking.qs_visits_service.repositories;

import com.booking.qs_visits_service.domain.VisitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisitStatusRepository extends JpaRepository<VisitStatus, String> {

    Optional<VisitStatus> findByVisitId(String visitId);
}
