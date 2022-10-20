package com.university.academicRegistrationSystem.controller;

import com.university.academicRegistrationSystem.model.dto.StudentDto;
import com.university.academicRegistrationSystem.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/registration")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/student-to-subject")
    @Operation(summary = "Add a student to a subject")
    public ResponseEntity<String> registerStudentInSubject(@RequestParam Long stuId, @RequestParam Long subId) {
        return (enrollmentService.enrollStudent(stuId, subId))?ResponseEntity.ok("Student with id "+ stuId +" successfully enrolled in subject "+ subId):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/students-in subject")
    @Operation(summary = "Obtain all students of a subject")
    public ResponseEntity<List<StudentDto>> getStudentsBySubject(@RequestParam Long subId) {
        return enrollmentService.getAllStudentsBySubject(subId).map(studentDto -> new ResponseEntity<>(studentDto, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
