package com.example.school.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Min(100)
    private Double salary;
}
