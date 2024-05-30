package com.example.school.service;

import com.example.school.repository.StudentRepository;
import com.example.school.repository.entity.Student;
import com.example.school.service.dto.StudentRequest;
import com.example.school.service.mapper.StudentMapper;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;
    @InjectMocks
    StudentService studentService;
    @Mock
    StudentMapper studentMapper;

    Student student;
    StudentRequest studentRequest;

    @BeforeEach
    void setUp() {
        student = new Student(1L, "maryam", "mokhtarifar", "tehran");
        studentRequest = new StudentRequest("maryam", "mokhtarifar", "tehran");
    }

    @Test
    void testCreateStudent() {
        when(studentMapper.studentRequestToStudent(studentRequest)).thenReturn(student);

        studentService.create(studentRequest);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testDeleteStudent() {
        when(studentRepository.existsById(student.getId())).thenReturn(true);
        studentService.delete(student.getId());
        verify(studentRepository, times(1)).deleteById(student.getId());
    }
    @Test
    void testDeleteStudent_ThrowsException() {
        when(studentRepository.existsById(student.getId())).thenReturn(false);
        assertThrows(Exception.class,()-> studentService.delete(student.getId()));
        verify(studentRepository, times(0)).deleteById(student.getId());
    }

    @Test
    void testUpdateStudent() {
        when(studentMapper.studentUpdateRequestToStudent(studentRequest,student.getId())).thenReturn(student);
        when(studentRepository.existsById(student.getId())).thenReturn(true);
        studentService.update(student.getId(), studentRequest);
        verify(studentRepository, times(1)).save(student);
    }
 @Test
    void testUpdateStudent_ThrowsException() {
        when(studentRepository.existsById(student.getId())).thenReturn(false);
        assertThrows(Exception.class,()->studentService.update(student.getId(),studentRequest));
        verify(studentRepository, times(0)).save(student);
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
