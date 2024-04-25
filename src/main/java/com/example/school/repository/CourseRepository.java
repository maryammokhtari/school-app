package com.example.school.repository;

import com.example.school.repository.model.Course;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {}
//در jparepo مولفه ی اول میگه که یعنی با چهentity میخوایی کار کنی و مولفه ی دوم میگه که جنس ایدیش چی هست
//    private List<Course> courses = new ArrayList<>();
//
//    public List<Course> getAll() {
//        return courses;
//    }
//
//    public Course getById(Long id) {
//        Course course = courses.stream()
//                .filter(c -> c.getId() == id)
//                .findFirst()
//                .orElseThrow();
//        return course;
//    }
//
//    public Course create(Course course) {
//        courses.add(course);
//        return course;
//    }
//
//    public Course update(Course course) {
//        Course updatedCourse = courses.stream()
//                .filter(c -> c.getId() == course.getId())
//                .findFirst()
//                .orElseThrow();
//
//        updatedCourse.setCapacity(course.getCapacity());
//        updatedCourse.setName(course.getName());
//        //TODO how to update list
//        return updatedCourse;
//    }
//
//    public void delete(Long id) {
//        courses.removeIf(course -> course.getId() == id);
//    }
//}
