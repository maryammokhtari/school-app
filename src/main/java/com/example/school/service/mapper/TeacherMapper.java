package com.example.school.service.mapper;

import com.example.school.repository.entity.Teacher;
import com.example.school.service.dto.TeacherRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "id", ignore = true)
    Teacher teacherRequestToTeacher(TeacherRequest teacherRequest);
    @Mapping(target="id",source = "id")
    @Mapping(target = "courses", ignore = true)
    Teacher teacherUpdateRequestToTeacher(TeacherRequest teacherRequest, Long id);
}
