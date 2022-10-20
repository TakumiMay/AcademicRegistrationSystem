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
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    @Operation(summary ="Add a new student")
    public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto studentDto){
        return new ResponseEntity<StudentDto>(studentService.addStudent(studentDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary ="Obtain all the students")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{stuId}")
    @Operation(summary ="Obtain a student given its id")
    public ResponseEntity<StudentDto> getStudent(@PathVariable("stuId") Long stuId) {
        return studentService.getStudentById(stuId).map(studentDto -> new ResponseEntity<>(studentDto, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{stuId}")
    @Operation(summary ="Edit a student given its id and data")
    public ResponseEntity<StudentDto> editStudent(@PathVariable("stuId") Long stuId, @Valid @RequestBody StudentDto studentDto) {
        return studentService.editStudent(stuId, studentDto).map(stuDto -> new ResponseEntity<>(stuDto, HttpStatus.ACCEPTED)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{stuId}")
    @Operation(summary ="Delete a student given its id")
    public ResponseEntity<String> deleteStudent(@PathVariable("stuId") Long stuId) {
        return (studentService.deleteStudent(stuId))?ResponseEntity.ok("Student "+ stuId +" deleted successfully."):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
