package com.university.academicRegistrationSystem.repository;

import com.university.academicRegistrationSystem.model.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}