package com.example.school.service.mapper;

import com.example.school.repository.entity.Teacher;
import com.example.school.service.dto.TeacherRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher teacherRequestToTeacher(TeacherRequest teacherRequest);
    @Mapping(target="id",source = "id")
    Teacher teacherUpdateRequestToTeacher(TeacherRequest teacherRequest, Long id);
}
