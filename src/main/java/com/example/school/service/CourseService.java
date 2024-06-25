package com.example.school.service;

import com.example.school.exception.ResourceNotFoundException;
import com.example.school.repository.CourseRepository;
import com.example.school.repository.entity.Course;
import com.example.school.service.dto.CourseRequest;
import com.example.school.service.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {


    private static final String COURSE_DOESN_T_EXIST = "Course doesn't exist";
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course findById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()){
            log.debug("course is found: " + course.get());
            return course.get();
        }
        throw new ResourceNotFoundException(COURSE_DOESN_T_EXIST);
    }

    public Course create(CourseRequest courseRequest) {

        Course course = courseMapper.courseRequestToCourse(courseRequest);

        return courseRepository.save(course);
    }

    public Course update(Long id, CourseRequest courseRequest) {
        if (!courseRepository.existsById(id))
            throw new ResourceNotFoundException(COURSE_DOESN_T_EXIST);
        Course course = courseMapper.courseUpdateRequestToCourse(courseRequest, id);
        return courseRepository.save(course);
    }

    public void delete(Long id) {
        if (!courseRepository.existsById(id))
            throw new ResourceNotFoundException(COURSE_DOESN_T_EXIST);
        courseRepository.deleteById(id);
    }
}
