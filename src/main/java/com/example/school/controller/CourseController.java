package com.example.school.controller;

import com.example.school.repository.entity.Course;
import com.example.school.service.CourseService;
import com.example.school.service.dto.CourseRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;
    //finalمیاد میگه کهابجکت ما که از جنس کورس سرویسه یه بار که ساخته شد دیگه تغییر نمیکنه

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }
    //responseEntityبرای اینه که خروی مون رو استاندارد برگردونیم

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        //@pathVaribale یعنی در ادرس ورودی باید یه مولفه ی عددی از حنس لانگ باشه
        return ResponseEntity.ok(courseService.findById(id));
        //responseEntity.okیعنی  کد200 برگردون و در بدنه اش اونی که داخله پرانتزه رو بذار
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody @Valid CourseRequest courseRequest) {
        //@validبرای اینه که بیاد داده های ورودی رو ارزیابی کنه که مطابق با مدل های ما باشه
        //@requestbodyمیگه انتظار داشته باش که در بدنه ی درخواست ورودی اطلاعاتی ارسال شده باشه
        return ResponseEntity.ok(courseService.create(courseRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.ok().build();
        //.buildبرای اینه که جواب بدون بدنه اس و خطا نده
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody @Valid CourseRequest courseRequest) {
        Course updatedCourse = courseService.update(id, courseRequest);
        return ResponseEntity.ok(updatedCourse);
    }
}
