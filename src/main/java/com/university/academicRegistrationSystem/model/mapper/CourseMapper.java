package com.university.academicRegistrationSystem.model.mapper;

import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.dto.CourseDto;

import java.util.stream.Collectors;

public class CourseMapper {

    public static CourseDto toDto(Course course) {
        return new CourseDto(course.getId(), course.getCourseName(), course.getPrograms());
    }

    public static Course toBO(CourseDto courseDto) {
        return new Course(courseDto.getId(), courseDto.getCourseName(), courseDto.getPrograms());
    }

}
