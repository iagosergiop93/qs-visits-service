package com.booking.qs_visits_service.dtos.visits;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateVisitRequest {
    private String id;
    private String orgId;
    private String locationId;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String visitTypeCode;
    private CustomerDto customer;
}
