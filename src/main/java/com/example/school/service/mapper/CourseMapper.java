package com.example.school.service.mapper;

import com.example.school.repository.entity.Course;
import com.example.school.service.dto.CourseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "students", ignore = true)
    Course courseRequestToCourse(CourseRequest courseRequest);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    Course courseUpdateRequestToCourse(CourseRequest courseRequest, Long id);

}
