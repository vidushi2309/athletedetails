package com.codewitharjun.fullstackbackend.controller;

import com.codewitharjun.fullstackbackend.model.Event;
import com.codewitharjun.fullstackbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/event/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/event")
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @DeleteMapping("/event/{id}")
    public String deleteEvent(@PathVariable Long id) {
        return eventService.deleteEvent(id);
    }
}
