package com.example.school.repository;

import com.example.school.repository.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    private Student student;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .firstName("Maryam")
                .lastName("Mokhtari")
                .city("Tehran")
                .build();
    }

    @Test
    @DisplayName("junit test for save new student operation")
    void testCreateStudent() {
        Student student1 = studentRepository.save(student);
        assertThat(student1).isNotNull();
        assertThat(student1.getId()).isGreaterThan(0);

    }

    @Test
    @DisplayName("junit test for get student list")
    void testGetAllStudent() {
        List<Student> students = studentRepository.findAll();
        assertThat(students.size()).isEqualTo(2);
        assertThat(students).isNotNull();

    }

    @Test
    @DisplayName("junit test for get student by id operation")
    void testGetByIdStudent() {
        studentRepository.save(student);
        Student result = studentRepository.findById(student.getId()).get();
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("junit test for update student by id operation")
    void testUpdateStudent() {
        studentRepository.save(student);
        Student getStudent = studentRepository.findById(student.getId()).get();
        getStudent.setFirstName("zahra");
        getStudent.setLastName("abazari");
        getStudent.setCity("tabriz");
        Student updatedStudent = studentRepository.save(getStudent);
        assertThat(updatedStudent).isNotNull();
        assertThat(updatedStudent.getFirstName()).isEqualTo("zahra");
    }

    @Test
    @DisplayName("junit test for delete student operation")
    void testDeleteStudent() {
        studentRepository.save(student);
        studentRepository.deleteById(student.getId());

        Optional<Student> deletedStudent = studentRepository.findById(student.getId());
        assertThat(deletedStudent).isEmpty();

    }

}
