package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.dto.SubjectDto;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Optional<SubjectDto> addSubject(Long id, SubjectDto subjectDto);
    List<SubjectDto> getAllByCourse(Long id);
    Optional<SubjectDto> getSubjectById(Long id, Long subId);
    Optional<SubjectDto> editSubject(Long id, SubjectDto subjectDto);
    boolean deleteSubject(Long id, Long subId);
}
