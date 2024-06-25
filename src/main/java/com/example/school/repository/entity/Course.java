package com.example.school.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Data//baraye ine ke lombok khodesh getter,setter dorost kone va ma dige nanevisim
@Entity// برای اینه که بگیم موارد زیر رو در دیتابیس به صورت یه جدول دربیاره
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //generatedvalueبرای اینه که ایدیها به صورت خودکار و یه دونه یه دونه اضافه بشن
    private Long id;
    @NotBlank //یعنی ورودی اسم ما خالی نباشه
    private String name;
    @Min(5)//یعنی ظرفیت ما عددش از 5 کمتر نباشه
    private Integer capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(mappedBy ="courses")
    private Set<Student> students;
}
//many to many: student with course
