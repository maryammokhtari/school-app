package com.example.school.service;

import com.example.school.repository.StudentRepository;
import com.example.school.repository.entity.Student;
import com.example.school.service.dto.StudentRequest;
import com.example.school.service.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

//    @Autowired --> instead of this we use lombok @RequiredArgsConstructor
//    public StudentService(StudentRepository studentRepository){
//        this.studentRepository= studentRepository;
//    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    public Student create(StudentRequest studentRequest) {
        Student student= studentMapper.studentRequestToStudent(studentRequest);
        return studentRepository.save(student);
    }

    @SneakyThrows
    public Student update(Long id , StudentRequest studentRequest) {
        if(!studentRepository.existsById(id))
            throw new Exception("student doesn't exist");
        Student student=studentMapper.studentUpdateRequestToStudent(studentRequest,id);
        return studentRepository.save(student);
    }
    @SneakyThrows
    public void delete(Long id) {
        if(!studentRepository.existsById(id))
            throw new Exception("student doesn't exist");
        studentRepository.deleteById(id);
    }

}
