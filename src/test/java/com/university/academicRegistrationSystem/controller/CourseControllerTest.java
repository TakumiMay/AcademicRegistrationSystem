package com.university.academicRegistrationSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.academicRegistrationSystem.model.dto.CourseDto;
import com.university.academicRegistrationSystem.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    private static final String BASE_PATH = "/courses";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CourseService courseService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateCourse() throws Exception {
        CourseDto course = new CourseDto(1L, "courseName", List.of("program1", "program2"));

        when(courseService.addCourse(course)).thenReturn(course);

        mockMvc.perform(post(BASE_PATH + "/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(course)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

}
