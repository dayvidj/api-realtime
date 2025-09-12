package com.dayvid.realtime.service;

import com.dayvid.realtime.dto.EventDTO;
import com.dayvid.realtime.model.Event;
import com.dayvid.realtime.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository repository;

    @Transactional
    public Long save(EventDTO eventDTO) {
        var event = repository.save(new Event(eventDTO));
        return event.getId();
    }
}
