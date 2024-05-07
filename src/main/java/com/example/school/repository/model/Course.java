package com.example.school.repository.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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

}
