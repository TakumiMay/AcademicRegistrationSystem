package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.domain.Student;
import com.university.academicRegistrationSystem.model.dto.StudentDto;
import com.university.academicRegistrationSystem.model.mapper.StudentMapper;
import com.university.academicRegistrationSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDto addStudent(StudentDto studentDto) {
        Student student = StudentMapper.toBO(studentDto);
        student.getSubjects().forEach(subject -> subject.getStudents().add(student));
        return StudentMapper.toDto(studentRepository.save(student));
    }

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll().stream().map(StudentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDto> getStudentById(Long stuId) {
        return studentRepository.findById(stuId).map(StudentMapper::toDto);
    }

    @Override
    public Optional<StudentDto> editStudent(Long stuId) {
        //TO DO
        return null;
    }

    @Override
    public Optional<StudentDto> deleteStudent(Long stuId) {
        //TO DO
        return null;
    }

}
