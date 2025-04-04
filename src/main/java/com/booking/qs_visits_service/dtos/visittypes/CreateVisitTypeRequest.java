package com.booking.qs_visits_service.dtos.visittypes;

import lombok.Data;

@Data
public class CreateVisitTypeRequest {
    private String orgId;
    private String locationId;
    private String code;
    private String description;
    private Integer durationMin;
}
