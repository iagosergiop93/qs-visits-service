package com.booking.qs_visits_service.services;

import com.booking.qs_visits_service.domain.Status;
import com.booking.qs_visits_service.domain.VisitStatus;
import com.booking.qs_visits_service.domain.VisitType;
import com.booking.qs_visits_service.dtos.Message;
import com.booking.qs_visits_service.dtos.Response;
import com.booking.qs_visits_service.dtos.visits.CreateVisitRequest;
import com.booking.qs_visits_service.dtos.visits.CreateVisitResponse;
import com.booking.qs_visits_service.dtos.visits.VisitTimeslotReserved;
import com.booking.qs_visits_service.messages.visits.producers.VisitsProducer;
import com.booking.qs_visits_service.repositories.CustomerRepository;
import com.booking.qs_visits_service.repositories.VisitRepository;
import com.booking.qs_visits_service.repositories.VisitStatusRepository;
import com.booking.qs_visits_service.repositories.VisitTypeRepository;
import com.booking.qs_visits_service.utils.CustomerUtils;
import com.booking.qs_visits_service.utils.VisitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class CreateVisitService {

    @Autowired
    private VisitTypeRepository visitTypeRepo;

    @Autowired
    private VisitRepository visitRepo;

    @Autowired
    private VisitStatusRepository visitStatusRepo;

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private VisitsProducer visitsProducer;

    public Response<CreateVisitResponse> createVisit(CreateVisitRequest request) {
        var response = new Response<CreateVisitResponse>();
        // Validate orgId and locationId
        if(request.getOrgId() == null || request.getOrgId().isEmpty()) {
            response
                    .addMessage(new Message("400", "Invalid organization"))
                    .withSuccess(false);
        }
        if(request.getLocationId() == null || request.getLocationId().isEmpty()) {
            response
                    .addMessage(new Message("400", "Invalid location"))
                    .withSuccess(false);
        }
        // Validate startTime
        // endTime is optional and is normally determined by the appointment type
        if(request.getDay() == null || request.getStartTime() == null) {
            response
                    .addMessage(new Message("400", "Invalid start time"))
                    .withSuccess(false);
        }
        // Validate visitTypeId
        Optional<VisitType> visitType;
        try {
            visitType = visitTypeRepo.findVisitTypeByCode(request.getOrgId(), request.getLocationId(), request.getVisitTypeCode());
            if(visitType.isEmpty()) {
                return response
                        .addMessage(new Message("400", "Visit Type not found"))
                        .withSuccess(false);
            }
        } catch (Throwable ex) {
            return response
                    .addMessage(new Message("500", "Error validating visit type"))
                    .withSuccess(false);
        }

        // generate id
        var visitId = UUID.randomUUID().toString();
        request.setId(visitId);

        try {
            // Save Customer
            var customer = CustomerUtils.createCustomer(request.getCustomer());

            // Save createVisit with status requested
            var visit = VisitUtils.createVisit(request);
            visit.setId(visitId);
            visit.setVisitType(visitType.get());
            visit.setCustomer(customer);

            var timeslot = visit.getTimeslot();
            if(request.getEndTime() == null) {
                var durationMin = visitType.get().getDurationMin();
                timeslot.setEndTime(timeslot.getStartTime().plusMinutes(durationMin));
                timeslot.setSlotDurationMin(durationMin);

                request.setEndTime(timeslot.getEndTime());
            }

            // Create Visit Status
            var visitStatus = new VisitStatus();
            visitStatus.setId(UUID.randomUUID().toString());
            visitStatus.setStatus(Status.REQUESTED);
            visitStatus.setTimestamp(Instant.now());
            visitStatus.setVisitId(visitId);

            customerRepo.save(customer);
            visitRepo.save(visit);
            visitStatusRepo.save(visitStatus);

            customerRepo.flush();
            visitRepo.flush();
            visitStatusRepo.flush();

        } catch (Throwable ex) {
            return response
                    .addMessage(new Message("500", "Error saving visit"))
                    .withSuccess(false);
        }

        // check availability
        visitsProducer.createVisitRequested(request);

        var cvr = new CreateVisitResponse();
        cvr.setId(visitId);
        cvr.setOrgId(request.getOrgId());
        cvr.setLocationId(request.getLocationId());

        response.with(cvr).withSuccess(true);

        return response;
    }

    public void visitTimeslotReserved(VisitTimeslotReserved request) {
        var visitOpt = visitRepo.findById(request.getId());
        visitOpt.ifPresentOrElse(visit -> {
            // Create Visit Status
            var visitStatusOpt = visitStatusRepo.findByVisitId(visit.getId());
            visitStatusOpt.ifPresent(visitStatus -> {
                if(request.getSlotReserved()) {
                    visit.setConfirmationCode(VisitUtils.generateVisitConfCode());
                    visitStatus.setStatus(Status.SCHEDULED);
                    visitStatus.setTimestamp(Instant.now());

                    visitRepo.save(visit);
                    visitStatusRepo.save(visitStatus);

                    visitRepo.flush();
                    visitStatusRepo.flush();
                }
                else {
                    visitStatus.setStatus(Status.SLOT_UNAVAILABLE);
                    visitStatus.setTimestamp(Instant.now());
                    visitStatusRepo.saveAndFlush(visitStatus);
                }
            });
        }, () -> {
            System.out.println("Couldn't find visit");
        });
    }


}
