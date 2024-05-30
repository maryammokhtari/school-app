package com.example.school.service.mapper;

import com.example.school.repository.entity.Course;
import com.example.school.service.dto.CourseRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course courseRequestToCourse(CourseRequest courseRequest);

    @Mapping(target = "id", source = "id")
    Course courseUpdateRequestToCourse(CourseRequest courseRequest, Long id);

}
