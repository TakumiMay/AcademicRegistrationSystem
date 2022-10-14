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
    public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto studentDto){
        return new ResponseEntity<StudentDto>(studentService.addStudent(studentDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(summary ="Obtain all the students")
    public ResponseEntity<List<StudentDto>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{stuId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable("stuId") Long stuId) {
        return studentService.getStudentById(stuId).map(studentDto -> new ResponseEntity<>(studentDto, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{stuId}")
    public ResponseEntity<StudentDto> editStudent(@PathVariable("stuId") Long stuId, @Valid @RequestBody StudentDto studentDto) {
        return studentService.editStudent(stuId).map(stuDto -> new ResponseEntity<>(stuDto, HttpStatus.ACCEPTED)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{stuId}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable("stuId") Long stuId) {
        return studentService.deleteStudent(stuId).map(studentDto -> new ResponseEntity<>(studentDto, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
