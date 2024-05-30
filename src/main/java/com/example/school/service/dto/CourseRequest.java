package com.example.school.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data//baraye ine ke lombok khodesh getter,setter dorost kone va ma dige nanevisim
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {

    @NotBlank //یعنی ورودی اسم ما خالی نباشه
    private String name;
    @Min(5)//یعنی ظرفیت ما عددش از 5 کمتر نباشه
    private Integer capacity;

}
