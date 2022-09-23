package com.university.academicRegistrationSystem.repositories;

import com.university.academicRegistrationSystem.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
