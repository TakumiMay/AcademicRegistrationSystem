package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.domain.Student;
import com.university.academicRegistrationSystem.model.dto.StudentDto;
import com.university.academicRegistrationSystem.model.mapper.StudentMapper;
import com.university.academicRegistrationSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream().map(StudentMapper::toDto).collect(Collectors.toList());
    }

}
