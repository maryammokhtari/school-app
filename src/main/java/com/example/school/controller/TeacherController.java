package com.example.school.controller;

import com.example.school.repository.model.Teacher;
import com.example.school.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody @Valid Teacher teacher){
        return ResponseEntity.ok(teacherService.create(teacher));
    }
    @PutMapping
    public ResponseEntity<Teacher> update(@RequestBody @Valid Teacher teacher){
        return ResponseEntity.ok(teacherService.update(teacher));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        teacherService.delete(id);
        return ResponseEntity.ok().build();
    }
}
