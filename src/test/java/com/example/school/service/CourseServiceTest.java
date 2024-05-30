package com.example.school.service;

import com.example.school.repository.CourseRepository;
import com.example.school.repository.entity.Course;
import com.example.school.service.dto.CourseRequest;
import com.example.school.service.mapper.CourseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    CourseRepository courseRepository;
    @Mock
    CourseMapper courseMapper;
    @InjectMocks
    CourseService courseService;
    Course course;
    CourseRequest courseRequest;

    @BeforeEach
    void setUp() {
        course = new Course(1L, "zaban", 7);
        courseRequest = new CourseRequest("zaban", 7);
    }

    @Test
    void testCreateCourse() {
        when(courseMapper.courseRequestToCourse(courseRequest)).thenReturn(course);

        courseService.create(courseRequest);
        verify(courseRepository, times(1)).save(course);
//        verify(courseRepository).save(course);
    }

    @Test
    void testDeleteCourse() {
        when(courseRepository.existsById(course.getId())).thenReturn(true);
        courseService.delete(course.getId());
        verify(courseRepository, times(1)).deleteById(course.getId());
    }

    @Test
    void testDeleteCourse_ThrowsException() {
        when(courseRepository.existsById(course.getId())).thenReturn(false);
        assertThrows(Exception.class, () -> {
            courseService.delete(course.getId());
        });
        verify(courseRepository, times(0)).deleteById(course.getId());
    }

    @Test
    void testUpdateCourse() {
        when(courseMapper.courseUpdateRequestToCourse(courseRequest, course.getId())).thenReturn(course);

        when(courseRepository.existsById(course.getId())).thenReturn(true);

        courseService.update(course.getId(), courseRequest);

        verify(courseRepository).save(course);

    }
    @Test
    void testUpdateCourse_ThrowException() {

        when(courseRepository.existsById(course.getId())).thenReturn(false);
        assertThrows(Exception.class, () -> courseService.update(course.getId(), courseRequest));
        verify(courseRepository, times(0)).save(course);
    }

    @Test
    void testGetAllCourse() {
        List<Course> list = new ArrayList<>();
        Course course1 = new Course(2L, "math", 6);
        Course course2 = new Course(3L, "phisiks", 8);
        Course course3 = new Course(4L, "chemistery", 10);
        list.add(course1);
        list.add(course2);
        list.add(course3);

        when(courseRepository.findAll()).thenReturn(list);


        List<Course> courses = courseService.getAll();
        assertEquals(3, courses.size());
        verify(courseRepository).findAll();
    }

    @Test
    void testGetById() {
        when(courseRepository.findById(course.getId())).thenReturn(Optional.ofNullable(course));

        Course result = courseService.findById(course.getId());

        assertEquals(result.getName(), course.getName());

        verify(courseRepository, times(1)).findById(course.getId());

    }


}
