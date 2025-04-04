package com.booking.qs_visits_service.messages.visits.producers;

import com.booking.qs_visits_service.dtos.visits.CreateVisitRequest;
import com.booking.qs_visits_service.messages.visits.BaseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class VisitsProducer {

    @Autowired
    private StreamBridge streamBridge;

    public Boolean createVisitRequested(CreateVisitRequest request) {
        var baseMessage = new BaseMessage<CreateVisitRequest>(request);
        var message = new GenericMessage<>(baseMessage);
        return streamBridge.send("create-visit-requested", message);
    }

    public Boolean visitCreated(String dto) {
        var baseMessage = new BaseMessage<String>();
        var message = new GenericMessage<>(baseMessage);
        return streamBridge.send("visit-created", message);
    }
}
