package com.university.academicRegistrationSystem.repository;

import com.university.academicRegistrationSystem.model.domain.Student;
import com.university.academicRegistrationSystem.model.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByCourseId(Long id);
    List<Student> findStudentsById(Long subId);
}