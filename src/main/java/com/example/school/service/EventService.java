package com.example.school.service;

import com.example.school.repository.EventRepository;
import com.example.school.repository.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    public Optional<Event> getById(Long id) {
        return eventRepository.findById(id);
    }

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Event event) {
        return eventRepository.save(event);
    }

    public void delete(Long id) {
        eventRepository.deleteById(id);
    }

}
