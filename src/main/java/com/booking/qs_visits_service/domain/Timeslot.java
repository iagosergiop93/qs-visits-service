package com.booking.qs_visits_service.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Embeddable
public class Timeslot {
    private Integer slotDurationMin;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;
}
