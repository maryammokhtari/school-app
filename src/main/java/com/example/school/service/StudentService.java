package com.example.school.service;

import com.example.school.repository.StudentRepository;
import com.example.school.repository.model.Student;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

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

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @SneakyThrows
    public Student update(Student student) {
        if(!studentRepository.existsById(student.getId()))
            throw new Exception("student doesn't exist");
        return studentRepository.save(student);
    }
    @SneakyThrows
    public void delete(Long id) {
        if(!studentRepository.existsById(id))
            throw new Exception("student doesn't exist");
        studentRepository.deleteById(id);
    }

}
