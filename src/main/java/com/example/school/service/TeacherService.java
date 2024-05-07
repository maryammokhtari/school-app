package com.example.school.service;

import com.example.school.repository.model.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAll();
    Teacher findById(Long id);
    Teacher create(Teacher teacher);
    Teacher update(Teacher teacher);
    void delete(Long id);

}
