package com.university.academicRegistrationSystem.model.mapper;

import com.university.academicRegistrationSystem.model.domain.Student;
import com.university.academicRegistrationSystem.model.dto.StudentDto;

import java.util.stream.Collectors;

public class StudentMapper {

    public static StudentDto toDto(Student student) {
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName(), student.getProgram(), student.getAverage());
    }

    public static Student toBO(StudentDto studentDto) {
        return new Student(studentDto.getId(), studentDto.getFirstName(), studentDto.getLastName(), studentDto.getProgram(), studentDto.getAverage());
    }

}
