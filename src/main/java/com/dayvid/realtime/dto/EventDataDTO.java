package com.dayvid.realtime.dto;

import com.dayvid.realtime.model.Event;

public record EventDataDTO(Long id, String type, String payload, String timestamp) {

    public EventDataDTO(Event event) {
        this(event.getId(), event.getType(), event.getPayload(), event.getTimestamp().toString());
    }

}
