package com.university.academicRegistrationSystem.model.mapper;

import com.university.academicRegistrationSystem.model.domain.Subject;
import com.university.academicRegistrationSystem.model.dto.SubjectDto;

import java.util.stream.Collectors;

public class SubjectMapper {

    public static SubjectDto toDto(Subject subject) {
        SubjectDto subjectDto;
        subjectDto = new SubjectDto(
                subject.getId(), subject.getName(), subject.getSchedule(), subject.getProfessor(), subject.getCredits(), subject.getCourse().getId());
        return subjectDto;
    }

    public static Subject toBO(SubjectDto subjectDto) {
        Subject subject = new Subject(
                subjectDto.getId(), subjectDto.getName(), subjectDto.getSchedule(), subjectDto.getProfessor(), subjectDto.getCredits());
        if(subjectDto.getStudents() != null)
            subject.setStudents(subjectDto.getStudents().stream().map(StudentMapper::toBO).collect(Collectors.toList()));
        return subject;
    }

}
