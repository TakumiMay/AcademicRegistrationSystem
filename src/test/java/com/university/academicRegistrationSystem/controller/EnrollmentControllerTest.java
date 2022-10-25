package com.university.academicRegistrationSystem.controller;

import com.university.academicRegistrationSystem.model.dto.StudentDto;
import com.university.academicRegistrationSystem.service.EnrollmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnrollmentController.class)
public class EnrollmentControllerTest {

    private static final String BASE_PATH = "/registration";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EnrollmentService enrollmentService;

    @Test
    public void shouldEnrollStudent() throws Exception {
        when(enrollmentService.enrollStudent(1L, 1L)).thenReturn(true);

        mockMvc.perform(post(BASE_PATH + "/student-to-subject/{subId}/{stuId}", 1L, 1L))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void shouldNotEnrollStudent() throws Exception {
        when(enrollmentService.enrollStudent(1L, 1L)).thenReturn(false);

        mockMvc.perform(post(BASE_PATH + "/student-to-subject/{subId}/{stuId}", 1L, 1L))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void shouldGetStudentsBySubject() throws Exception {
        StudentDto student = new StudentDto(1L, "firstName", "lastName", "program", 4.0);
        StudentDto student2 = new StudentDto(2L, "firstName2", "lastName2", "program2", 4.5);
        StudentDto student3 = new StudentDto(3L, "firstName3", "lastName3", "program3", 4.2);
        List<StudentDto> studentDtoList = List.of(student, student2, student3);

        when(enrollmentService.getAllStudentsBySubject(1L)).thenReturn(Optional.of(studentDtoList));

        mockMvc.perform(get(BASE_PATH + "/students-in-subject/{subId}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.size()").value(studentDtoList.size()))
                .andDo(print());
    }

    @Test
    public void shouldNotGetStudentsBySubject() throws Exception {
        when(enrollmentService.getAllStudentsBySubject(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_PATH + "/students-in-subject/{subId}", 1L))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
