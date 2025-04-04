package com.booking.qs_visits_service.messages.visits.consumers;

import com.booking.qs_visits_service.dtos.visits.VisitTimeslotReserved;
import com.booking.qs_visits_service.messages.visits.BaseMessage;
import com.booking.qs_visits_service.services.CreateVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.function.Consumer;

@Configuration
public class VisitsConsumer {

    @Autowired
    private CreateVisitService createVisitService;

    @Bean
    public Consumer<BaseMessage<VisitTimeslotReserved>> visittimeslotreserved() {
        return message -> {
            System.out.println("Inside consumer visitTimeslotReserved: ");
            System.out.println(message.toString());
            createVisitService.visitTimeslotReserved(message.getPayload());
        };
    }
}
