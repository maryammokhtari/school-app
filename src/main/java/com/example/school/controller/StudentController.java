package com.example.school.controller;

import com.example.school.repository.model.Student;
import com.example.school.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

//اینجا دیگه autowiredنکردیم چرا؟چون در بالا از انوتیشن@requiredArgconstructorاستفاده کردیمو دیگه به
//  autowiredنیاز نداریم و اون خودش کار رو انام میده در پشت صحنه
//    @Autowired
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getById(id));
    }
    @PostMapping
    public ResponseEntity<Student> create (@RequestBody @Valid Student student){
        return ResponseEntity.ok(studentService.create(student));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping
    public ResponseEntity<Student>update(@RequestBody @Valid Student student){
        return ResponseEntity.ok(studentService.update(student));
    }

}
