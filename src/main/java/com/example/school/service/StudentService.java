package com.example.school.service;

import com.example.school.exception.ResourceNotFoundException;
import com.example.school.repository.StudentRepository;
import com.example.school.repository.entity.Student;
import com.example.school.service.dto.StudentRequest;
import com.example.school.service.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private static final String STUDENT_DOESN_T_EXIST = "Student doesn't exist";
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent())
            return student.get();
        throw new ResourceNotFoundException(STUDENT_DOESN_T_EXIST);
    }

    public Student create(StudentRequest studentRequest) {
        Student student = studentMapper.studentRequestToStudent(studentRequest);
        return studentRepository.save(student);
    }

    public Student update(Long id, StudentRequest studentRequest) {
        if (!studentRepository.existsById(id))
            throw new ResourceNotFoundException(STUDENT_DOESN_T_EXIST);
        Student student = studentMapper.studentUpdateRequestToStudent(studentRequest, id);
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        if (!studentRepository.existsById(id))
            throw new ResourceNotFoundException(STUDENT_DOESN_T_EXIST);
        studentRepository.deleteById(id);
    }

}
