package com.dayvid.realtime.controller;

import com.dayvid.realtime.dto.EventDTO;
import com.dayvid.realtime.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    EventService service;

    @PostMapping
    public ResponseEntity<Long> createEvent(@RequestBody EventDTO eventDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(eventDTO));
    }

}
