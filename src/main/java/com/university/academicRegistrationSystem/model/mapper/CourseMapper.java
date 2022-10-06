package com.university.academicRegistrationSystem.model.mapper;

import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.dto.CourseDto;

public class CourseMapper {

    public static CourseDto toDto(Course course) {
        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setPrograms(course.getPrograms());
        return courseDto;
    }

}
