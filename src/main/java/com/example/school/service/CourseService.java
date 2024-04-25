package com.example.school.service;

import com.example.school.repository.CourseRepository;
import com.example.school.repository.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {


    private final CourseRepository courseRepository;

    @Autowired
   public CourseService(CourseRepository cr) {
        this.courseRepository = cr;
    }

   public List<Course> getAll() {
        return courseRepository.findAll();
    }

   public Course getById(Long id) {
        return courseRepository.getById(id);
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public Course update(Course course) {
        return courseRepository.save(course);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
