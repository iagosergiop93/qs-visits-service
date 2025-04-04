package com.booking.qs_visits_service.repositories;

import com.booking.qs_visits_service.domain.VisitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VisitTypeRepository extends JpaRepository<VisitType, String> {

    @Query(value = "Select vt from VisitType vt Where vt.orgId = ?1 AND vt.locationId = ?2")
    List<VisitType> findByOrgAndLocation(String orgId, String locationId);

    @Query(value = "Select vt from VisitType vt Where vt.orgId = ?1 AND vt.locationId = ?2 AND vt.code = ?3")
    Optional<VisitType> findVisitTypeByCode(String orgId, String locationId, String code);
}
