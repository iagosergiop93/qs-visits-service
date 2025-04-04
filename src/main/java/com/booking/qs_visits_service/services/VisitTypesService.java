package com.booking.qs_visits_service.services;

import com.booking.qs_visits_service.domain.VisitType;
import com.booking.qs_visits_service.dtos.Message;
import com.booking.qs_visits_service.dtos.Response;
import com.booking.qs_visits_service.dtos.visittypes.CreateVisitTypeRequest;
import com.booking.qs_visits_service.repositories.VisitTypeRepository;
import com.booking.qs_visits_service.utils.VisitTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitTypesService {

    @Autowired
    private VisitTypeRepository repo;

    public Response<VisitType> createVisitType(CreateVisitTypeRequest request) {
        var response = new Response<VisitType>();
        try {
            var visitType = VisitTypeUtils.createVisitType(request);
            visitType = repo.save(visitType);
            response
                    .with(visitType)
                    .withSuccess(true);
        } catch (Throwable ex) {
            response
                    .addMessage(new Message("500", ex.getMessage()))
                    .withSuccess(false);
        }
        return response;
    }

    public Response<List<VisitType>> listVisitTypesByLocation(String orgId, String locationId) {
        var response = new Response<List<VisitType>>();
        try {
            var visitTypes = repo.findByOrgAndLocation(orgId, locationId);
            response.with(visitTypes).withSuccess(true);
        } catch (Throwable ex) {
            response
                    .addMessage(new Message("500", ex.getMessage()))
                    .withSuccess(false);
        }
        return response;
    }
}
