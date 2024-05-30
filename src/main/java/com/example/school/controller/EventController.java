package com.example.school.controller;

import com.example.school.repository.entity.Event;
import com.example.school.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getAll() {
        return ResponseEntity.ok(eventService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getById(id).get());
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody @Valid Event event) {
        return ResponseEntity.ok(eventService.create(event));
    }

    @PutMapping
    public ResponseEntity<Event> update(@RequestBody @Valid Event event) {
        return ResponseEntity.ok(eventService.update(event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.ok().build();
    }
}
