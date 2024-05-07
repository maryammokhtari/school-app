package com.example.school.service;

import com.example.school.repository.CourseRepository;
import com.example.school.repository.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    CourseRepository courseRepository;
    @InjectMocks
    CourseService courseService;
    Course course;

    @BeforeEach
    void setUp(){
        course= new Course(1L,"zaban",7);
    }

    @Test
    void testCreateCourse(){
        courseService.create(course);
        verify(courseRepository , times(1)).save(course);
//        verify(courseRepository).save(course);
    }
    @Test
    void testDeleteCourse(){
        courseService.delete(course.getId());
        verify(courseRepository , times(1)).deleteById(course.getId());
    }
    @Test
    void testUpdateCourse(){
        courseService.update(course);
        verify(courseRepository).save(course);

    }
    @Test
    void testGetAllCourse(){
        List<Course> list= new ArrayList<>();
        Course course1=new Course(2L,"math",6);
        Course course2=new Course(3L,"phisiks",8);
        Course course3=new Course(4L,"chemistery",10);
        list.add(course1);
        list.add(course2);
        list.add(course3);

        when(courseRepository.findAll()).thenReturn(list);


        List<Course> courses = courseService.getAll();
        assertEquals(3,courses.size());
        verify(courseRepository).findAll();
    }
    @Test
    void testGetById(){
        when(courseRepository.findById(course.getId())).thenReturn(Optional.ofNullable(course));

        Course result = courseService.findById(course.getId());

       assertEquals(result.getName(),course.getName());

        verify(courseRepository,times( 1)).findById(course.getId());

    }


}
