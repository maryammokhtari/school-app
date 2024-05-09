package com.example.school.controller;

import com.example.school.repository.model.Teacher;
import com.example.school.service.TeacherService;
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

@WebMvcTest(TeacherController.class)
public class TeacherControllerTest {

    @MockBean
    TeacherService teacherService;
    @Autowired
    MockMvc mockMvc;
    Teacher teacher;
    String path = "/api/v1/teachers";

    @BeforeEach
    void setUp() {
        teacher = new Teacher(1l, "maryam", "mokhtari", 500.0);
    }

    @SneakyThrows
    @Test
    public void testGetAll() {
        Teacher teacher1 = new Teacher(2l, "hami", "rahmani", 550.0);
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);
        teachers.add(teacher1);
        when(teacherService.getAll()).thenReturn(teachers);
        mockMvc.perform(get(path))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("maryam")));
    }

    @SneakyThrows
    @Test
    void testGetById() {
        when(teacherService.findById(teacher.getId())).thenReturn(teacher);
        mockMvc.perform(get(path + "/{id}", teacher.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(teacher.getId()));
    }

    @SneakyThrows
    @Test
    void testCreateTEacher() {
        when(teacherService.create(teacher)).thenReturn(teacher);
        mockMvc.perform(post(path)
                        .content(new ObjectMapper().writeValueAsString(teacher))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(teacher.getId()));
    }

    @SneakyThrows
    @Test
    void testUpdateTeacher() {
        Teacher teacher1 = new Teacher(2l, "hami", "rahmani", 550.0);
        when(teacherService.update(teacher1)).thenReturn(teacher1);
        mockMvc.perform(put(path)
                        .content(new ObjectMapper().writeValueAsString(teacher1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(teacher1.getId()))
                .andExpect(jsonPath("$.firstName").value(teacher1.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(teacher1.getLastName()))
                .andExpect(jsonPath("$.salary").value(teacher1.getSalary()));


    }

    @SneakyThrows
    @Test
    void testDeleteTeacher() {
        mockMvc.perform(delete(path + "/{id}", teacher.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
