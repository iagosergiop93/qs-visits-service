package com.booking.qs_visits_service.messages.visits;

import com.booking.qs_visits_service.dtos.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class BaseMessage<T> {
    private String id;
    private T payload;
    private long timestamp = Instant.now().toEpochMilli();
    private Message errorMessage;

    public BaseMessage(T payload) {
        this.payload = payload;
    }
}
