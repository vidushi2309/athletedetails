package com.codewitharjun.fullstackbackend.service;

import com.codewitharjun.fullstackbackend.exception.UserNotFoundException;
import com.codewitharjun.fullstackbackend.model.Event;
import com.codewitharjun.fullstackbackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    /**
     * Fetch all events.
     * @return a list of all events.
     */
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    /**
     * Fetch a single event by ID.
     * @param id the event ID.
     * @return the event details.
     * @throws UserNotFoundException if the event is not found.
     */
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)); // Reuse exception for simplicity
    }

    /**
     * Create a new event.
     * @param event the event to create.
     * @return the created event.
     */
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    /**
     * Delete an event by ID.
     * @param id the event ID.
     * @return a confirmation message.
     */
    public String deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        eventRepository.deleteById(id);
        return "Event with id " + id + " has been deleted successfully.";
    }
}
