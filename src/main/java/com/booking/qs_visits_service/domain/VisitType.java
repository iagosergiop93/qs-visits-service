package com.booking.qs_visits_service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "visit_type")
public class VisitType {
    @Id
    private String id;
    private String orgId;
    private String locationId;

    @Column(unique = true)
    private String code;

    private String description;
    private Integer durationMin;
}
