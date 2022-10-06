package com.university.academicRegistrationSystem.model.mapper;

import com.university.academicRegistrationSystem.model.domain.Subject;
import com.university.academicRegistrationSystem.model.dto.SubjectDto;

public class SubjectMapper {

    public static SubjectDto toDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setSchedule(subject.getSchedule());
        subjectDto.setCredits(subject.getCredits());
        subjectDto.setCourse(subject.getCourse().getCourseName());
        return subjectDto;
    }

}
