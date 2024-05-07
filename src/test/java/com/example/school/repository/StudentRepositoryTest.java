package com.example.school.repository;

import com.example.school.repository.model.Student;
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
        //when: action that we are going to test
        Student student1 = studentRepository.save(student);
        //then: verify the output
        assertThat(student1).isNotNull();
        assertThat(student1.getId()).isGreaterThan(0);

    }

    @Test
    @DisplayName("junit test for get student list")
    void testGetAllStudent() {
        //given:setup object or precondition
        Student student2 = Student.builder()
                .firstName("hami")
                .lastName("rahmani")
                .city("navan")
                .build();
        Student student3 = Student.builder()
                .firstName("mahsa")
                .lastName("mokhtari")
                .city("tehran")
                .build();
        studentRepository.save(student2);
        studentRepository.save(student3);
        //when: action that we are going test
        List<Student> students = studentRepository.findAll();
        //then: verify the output
        assertThat(students.size()).isEqualTo(2);
        assertThat(students).isNotNull();

    }

    @Test
    @DisplayName("junit test for get student by id operation")
    void testGetByIdStudent() {
        //given: setup object o precondition
        studentRepository.save(student);
        //when: action that we are going to test
        Student result = studentRepository.findById(student.getId()).get();
        //then: verify the output or expected result
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("junit test for update student by id operation")
    void testUpdateStudent() {
        //given:setup the object or precondition
        studentRepository.save(student);
        //when:action we are going to test
        Student getStudent = studentRepository.findById(student.getId()).get();
        getStudent.setFirstName("zahra");
        getStudent.setLastName("abazari");
        getStudent.setCity("tabriz");
        Student updatedStudent = studentRepository.save(getStudent);
        //then: verify the output or expected result
        assertThat(updatedStudent).isNotNull();
        assertThat(updatedStudent.getFirstName()).isEqualTo("zahra");
    }

    @Test
    @DisplayName("junit test for delete student operation")
    void testDeleteStudent() {
        //given:setup object or precondition
        studentRepository.save(student);
        //when: action that we are going to test
        studentRepository.deleteById(student.getId());

        Optional<Student> deletedStudent = studentRepository.findById(student.getId());
        //then: verify the output
        assertThat(deletedStudent).isEmpty();

    }

}
