package com.example.school.controller;

import com.example.school.repository.entity.Course;
import com.example.school.service.CourseService;
import com.example.school.service.dto.CourseRequest;
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

   CourseRequest courseRequest;
   Course course;
   Long requestId = 1L;
    String path = "/api/v1/courses";

    @BeforeEach
    void setUp() {
        courseRequest = new CourseRequest("math", 10);
        course = new Course(1L,"math", 10);
    }


    @SneakyThrows
    @Test
    public void testGetAll() {
        List<Course>courses=new ArrayList<>();
        Course course1=new Course(1L,"math", 10);
        Course course2=new Course(2L,"chemistery", 23);
        courses.add(course1);
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
        when(courseService.findById(requestId)).thenReturn(course);
        mockMvc.perform(get(path + "/{id}", course.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(course.getId()));

    }

    @SneakyThrows
    @Test
    void testCreateCourse() {

        when(courseService.create(courseRequest)).thenReturn(course);

        mockMvc.perform(
                        post(path)
                                .content(new ObjectMapper().writeValueAsString(courseRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(course.getId()));

    }
    @SneakyThrows
    @Test
    void testUpdateCourse(){
        when(courseService.update(1L,courseRequest)).thenReturn(course);

        mockMvc.perform(
                 put(path+"/1")
                .content(new ObjectMapper().writeValueAsString(courseRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(course.getId()))
                .andExpect(jsonPath("$.name").value(course.getName()))
                .andExpect(jsonPath("$.capacity").value(course.getCapacity()));

    }
    @SneakyThrows
    @Test
    void testDeleteCourse(){
        mockMvc.perform(delete(path+"/{id}",course.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
