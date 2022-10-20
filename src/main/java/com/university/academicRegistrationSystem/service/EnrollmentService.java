package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.dto.StudentDto;

import java.util.List;
import java.util.Optional;

public interface EnrollmentService {
    boolean enrollStudent(Long stuId, Long subId);
    Optional<List<StudentDto>> getAllStudentsBySubject(Long subId);
}
