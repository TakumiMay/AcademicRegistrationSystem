package com.university.academicRegistrationSystem.service;


import com.university.academicRegistrationSystem.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void givenStudent_whenSave_thenReturnStudent(){

    }

}
