package com.example.school.service;

import com.example.school.repository.TeacherRepository;
import com.example.school.repository.model.Teacher;
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
public class TeacherServiceTest {


    @Mock
    TeacherRepository teacherRepository;
    @InjectMocks
    TeacherService teacherService;

    Teacher teacher;

    @BeforeEach
    void setUp() {
        teacher = new Teacher(1L, "maryam", "mokhtari", 500.00);
    }

    @Test
    void testCreateTeacher() {
        teacherService.create(teacher);
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    void testDeleteTeacher() {
        teacherService.delete(teacher.getId());
        verify(teacherRepository, times(1)).deleteById(teacher.getId());

    }

    @Test
    void testUpdateTeacher() {
        teacherService.update(teacher);
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    void testGetAllTeacher() {
        List<Teacher> list = new ArrayList<>();
        Teacher teacher1 = new Teacher(2L, "hami", "rahmani", 200.00);
        Teacher teacher2 = new Teacher(3L, "mahsa", "mokhtari", 200.00);
        Teacher teacher3 = new Teacher(4L, "zahra", "abazari", 300.00);
        list.add(teacher1);
        list.add(teacher2);
        list.add(teacher3);
        when(teacherRepository.findAll()).thenReturn(list);
        List<Teacher> teachers = teacherService.getAll();
        assertEquals(3, teachers.size());
        verify(teacherRepository, times(1)).findAll();
    }

    @Test
    void testGetById(){
        when(teacherRepository.findById(teacher.getId())).thenReturn(Optional.ofNullable(teacher));
        Teacher result = teacherService.findById(teacher.getId());
        assertEquals(result.getFirstName(),teacher.getFirstName());
        verify(teacherRepository,times(1)).findById(teacher.getId());
    }

}
