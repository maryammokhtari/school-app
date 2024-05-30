package com.example.school.controller;

import com.example.school.repository.entity.Teacher;
import com.example.school.service.TeacherService;
import com.example.school.service.dto.TeacherRequest;
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
        return ResponseEntity.ok(teacherService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody @Valid TeacherRequest teacherRequest) {
        return ResponseEntity.ok(teacherService.create(teacherRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable Long id, @RequestBody @Valid TeacherRequest teacherRequest) {
        Teacher updateTeacher = teacherService.update(id, teacherRequest);
        return ResponseEntity.ok(updateTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.ok().build();
    }
}
