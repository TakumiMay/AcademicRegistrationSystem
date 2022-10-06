package com.university.academicRegistrationSystem.repository;

import com.university.academicRegistrationSystem.model.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
