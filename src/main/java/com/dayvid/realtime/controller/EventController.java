package com.dayvid.realtime.controller;

import com.dayvid.realtime.dto.EventDTO;
import com.dayvid.realtime.dto.EventDataDTO;
import com.dayvid.realtime.model.Event;
import com.dayvid.realtime.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService service;

    @PostMapping
    public ResponseEntity<EventDataDTO> createEvent(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(eventDTO));
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> streamEvents() {
        return service.stream();
    }

    @GetMapping
    public ResponseEntity<List<EventDataDTO>> getAllEvents() {
        List<EventDataDTO> events = service.getAllEvents();
        return ResponseEntity.ok(events);
    }

}
