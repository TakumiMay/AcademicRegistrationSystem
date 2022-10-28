package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.dto.CourseDto;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    CourseDto addCourse(CourseDto courseDto);
    List<CourseDto> getAll();
    Optional<CourseDto> getCourseById(Long id);
    Optional<CourseDto> editCourse(Long id, CourseDto courseDto);
    void deleteCourse(Long id);
}
