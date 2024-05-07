package com.example.school.service;

import com.example.school.repository.StudentRepository;
import com.example.school.repository.model.Student;
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
public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;
    @InjectMocks
    StudentService studentService;

    Student student;

    @BeforeEach
    void setUp() {
        student = new Student(1L, "maryam", "mokhtarifar", "tehran");
    }

    @Test
    void testCreateStudent() {
        studentService.create(student);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testDeleteStudent() {
        studentService.delete(student.getId());
        verify(studentRepository, times(1)).deleteById(student.getId());
    }

    @Test
    void testUpdateStudent() {
        studentService.update(student);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetAllStudent() {
        List<Student> list = new ArrayList<>();
        Student student1 = new Student(2L, "hami", "rahmani", "navan");
        Student student2 = new Student(3L, "mahsa", "mokhtri", "teharan");
        Student student3 = new Student(4L, "zahra", "abazari", "tabriz");
        list.add(student1);
        list.add(student2);
        list.add(student3);
        when(studentRepository.findAll()).thenReturn(list);

        List<Student> students = studentService.getAll();
        assertEquals(3, students.size());
        verify(studentRepository).findAll();
    }

    @Test
    void testGetById() {
        when(studentRepository.findById(student.getId())).thenReturn(Optional.ofNullable(student));
        Student result = studentService.findById(student.getId());
        assertEquals(result.getFirstName(), student.getFirstName());
        verify(studentRepository, times(1)).findById(student.getId());
    }


}
