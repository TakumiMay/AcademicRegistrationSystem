package com.university.academicRegistrationSystem.model.mapper;

import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.dto.CourseDto;

public class CourseMapper {

    public static CourseDto toDto(Course course) {
        CourseDto courseDto = new CourseDto(course.getId(), course.getCourseName(), course.getPrograms());
        if (!course.getSubjects().isEmpty()) {
            course.getSubjects().forEach(subject ->
                courseDto.getSubjects().add( SubjectMapper.toDto(subject) )
            );
        }
        return courseDto;
    }

    public static Course toBO(CourseDto courseDto) {
        Course course = new Course(courseDto.getId(), courseDto.getCourseName(), courseDto.getPrograms());
        if (!courseDto.getSubjects().isEmpty()) {
            courseDto.getSubjects().forEach(subjectDto ->
                    course.getSubjects().add( SubjectMapper.toBO(subjectDto) )
            );
        }
        return course;
    }

}
