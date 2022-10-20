package com.university.academicRegistrationSystem.service;


import com.university.academicRegistrationSystem.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SubjectServiceTests {

    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private SubjectService subjectService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void givenSubject_whenSave_thenReturnSubject() {

    }

}
