package com.example.school.service.mapper;

import com.example.school.repository.entity.Student;
import com.example.school.service.dto.StudentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student studentRequestToStudent(StudentRequest studentRequest);

    @Mapping(target = "id",source = "id")
    Student studentUpdateRequestToStudent(StudentRequest studentRequest,Long id);
}
