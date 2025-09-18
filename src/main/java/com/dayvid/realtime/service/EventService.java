package com.dayvid.realtime.service;

import com.dayvid.realtime.dto.EventDTO;
import com.dayvid.realtime.dto.EventDataDTO;
import com.dayvid.realtime.model.Event;
import com.dayvid.realtime.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    private final Sinks.Many<Event> sink = Sinks.many().multicast().onBackpressureBuffer();

    @Transactional
    public EventDataDTO save(EventDTO eventDTO) {
        var event = repository.save(new Event(eventDTO));
        sink.tryEmitNext(event);
        return new EventDataDTO(event);
    }

    public Flux<Event> stream() {
        return sink.asFlux();
    }

    public List<EventDataDTO> getAllEvents() {
        return repository.findAll().stream().map(EventDataDTO::new).toList();
    }

}
