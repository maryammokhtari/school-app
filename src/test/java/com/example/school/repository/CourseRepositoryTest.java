package com.example.school.repository;

import com.example.school.repository.model.Course;
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
        //given: setup object or preconditi
        Course course2 = Course.builder()
                .name("physiks")
                .capacity(7)
                .build();
        Course course3 = Course.builder()
                .name("chemistery")
                .capacity(15)
                .build();
        courseRepository.save(course2);
        courseRepository.save(course3);
        //when:action that we are going to test
        List<Course> courses = courseRepository.findAll();
        //then:verify the output
        assertThat(courses).isNotNull();
        assertThat(courses.size()).isEqualTo(2);


    }

    @Test
    @DisplayName("junit test for get course by id operation")
    void testGetByIdCourse() {
        //give:setup object or precondition
        courseRepository.save(course);
        //when:action that we are going to test
        Course result = courseRepository.findById(course.getId()).get();
        //then: verify the output or expected result
        assertThat(result).isNotNull();


    }

    @Test
    @DisplayName("junit test for update course operation")
    void testUpdateCourse() {
        //given:setup object or precondition
        courseRepository.save(course);
        //when:action that we are going to test
        Course getCourse = courseRepository.findById(course.getId()).get();
        getCourse.setName("dini");
        getCourse.setCapacity(90);
        Course updatedCourse = courseRepository.save(getCourse);
        //then: verify the output or expected result
        assertThat(updatedCourse).isNotNull();
        assertThat(updatedCourse.getName()).isEqualTo("dini");
    }

    @Test
    @DisplayName("junit test for delete course operation")
    void testDeleteCourse() {
        //given: setup object or precondition
        courseRepository.save(course);
        //when: action or behavior that we are going to test
        courseRepository.deleteById(course.getId());
        Optional<Course> result = courseRepository.findById(course.getId());
        //then:verify the output or expected result
        assertThat(result).isEmpty();
    }


}
