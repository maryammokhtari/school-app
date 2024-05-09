package com.example.school.controller;

import com.example.school.repository.model.Student;
import com.example.school.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @MockBean
    StudentService studentService;
    @Autowired
    MockMvc mockMvc;
    Student student;
    String path = "/api/v1/students";

    @BeforeEach
    void setUp() {
        student = new Student(1L, "maryam", "mokhtai", "tehran");
    }

    @SneakyThrows
    @Test
    public void testGetAll() {
        Student student1 = new Student(2L, "hami", "rahmai", "navan");
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student);
        when(studentService.getAll()).thenReturn(students);

        mockMvc.perform(get(path))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("hami")));


    }

    @SneakyThrows
    @Test
    void testGetById() {
        when(studentService.findById(student.getId())).thenReturn(student);
        mockMvc.perform(get(path + "/{id}", student.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()));
    }

    @SneakyThrows
    @Test
    void testCreateStudent() {
        when(studentService.create(student)).thenReturn(student);

        mockMvc.perform(post(path)
                        .content(new ObjectMapper().writeValueAsString(student))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()));


    }
    @SneakyThrows
    @Test
    void testUpdateStudent(){
        Student student1 = new Student(2L, "hami", "rahmai", "navan");
        when(studentService.update(student1)).thenReturn(student1);
        mockMvc.perform(put(path)
                .content(new ObjectMapper().writeValueAsString(student1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student1.getId()))
                .andExpect(jsonPath("$.firstName").value(student1.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(student1.getLastName()))
                .andExpect(jsonPath("$.city").value(student1.getCity()));
    }
    @SneakyThrows
    @Test
    void testDeleteStudent(){
        mockMvc.perform(delete(path+"/{id}", student.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
