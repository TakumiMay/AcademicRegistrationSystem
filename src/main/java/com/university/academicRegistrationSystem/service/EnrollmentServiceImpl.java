package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.domain.Student;
import com.university.academicRegistrationSystem.model.domain.Subject;
import com.university.academicRegistrationSystem.model.dto.StudentDto;
import com.university.academicRegistrationSystem.model.mapper.StudentMapper;
import com.university.academicRegistrationSystem.repository.StudentRepository;
import com.university.academicRegistrationSystem.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public boolean enrollStudent(Long stuId, Long subId) {
        Optional<Student> student = studentRepository.findById(stuId);
        Optional<Subject> subject = subjectRepository.findById(subId);
        if(student.isPresent() && subject.isPresent()) {
            student.get().getSubjects().add(subject.get());
            subject.get().getStudents().add(student.get());
            studentRepository.save(student.get());
            subjectRepository.save(subject.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<List<StudentDto>> getAllStudentsBySubject(Long subId) {
        Optional<Subject> subject = subjectRepository.findById(subId);
        return subject.map(sub -> sub.getStudents().stream().map(StudentMapper::toDto).collect(Collectors.toList()));
    }

}
