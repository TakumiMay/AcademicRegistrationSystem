package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto addStudent(StudentDto studentDto);
    List<StudentDto> getAll();
    StudentDto getStudentById(Long id);
}
