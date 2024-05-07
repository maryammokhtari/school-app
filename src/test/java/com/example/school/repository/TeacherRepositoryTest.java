package com.example.school.repository;

import com.example.school.repository.model.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest

public class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        teacher = Teacher.builder()
                .firstName("maryam")
                .lastName("mokhtari")
                .salary(500.0)
                .build();
    }

    @Test
    @DisplayName("junit test for save teacher operation")
    void testCreateTeacher() {
        //when
        Teacher teacher1 = teacherRepository.save(teacher);
        //then
        assertThat(teacher1).isNotNull();
        assertThat(teacher1.getId()).isGreaterThan(0);

    }

    @Test
    @DisplayName("junit test for get teacher list")
    void testgetAllTeachers() {
        //given:
        Teacher teacher1 = Teacher.builder()
                .firstName("hami")
                .lastName("rahmani")
                .salary(600.0)
                .build();
        Teacher teacher2 = Teacher.builder()
                .firstName("mahsa")
                .lastName("mokhtari")
                .salary(900.0)
                .build();
        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);
        //when
        List<Teacher> teachers = teacherRepository.findAll();
        //then
        assertThat(teachers.size()).isEqualTo(2);
        assertThat(teachers).isNotNull();


    }

    @Test
    @DisplayName("junit test for get by id teacher operation")
    void testGetByIdTeacher() {
        //given
        teacherRepository.save(teacher);
        //when
        Teacher teacher1 = teacherRepository.findById(teacher.getId()).get();
        //then
        assertThat(teacher1).isNotNull();

    }

    @Test
    @DisplayName("junit test for update teacher opertion")
    void testUpdateTeacher() {
        //given
        teacherRepository.save(teacher);
        //when
        Teacher getTeacher = teacherRepository.findById(teacher.getId()).get();
        getTeacher.setFirstName("zahra");
        getTeacher.setLastName("abazari");
        getTeacher.setSalary(500.0);
        Teacher updatedTeacher = teacherRepository.save(getTeacher);
        //then
        assertThat(updatedTeacher).isNotNull();
        assertThat(updatedTeacher.getFirstName()).isEqualTo("zahra");

    }

    @Test
    @DisplayName("junit test for delete teacher operation")
    void testDeleteTeacher() {
        //given
        teacherRepository.save(teacher);
        //when
        teacherRepository.deleteById(teacher.getId());
        Optional<Teacher> deletedTeacher = teacherRepository.findById(teacher.getId());
        //then
        assertThat(deletedTeacher).isEmpty();
    }
}
