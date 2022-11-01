package com.university.academicRegistrationSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.dto.SubjectDto;
import com.university.academicRegistrationSystem.repository.CourseRepository;
import com.university.academicRegistrationSystem.repository.SubjectRepository;
import com.university.academicRegistrationSystem.service.SubjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubjectController.class)
public class SubjectControllerTest {

    private static final String BASE_PATH = "/courses/{id}/subjects";

    @Autowired
    private MockMvc mockMvc;
    @SpyBean
    private SubjectServiceImpl subjectService;
    @MockBean
    private SubjectRepository subjectRepository;
    @MockBean
    private CourseRepository courseRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {

    }

    /**@Test
    public void shouldAddSubject() throws Exception {
        SubjectDto subject = new SubjectDto(1L, "subject1", "LUN - MIE 9:00AM", "professor1", 4);
        Course course = new Course(1L, "courseName", List.of("program1", "program2"));

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(subjectService.addSubject(1L, subject)).thenReturn(Optional.of(subject));

        mockMvc.perform(post(BASE_PATH + "/create", 1L).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(subject)))
                .andExpect(status().isCreated())
                .andDo(print());
    }*/


}
