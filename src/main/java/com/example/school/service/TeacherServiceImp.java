package com.example.school.service;

import com.example.school.repository.TeacherRepository;
import com.example.school.repository.model.Teacher;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImp implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    @SneakyThrows
    public Teacher create(Teacher teacher) {
        if(!teacherRepository.existsById(teacher.getId()))
            throw new Exception("teacher with this id is not found");
        return teacherRepository.save(teacher);
    }

    @Override
    @SneakyThrows
    public Teacher update(Teacher teacher) {
        if(!teacherRepository.existsById(teacher.getId()))
            throw new Exception("teacher with this id is not found");
        return teacherRepository.save(teacher);
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        if (!teacherRepository.existsById(id))
            throw new Exception("teacher with this id is not found");
        teacherRepository.deleteById(id);

    }
}
