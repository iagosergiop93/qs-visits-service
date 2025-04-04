package com.booking.qs_visits_service.utils;

import com.booking.qs_visits_service.domain.Timeslot;
import com.booking.qs_visits_service.domain.Visit;
import com.booking.qs_visits_service.dtos.visits.CreateVisitRequest;

import java.util.UUID;

public class VisitUtils {

    public static Visit createVisit(CreateVisitRequest request) {
        var visit = new Visit();
        visit.setId(request.getId());
        visit.setOrgId(request.getOrgId());
        visit.setLocationId(request.getLocationId());

        var timeslot = new Timeslot();
        timeslot.setDay(request.getDay());
        timeslot.setStartTime(request.getStartTime());
        if(request.getEndTime() != null) {
            timeslot.setEndTime(request.getEndTime());
            timeslot.setSlotDurationMin(
                    (int) Math.floor((request.getEndTime().toSecondOfDay() - request.getStartTime().toSecondOfDay())/60)
            );
        }
        visit.setTimeslot(timeslot);

        return visit;
    }

    public static String generateVisitConfCode() {
        return UUID.randomUUID().toString().substring(0,8);
    }

}
