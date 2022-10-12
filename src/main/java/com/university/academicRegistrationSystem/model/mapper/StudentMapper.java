package com.university.academicRegistrationSystem.model.mapper;

import com.university.academicRegistrationSystem.model.domain.Student;
import com.university.academicRegistrationSystem.model.dto.StudentDto;

import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto(
                student.getId(), student.getFirstName(), student.getLastName(), student.getProgram(), student.getAverage());
        studentDto.setSubjects(student.getSubjects().stream().map(SubjectMapper::toDto).collect(Collectors.toList()));
        return studentDto;
    }

    public static Student toBO(StudentDto studentDto) {
        Student student = new Student(
                studentDto.getId(), studentDto.getFirstName(), studentDto.getLastName(), studentDto.getProgram(), studentDto.getAverage());
        student.setSubjects(studentDto.getSubjects().stream().map(SubjectMapper::toBO).collect(Collectors.toList()));
        return student;
    }

}
