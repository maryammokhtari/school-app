package com.example.school.service.mapper;

import com.example.school.repository.entity.Student;
import com.example.school.service.dto.StudentRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(target = "courses", ignore = true)
    @Mapping(target = "id", ignore = true)
    Student studentRequestToStudent(StudentRequest studentRequest);

    @Mapping(target = "id",source = "id")
    @Mapping(target = "courses", ignore = true)
    Student studentUpdateRequestToStudent(StudentRequest studentRequest,Long id);
}
