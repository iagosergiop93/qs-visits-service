package com.booking.qs_visits_service.controllers;

import com.booking.qs_visits_service.domain.Visit;
import com.booking.qs_visits_service.dtos.Response;
import com.booking.qs_visits_service.dtos.visits.CreateVisitRequest;
import com.booking.qs_visits_service.dtos.visits.CreateVisitResponse;
import com.booking.qs_visits_service.services.CreateVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateVisitController {

    @Autowired
    private CreateVisitService service;

    @PostMapping("/api/visits/createVisit")
    public Response<CreateVisitResponse> createVisit(@RequestBody CreateVisitRequest request) {
        return service.createVisit(request);
    }

}
