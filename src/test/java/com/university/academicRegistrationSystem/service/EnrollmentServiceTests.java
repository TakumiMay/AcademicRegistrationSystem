package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.domain.Student;
import com.university.academicRegistrationSystem.model.domain.Subject;
import com.university.academicRegistrationSystem.model.dto.StudentDto;
import com.university.academicRegistrationSystem.repository.StudentRepository;
import com.university.academicRegistrationSystem.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTests {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private EnrollmentServiceImpl enrollmentServiceImpl;

    @Test
    public void shouldEnrollStudent() {
        Subject subject = new Subject(1L, "subjectName", "schedule", "professor", 3);
        Student student = new Student(1L, "firstName", "lastName", "program", 4.0);

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        boolean response = enrollmentServiceImpl.enrollStudent(student.getId(), subject.getId());

        assertThat(response).isTrue();
    }

    @Test
    public void shouldNotEnrollStudent() {
        Student student = new Student(1L, "firstName", "lastName", "program", 4.0);

        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        boolean response = enrollmentServiceImpl.enrollStudent(student.getId(), 1L);

        assertThat(response).isFalse();
    }

    @Test
    public void shouldGetStudentsBySubject() {
        Subject subject = new Subject(1L, "subjectName", "schedule", "professor", 3);
        Student student = new Student(1L, "firstName", "lastName", "program", 4.0);
        subject.getStudents().add(student);

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        Optional<List<StudentDto>> optionalStudentDtoList = enrollmentServiceImpl.getAllStudentsBySubject(subject.getId());

        assertThat(optionalStudentDtoList).isPresent();
        assertThat(optionalStudentDtoList.get().size()).isEqualTo(1);
        assertThat(optionalStudentDtoList.get().get(0).getFirstName()).isEqualTo(student.getFirstName());
    }

    @Test
    public void shouldGetEmptyStudentsBySubject() {
        Subject subject = new Subject(1L, "subjectName", "schedule", "professor", 3);

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        Optional<List<StudentDto>> optionalStudentDtoList = enrollmentServiceImpl.getAllStudentsBySubject(1L);

        assertThat(optionalStudentDtoList).isPresent();
        assertThat(optionalStudentDtoList.get()).isEmpty();
        assertThat(optionalStudentDtoList.get().size()).isEqualTo(0);
    }

    @Test
    public void shouldNotGetStudentsBySubjectNotFound() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<List<StudentDto>> optionalStudentDtoList = enrollmentServiceImpl.getAllStudentsBySubject(1L);

        assertThat(optionalStudentDtoList).isEmpty();
    }

}
