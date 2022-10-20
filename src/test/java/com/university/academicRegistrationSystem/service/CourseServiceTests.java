package com.university.academicRegistrationSystem.service;


import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {

    @Mock
    private CourseRepository courseRepository;
    @InjectMocks
    private CourseService courseService;

    private Course course;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void givenCourse_whenSave_thenReturnCourse(){

    }

}
