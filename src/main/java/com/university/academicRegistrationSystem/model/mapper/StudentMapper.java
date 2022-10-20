package com.university.academicRegistrationSystem.model.mapper;

import com.university.academicRegistrationSystem.model.domain.Student;
import com.university.academicRegistrationSystem.model.dto.StudentDto;

public class StudentMapper {

    public static StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto(student.getId(), student.getFirstName(),
                student.getLastName(), student.getProgram(), student.getAverage());
        if (!student.getSubjects().isEmpty()) {
            student.getSubjects().forEach(subject ->
                    studentDto.getSubjects().add( SubjectMapper.toDto(subject) )
            );
        }
        return studentDto;
    }

    public static Student toBO(StudentDto studentDto) {
        Student student = new Student(studentDto.getId(), studentDto.getFirstName(),
                studentDto.getLastName(), studentDto.getProgram(), studentDto.getAverage());
        if (!studentDto.getSubjects().isEmpty()) {
            studentDto.getSubjects().forEach(subjectDto ->
                    student.getSubjects().add( SubjectMapper.toBO(subjectDto) )
            );
        }
        return student;
    }

}
