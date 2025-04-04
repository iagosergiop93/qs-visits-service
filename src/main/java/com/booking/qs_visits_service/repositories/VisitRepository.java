package com.booking.qs_visits_service.repositories;

import com.booking.qs_visits_service.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, String> {
}
