package com.booking.qs_visits_service.controllers;

import com.booking.qs_visits_service.domain.VisitType;
import com.booking.qs_visits_service.dtos.Response;
import com.booking.qs_visits_service.dtos.visittypes.CreateVisitTypeRequest;
import com.booking.qs_visits_service.services.VisitTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VisitTypeController {

    @Autowired
    private VisitTypesService service;

    @PostMapping("/api/visittype/create/")
    public Response<VisitType> createVisitType(@RequestBody CreateVisitTypeRequest request) {
        return service.createVisitType(request);
    }

    @GetMapping("/api/visittype/orgId/{orgId}/location/{location}")
    public Response<List<VisitType>> listVisitTypesByLocation(@PathVariable String orgId, @PathVariable String location) {
        return service.listVisitTypesByLocation(orgId, location);
    }
}
