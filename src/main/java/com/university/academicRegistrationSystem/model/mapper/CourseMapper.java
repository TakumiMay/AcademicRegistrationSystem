package com.university.academicRegistrationSystem.model.mapper;

import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.dto.CourseDto;

import java.util.stream.Collectors;

public class CourseMapper {

    public static CourseDto toDto(Course course) {
        CourseDto courseDto = new CourseDto(
                course.getId(), course.getCourseName(), course.getPrograms());
        if(course.getSubjects() != null)
            courseDto.setSubjects(course.getSubjects().stream().map(SubjectMapper::toDto).collect(Collectors.toList()));
        return courseDto;
    }

    public static Course toBO(CourseDto courseDto) {
        Course course = new Course(
                courseDto.getId(), courseDto.getCourseName(), courseDto.getPrograms());
        if(courseDto.getSubjects() != null)
            course.setSubjects(courseDto.getSubjects().stream().map(SubjectMapper::toBO).collect(Collectors.toList()));
        return course;
    }

}
