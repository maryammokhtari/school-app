package com.example.school.service;

import com.example.school.repository.TeacherRepository;
import com.example.school.repository.entity.Teacher;
import com.example.school.service.dto.TeacherRequest;
import com.example.school.service.mapper.TeacherMapper;
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
public class TeacherServiceTest {


    @Mock
    TeacherRepository teacherRepository;
    @Mock
    TeacherMapper teacherMapper;
    @InjectMocks
    TeacherServiceImp teacherService;

    Teacher teacher;
    TeacherRequest teacherRequest;

    @BeforeEach
    void setUp() {
        teacher = new Teacher(1L, "maryam", "mokhtari", 500.00,null);
        teacherRequest = new TeacherRequest("maryam", "mokhtari", 500.00);
    }

    @Test
    void testCreateTeacher() {
        when(teacherMapper.teacherRequestToTeacher(teacherRequest)).thenReturn(teacher);

        teacherService.create(teacherRequest);
        verify(teacherRepository, times(1)).save(teacher);
    }

    @Test
    void testDeleteTeacher() {
        when(teacherRepository.existsById(teacher.getId())).thenReturn(true);
        teacherService.delete(teacher.getId());
        verify(teacherRepository, times(1)).deleteById(teacher.getId());

    }
    @Test
    void testDeleteTeacher_ThrowException() {
        when(teacherRepository.existsById(teacher.getId())).thenReturn(false);
        assertThrows(Exception.class,()->teacherService.delete(teacher.getId()));
        verify(teacherRepository, times(0)).deleteById(teacher.getId());

    }

    @Test
    void testUpdateTeacher() {
        when(teacherMapper.teacherUpdateRequestToTeacher(teacherRequest,teacher.getId())).thenReturn(teacher);
         when(teacherRepository.existsById(teacher.getId())).thenReturn(true);
        teacherService.update(teacher.getId(), teacherRequest);
        verify(teacherRepository, times(1)).save(teacher);
    }
    @Test
    void testUpdateTeacher_ThrowsException() {
        when(teacherRepository.existsById(teacher.getId())).thenReturn(false);
        assertThrows(Exception.class,()->
                teacherService.update(teacher.getId(), teacherRequest));
        verify(teacherRepository, times(0)).save(teacher);
    }

    @Test
    void testGetAllTeacher() {
        List<Teacher> list = new ArrayList<>();
        Teacher teacher1 = new Teacher(2L, "hami", "rahmani", 200.00,null);
        Teacher teacher2 = new Teacher(3L, "mahsa", "mokhtari", 200.00,null);
        Teacher teacher3 = new Teacher(4L, "zahra", "abazari", 300.00,null);
        list.add(teacher1);
        list.add(teacher2);
        list.add(teacher3);
        when(teacherRepository.findAll()).thenReturn(list);
        List<Teacher> teachers = teacherService.getAll();
        assertEquals(3, teachers.size());
        verify(teacherRepository, times(1)).findAll();
    }

    @Test
    void testGetById() {
        when(teacherRepository.findById(teacher.getId())).thenReturn(Optional.ofNullable(teacher));
        Teacher result = teacherService.findById(teacher.getId());
        assertEquals(result.getFirstName(), teacher.getFirstName());
        verify(teacherRepository, times(1)).findById(teacher.getId());
    }

}
