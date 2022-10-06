package com.university.academicRegistrationSystem.controller;

import com.university.academicRegistrationSystem.model.domain.Student;
import com.university.academicRegistrationSystem.model.dto.StudentDto;
import com.university.academicRegistrationSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }
}
