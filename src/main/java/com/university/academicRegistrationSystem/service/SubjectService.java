package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.dto.SubjectDto;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Optional<SubjectDto> addSubject(Long id, SubjectDto subjectDto);
    Optional<List<SubjectDto>> getAllByCourse(Long id);
    Optional<SubjectDto> getSubjectById(Long id, Long subId);
    Optional<SubjectDto> editSubject(Long id, Long subId, SubjectDto subjectDto);
    void deleteSubject(Long id, Long subId);
}
