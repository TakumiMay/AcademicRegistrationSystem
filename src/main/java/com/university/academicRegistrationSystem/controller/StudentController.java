package com.university.academicRegistrationSystem.controller;

import com.university.academicRegistrationSystem.model.dto.StudentDto;
import com.university.academicRegistrationSystem.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/create")
    public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto studentDto){
        ResponseEntity<StudentDto> response = new ResponseEntity<StudentDto>(studentService.addStudent(studentDto), HttpStatus.OK);
        return response;
    }

    @GetMapping("/all")
    @Operation(summary ="Obtain all the students")
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudent(Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

}
