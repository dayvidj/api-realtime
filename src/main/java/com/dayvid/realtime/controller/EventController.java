package com.dayvid.realtime.controller;

import com.dayvid.realtime.dto.EventDTO;
import com.dayvid.realtime.dto.EventDataDTO;
import com.dayvid.realtime.dto.EventTypeCount;
import com.dayvid.realtime.model.Event;
import com.dayvid.realtime.service.EventService;
import jakarta.validation.Valid;
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
    public ResponseEntity<EventDataDTO> createEvent(@RequestBody @Valid EventDTO eventDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(eventDTO));
    }

    /*
    Fornece um stream contínuo (SSE) de novos eventos.
    Clientes permanecem conectados recebendo eventos em tempo real.
    */
    @GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Event> streamEvents() {
        return service.stream();
    }

    // Retorna estatísticas de contagem de eventos por tipo nos últimos minutos.
    @GetMapping("/stats")
    public ResponseEntity<List<EventTypeCount>> getStats(@RequestParam int minutes) {
        return ResponseEntity.ok(service.stats(minutes));
    }

    @GetMapping
    public ResponseEntity<List<EventDataDTO>> getAllEvents() {
        List<EventDataDTO> events = service.getAllEvents();
        return ResponseEntity.ok(events);
    }

}
