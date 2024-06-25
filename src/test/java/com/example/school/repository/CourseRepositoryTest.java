package com.example.school.repository;

import com.example.school.repository.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;
    private Course course;

    @BeforeEach
    void setUp() {
        course = Course.builder()
                .name("Math")
                .capacity(10)
                .build();
    }

    @Test
    @DisplayName("junit test for save new course operation")
    void testCreateCourse() {
        //when:action that we are going to be test
        Course course1 = courseRepository.save(course);
        //then:verify the output
        assertThat(course1).isNotNull();
        assertThat(course1.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("junit test for get course list")
    void testGetAllCourse() {
        List<Course> courses = courseRepository.findAll();
        assertThat(courses).isNotNull();
        assertThat(courses.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("junit test for get course by id operation")
    void testGetByIdCourse() {
        courseRepository.save(course);
        Course result = courseRepository.findById(course.getId()).get();
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("junit test for update course operation")
    void testUpdateCourse() {
        courseRepository.save(course);
        Course getCourse = courseRepository.findById(course.getId()).get();
        getCourse.setName("dini");
        getCourse.setCapacity(90);
        Course updatedCourse = courseRepository.save(getCourse);
        assertThat(updatedCourse).isNotNull();
        assertThat(updatedCourse.getName()).isEqualTo("dini");
    }

    @Test
    @DisplayName("junit test for delete course operation")
    void testDeleteCourse() {
        courseRepository.save(course);
        courseRepository.deleteById(course.getId());
        Optional<Course> result = courseRepository.findById(course.getId());
        assertThat(result).isEmpty();
    }


}
