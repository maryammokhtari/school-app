package com.example.school.controller;

import com.example.school.repository.model.Course;
import com.example.school.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {
    @MockBean
    CourseService courseService;
    @Autowired
    MockMvc mockMvc;

    Course course;
    String path = "/api/v1/courses";

    @BeforeEach
    void setUp() {
        course = new Course(1L, "math", 10);
    }

    @SneakyThrows
    @Test
    public void testGetAll() {

        Course course2 = new Course(2l, "chemistery", 23);
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course2);

        when(courseService.getAll()).thenReturn(courses);

        mockMvc.perform(get(path))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].name", Matchers.is("math")));
    }

    @SneakyThrows
    @Test
    void testGetById() {
        when(courseService.findById(course.getId())).thenReturn(course);
        mockMvc.perform(get(path + "/{id}", course.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(course.getId()));

    }

    @SneakyThrows
    @Test
    void testCreateCourse() {
        when(courseService.create(course)).thenReturn(course);

        mockMvc.perform(
                        post(path)
                                .content(new ObjectMapper().writeValueAsString(course))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(course.getId()));

    }
    @SneakyThrows
    @Test
    void testUpdateCourse(){
        Course course2 = new Course(1l, "chemistery", 23);
        when(courseService.update(course2)).thenReturn(course2);

        mockMvc.perform(
                 put(path)
                .content(new ObjectMapper().writeValueAsString(course2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(course2.getId()))
                .andExpect(jsonPath("$.name").value(course2.getName()))
                .andExpect(jsonPath("$.capacity").value(course2.getCapacity()));

    }
    @SneakyThrows
    @Test
    void testDeleteCourse(){
        mockMvc.perform(delete(path+"/{id}",course.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
