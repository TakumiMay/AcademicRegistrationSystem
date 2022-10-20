package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    StudentDto addStudent(StudentDto studentDto);
    List<StudentDto> getAll();
    Optional<StudentDto> getStudentById(Long stuId);
    Optional<StudentDto> editStudent(Long stuId, StudentDto studentDto);
    boolean deleteStudent(Long stuId);
}
