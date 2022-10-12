package com.university.academicRegistrationSystem.controller;

import com.university.academicRegistrationSystem.model.dto.CourseDto;
import com.university.academicRegistrationSystem.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/create")
    @Operation(summary ="Add a new course")
    public ResponseEntity<CourseDto> addCourse(@Valid @RequestBody CourseDto courseDto) {
        ResponseEntity<CourseDto> response = new ResponseEntity<CourseDto>(courseService.addCourse(courseDto), HttpStatus.OK);
        return response;
    }

    @GetMapping("/all")
    @Operation(summary ="Obtain all the courses")
    public ResponseEntity<List<CourseDto>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable("id") Long id) {
        return courseService.getCourseById(id).map(courseDto -> new ResponseEntity<>(courseDto, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



}
