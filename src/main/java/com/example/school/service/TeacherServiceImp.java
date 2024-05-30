package com.example.school.service;

import com.example.school.repository.TeacherRepository;
import com.example.school.repository.entity.Teacher;
import com.example.school.service.dto.TeacherRequest;
import com.example.school.service.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImp implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    public Teacher create(TeacherRequest teacherRequest) {
        Teacher teacher = teacherMapper.teacherRequestToTeacher(teacherRequest);
        return teacherRepository.save(teacher);
    }

    @Override
    @SneakyThrows
    public Teacher update(Long id, TeacherRequest teacherRequest) {
        if (!teacherRepository.existsById(id))
            throw new Exception("teacher with this id is not found");
        Teacher teacher = teacherMapper.teacherUpdateRequestToTeacher(teacherRequest, id);
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
