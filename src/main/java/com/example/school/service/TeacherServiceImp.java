package com.example.school.service;

import com.example.school.exception.ResourceNotFoundException;
import com.example.school.repository.TeacherRepository;
import com.example.school.repository.entity.Teacher;
import com.example.school.service.dto.TeacherRequest;
import com.example.school.service.mapper.TeacherMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImp implements TeacherService {

    private static final String TEACHER_WITH_THIS_ID_IS_NOT_FOUND = "Teacher with this id is not found";
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent())
            return teacher.get();
        throw new ResourceNotFoundException(TEACHER_WITH_THIS_ID_IS_NOT_FOUND);
    }

    @Override
    public Teacher create(TeacherRequest teacherRequest) {
        Teacher teacher = teacherMapper.teacherRequestToTeacher(teacherRequest);
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(Long id, TeacherRequest teacherRequest) {
        if (!teacherRepository.existsById(id))
            throw new ResourceNotFoundException(TEACHER_WITH_THIS_ID_IS_NOT_FOUND);
        Teacher teacher = teacherMapper.teacherUpdateRequestToTeacher(teacherRequest, id);
        return teacherRepository.save(teacher);
    }

    @Override
    public void delete(Long id) {
        if (!teacherRepository.existsById(id))
            throw new ResourceNotFoundException(TEACHER_WITH_THIS_ID_IS_NOT_FOUND);
        teacherRepository.deleteById(id);

    }
}
