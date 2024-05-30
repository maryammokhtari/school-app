package com.example.school.service;

import com.example.school.repository.entity.Teacher;
import com.example.school.service.dto.TeacherRequest;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAll();

    Teacher findById(Long id);

    Teacher create(TeacherRequest teacherRequest);

    Teacher update(Long id, TeacherRequest teacherRequest);

    void delete(Long id);

}
