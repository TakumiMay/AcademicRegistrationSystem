package com.university.academicRegistrationSystem.repository;

import com.university.academicRegistrationSystem.model.domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;
    private Student student;

    @BeforeEach
    public void setUp() {
        student = new Student(
                null, "firstName", "lastName", "program1", 1.0);
    }

    @Test
    @DisplayName("JUnit test for save Student operation")
    public void givenStudent_whenSave_thenReturnSavedStudent() {
        //Given - precondition or set up

        //When - action or the behaviour that we are going to test
        Student savedStudent = studentRepository.save(student);

        //Then - verify the output
        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("JUnit test for find all Students operation")
    public void givenStudents_whenFindAll_thenReturnAllStudents(){
        Student student2 = new Student(null, "firstName2", "lastName2", "program2", 2.0);
        studentRepository.save(student);
        studentRepository.save(student2);

        List<Student> students = studentRepository.findAll();

        assertThat(students).isNotNull();
        assertThat(students.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("JUnit test for find a Student operation")
    public void givenStudent_whenFindById_thenReturnStudent(){
        studentRepository.save(student);

        Student studentDB = studentRepository.findById(1L).get();

        assertThat(studentDB).isNotNull();
    }

    @Test
    @DisplayName("JUnit test for edit a Student operation")
    public void givenStudent_whenEdit_thenReturnUpdatedStudent() {
        studentRepository.save(student);

        Student savedStudent = studentRepository.findById(student.getId()).get();
        savedStudent.setFirstName("anotherName");
        Student editedStudent = studentRepository.save(savedStudent);

        assertThat(editedStudent.getFirstName()).isEqualTo("anotherName");
        assertThat(editedStudent.getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("JUnit test for delete a Student operation")
    public void givenStudent_whenDelete_thenRemoveStudent(){
        studentRepository.save(student);

        studentRepository.delete(student);
        Optional<Student> optionalStudent = studentRepository.findById(student.getId());

        assertThat(optionalStudent).isEmpty();
    }

}
