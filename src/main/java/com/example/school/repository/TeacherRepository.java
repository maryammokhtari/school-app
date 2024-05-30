package com.example.school.repository;

import com.example.school.repository.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {}
//    private List<Teacher> teachers = new ArrayList<>();
//
//    public List<Teacher> getAll() {
//        return teachers;
//    }
//
//    public Teacher getByID(Long id) {
//        Teacher teacher = teachers.stream()
//                .filter(teacher1 -> teacher1.getId() == id)
//                .findFirst()
//                .orElseThrow();
//        return teacher;
//    }
//
//    public Teacher create(Teacher teacher) {
//        teachers.add(teacher);
//        return teacher;
//    }
//
//    public Teacher update(Teacher teacher) {
//        Teacher updatedTeacher = teachers.stream()
//                .filter(teacher1 -> teacher1.getId() == teacher.getId())
//                .findFirst()
//                .orElseThrow();
//        //todo how to update list
//        return updatedTeacher;
//    }
//
//    public void delete(Long id) {
//        teachers.removeIf(teacher -> teacher.getId() == id);
//
//    }
//
//}
