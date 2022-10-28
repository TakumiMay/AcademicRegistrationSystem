package com.university.academicRegistrationSystem.service;

import com.university.academicRegistrationSystem.model.domain.Student;
import com.university.academicRegistrationSystem.model.dto.StudentDto;
import com.university.academicRegistrationSystem.model.mapper.StudentMapper;
import com.university.academicRegistrationSystem.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void shouldAddStudent() {
        Student student = new Student(1L, "firstName", "lastName", "program", 4.0);

        when(studentRepository.save(student)).thenReturn(student);

        StudentDto savedStudent = studentService.addStudent(StudentMapper.toDto(student));

        assertThat(savedStudent).isNotNull();
        assertThat(savedStudent.getId()).isEqualTo(1L);
        assertThat(savedStudent.getFirstName()).isEqualTo("firstName");
    }

    @Test
    public void shouldNotAddStudent() {
        Student student = new Student();

        when(studentRepository.save(student)).thenReturn(student);

        StudentDto savedStudent = studentService.addStudent(StudentMapper.toDto(student));

        assertThat(savedStudent.getId()).isNull();
        assertThat(savedStudent.getFirstName()).isNull();
    }

    @Test
    public void shouldGetAllStudents() {
        StudentDto student1 = new StudentDto(1L, "firstName", "lastName", "program", 4.0);
        StudentDto student2 = new StudentDto(2L, "firstName2", "lastName2", "program2", 4.5);
        StudentDto student3 = new StudentDto(3L, "firstName3", "lastName3", "program", 4.0);
        List<StudentDto> studentDtoList = List.of(student1, student2, student3);

        when(studentRepository.findAll()).thenReturn(studentDtoList.stream().map(StudentMapper::toBO).collect(Collectors.toList()));

        List<StudentDto> savedStudents = studentService.getAll();

        assertThat(studentDtoList).isNotNull();
        assertThat(studentDtoList.size()).isEqualTo(3);
        assertThat(studentDtoList.get(0).getFirstName()).isEqualTo("firstName");
        assertThat(studentDtoList.get(1).getFirstName()).isEqualTo("firstName2");
        assertThat(studentDtoList.get(2).getFirstName()).isEqualTo("firstName3");
    }

    @Test
    public void shouldNotGetAllStudents() {
        List<Student> studentDtoList = new ArrayList<>();

        when(studentRepository.findAll()).thenReturn(studentDtoList);

        List<StudentDto> savedStudents = studentService.getAll();

        assertThat(studentDtoList).isNotNull();
        assertThat(studentDtoList.size()).isEqualTo(0);
    }

    @Test
    public void shouldGetStudentById() {
        Student student = new Student(1L, "firstName", "lastName", "program", 4.0);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<StudentDto> response = studentService.getStudentById(1L);

        assertThat(response).isPresent();
        assertThat(response.get().getFirstName()).isEqualTo("firstName");
    }

    @Test
    public void shouldNotGetStudentById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<StudentDto> response = studentService.getStudentById(1L);

        assertThat(response).isEmpty();
    }

    @Test
    public void shouldEditStudent() {
        Student student = new Student(1L, "firstName", "lastName", "program", 4.0);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);

        Optional<StudentDto> optionalStudentDto = studentService.editStudent(1L, StudentMapper.toDto(student));

        assertThat(optionalStudentDto).isPresent();
        assertThat(optionalStudentDto.get().getLastName()).isEqualTo("lastName");
    }

    @Test
    public void shouldNotEditStudent() {
        Student student = new Student(1L, "firstName", "lastName", "program", 4.0);

        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<StudentDto> optionalStudentDto = studentService.editStudent(1L, StudentMapper.toDto(student));

        assertThat(optionalStudentDto).isEmpty();
    }

    @Test
    public void shouldDeleteStudent() {
        Student student = new Student(1L, "firstName", "lastName", "program", 4.0);

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).delete(student);
    }

    @Test
    public void shouldNotDeleteStudent() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            studentService.deleteStudent(1L);
        }).isInstanceOf(RuntimeException.class)
                .hasMessageContaining("The student to delete by id does not exist");
    }

}
