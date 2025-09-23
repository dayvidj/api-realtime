package com.dayvid.realtime.repository;

import com.dayvid.realtime.dto.EventTypeCount;
import com.dayvid.realtime.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    //Query JPQL para retornar a contagem de eventos por tipo desde um determinado tempo.
    @Query("SELECT new com.dayvid.realtime.dto.EventTypeCount (e.type, COUNT(e)) FROM Event e WHERE e.timestamp >= :since GROUP BY e.type")
    List<EventTypeCount> countEventsByTypeSince(@Param("since") LocalDateTime since);

}
