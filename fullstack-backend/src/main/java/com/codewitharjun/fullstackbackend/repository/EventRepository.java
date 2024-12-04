package com.codewitharjun.fullstackbackend.repository;

import com.codewitharjun.fullstackbackend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
