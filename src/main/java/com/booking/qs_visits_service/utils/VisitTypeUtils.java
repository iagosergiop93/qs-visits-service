package com.booking.qs_visits_service.utils;

import com.booking.qs_visits_service.domain.VisitType;
import com.booking.qs_visits_service.dtos.visittypes.CreateVisitTypeRequest;

import java.util.UUID;

public class VisitTypeUtils {

    public static VisitType createVisitType(CreateVisitTypeRequest request) {
        var vt = new VisitType();
        vt.setOrgId(request.getOrgId());
        vt.setLocationId(request.getLocationId());
        vt.setCode(request.getCode());
        vt.setDescription(request.getDescription());
        vt.setDurationMin(request.getDurationMin());

        vt.setId(UUID.randomUUID().toString());

        return vt;
    }

}
