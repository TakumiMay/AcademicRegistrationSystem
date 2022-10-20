package com.university.academicRegistrationSystem.controller;

import com.university.academicRegistrationSystem.exceptionHandler.MyExceptionHandler;
import com.university.academicRegistrationSystem.service.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest(classes = {SubjectController.class, MyExceptionHandler.class})
@ActiveProfiles("integration-test")
@AutoConfigureMockMvc(addFilters = false)
public class SubjectControllerTest {

    private static final String BASE_PATH = "/courses/{id}/subjects";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MyExceptionHandler exceptionHandler;
    @Autowired
    private SubjectController subjectController;
    @MockBean
    private SubjectService subjectService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).setControllerAdvice(exceptionHandler)
                .build();
    }


}
