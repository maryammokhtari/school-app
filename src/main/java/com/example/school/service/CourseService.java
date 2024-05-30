package com.example.school.service;

import com.example.school.repository.CourseRepository;
import com.example.school.repository.entity.Course;
import com.example.school.service.dto.CourseRequest;
import com.example.school.service.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {


    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

//    @Autowired
//    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
//        this.courseRepository = courseRepository;
//        this.courseMapper = courseMapper;
//    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

   public Course findById(Long id) {
        return courseRepository.findById(id).get();
    }

    public Course create(CourseRequest courseRequest) {

        Course course = courseMapper.courseRequestToCourse(courseRequest);

        return courseRepository.save(course);
    }

    @SneakyThrows
    public Course update(Long id, CourseRequest courseRequest) {
        if(!courseRepository.existsById(id))
            throw new Exception("Course doesn't exist");
        Course course= courseMapper.courseUpdateRequestToCourse(courseRequest,id);
//        course.setId(id);
        return courseRepository.save(course);
    }

    @SneakyThrows
    public void delete(Long  id) {
        if(!courseRepository.existsById(id))
            throw new Exception("Course doesn't exist");
        courseRepository.deleteById(id);
    }
}
