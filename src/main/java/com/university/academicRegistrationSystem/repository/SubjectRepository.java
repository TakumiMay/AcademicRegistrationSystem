package com.university.academicRegistrationSystem.repository;

import com.university.academicRegistrationSystem.model.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query()
    List<Subject> findByCourseId(Long id);
}