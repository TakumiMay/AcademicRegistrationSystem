package com.university.academicRegistrationSystem.controller;

import com.university.academicRegistrationSystem.exceptionHandler.MyExceptionHandler;
import com.university.academicRegistrationSystem.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(classes = {StudentController.class, MyExceptionHandler.class})
@ActiveProfiles("integration-test")
@AutoConfigureMockMvc(addFilters = false)
public class StudentControllerTest {

    private static final String BASE_PATH = "/students";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MyExceptionHandler exceptionHandler;
    @Autowired
    private StudentController studentController;
    @MockBean
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).setControllerAdvice(exceptionHandler)
                .build();
    }


}
