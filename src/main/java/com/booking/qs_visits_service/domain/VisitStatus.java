package com.booking.qs_visits_service.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "visits_status")
public class VisitStatus {
    @Id
    private String id;
    private String visitId;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    private Instant timestamp;
}
