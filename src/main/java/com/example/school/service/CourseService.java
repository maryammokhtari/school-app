package com.example.school.service;

import com.example.school.repository.CourseRepository;
import com.example.school.repository.model.Course;
import lombok.SneakyThrows;
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

   public Course findById(Long id) {
        return courseRepository.findById(id).get();
    }

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @SneakyThrows
    public Course update(Course course) {
        if(!courseRepository.existsById(course.getId()))
            throw new Exception("Course doesn't exist");
        return courseRepository.save(course);
    }

    @SneakyThrows
    public void delete(Long id) {
        if(!courseRepository.existsById(id))
            throw new Exception("Course doesn't exist");
        courseRepository.deleteById(id);
    }
}
