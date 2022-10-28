package com.university.academicRegistrationSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.university.academicRegistrationSystem.model.dto.StudentDto;
import com.university.academicRegistrationSystem.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    private static final String BASE_PATH = "/students";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldAddStudent() throws Exception {
        StudentDto student = new StudentDto(1L, "firstName", "lastName", "program", 4.0);

        when(studentService.addStudent(student)).thenReturn(student);

        mockMvc.perform(post(BASE_PATH + "/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @ParameterizedTest
    @MethodSource("generateWrongInputs")
    public void shouldNotAddStudent() {
        //TO DO
        assertThat(true).isTrue();
    }

    private static Stream<Arguments> generateWrongInputs() {
        //TO DO
        return Stream.of(
                Arguments.of(new StudentDto(1L, "firstName", "lastName", "program", 4.0))
        );
    }


}
