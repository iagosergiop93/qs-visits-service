package com.booking.qs_visits_service.dtos.visits;

import lombok.Data;

@Data
public class CreateVisitResponse {
    private String id;
    private String orgId;
    private String locationId;
}
