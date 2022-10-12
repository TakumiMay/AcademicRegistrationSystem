package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    SubjectDto addSubject(SubjectDto subjectDto);
    List<SubjectDto> getAllByCourse(Long id);
}
