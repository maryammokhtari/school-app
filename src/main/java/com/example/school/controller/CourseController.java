package com.example.school.controller;

import com.example.school.repository.entity.Course;
import com.example.school.service.CourseService;
import com.example.school.service.dto.CourseRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody @Valid CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.create(courseRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody @Valid CourseRequest courseRequest) {
        Course updatedCourse = courseService.update(id, courseRequest);
        return ResponseEntity.ok(updatedCourse);
    }
}
