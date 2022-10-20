package com.university.academicRegistrationSystem.model.mapper;

import com.university.academicRegistrationSystem.model.domain.Subject;
import com.university.academicRegistrationSystem.model.dto.SubjectDto;

public class SubjectMapper {

    public static SubjectDto toDto(Subject subject) {
        return new SubjectDto(subject.getId(), subject.getName(), subject.getSchedule(), subject.getProfessor(), subject.getCredits());
    }

    public static Subject toBO(SubjectDto subjectDto) {
        return new Subject(subjectDto.getId(), subjectDto.getName(), subjectDto.getSchedule(), subjectDto.getProfessor(), subjectDto.getCredits());
    }

}
