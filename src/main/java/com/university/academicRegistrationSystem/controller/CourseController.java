package com.university.academicRegistrationSystem.controller;

import com.university.academicRegistrationSystem.model.domain.Course;
import com.university.academicRegistrationSystem.model.dto.CourseDto;
import com.university.academicRegistrationSystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public ResponseEntity<List<CourseDto>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

}
