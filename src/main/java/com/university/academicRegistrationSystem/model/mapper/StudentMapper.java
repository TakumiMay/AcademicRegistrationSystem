package com.university.academicRegistrationSystem.model.mapper;

import com.university.academicRegistrationSystem.model.domain.Student;
import com.university.academicRegistrationSystem.model.dto.StudentDto;

public class StudentMapper {

    public static StudentDto toDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        return studentDto;
    }

}
