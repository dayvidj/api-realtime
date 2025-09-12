package com.dayvid.realtime.repository;

import com.dayvid.realtime.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
