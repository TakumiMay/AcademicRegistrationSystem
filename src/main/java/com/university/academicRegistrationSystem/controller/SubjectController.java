package com.university.academicRegistrationSystem.controller;

import com.university.academicRegistrationSystem.model.dto.SubjectDto;
import com.university.academicRegistrationSystem.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/courses/{cId}/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    public ResponseEntity<SubjectDto> addSubject(@Valid @RequestBody SubjectDto subjectDto){
        ResponseEntity<SubjectDto> response = new ResponseEntity<SubjectDto>(subjectService.addSubject(subjectDto), HttpStatus.OK);
        return response;
    }

    @GetMapping("/all")
    @Operation(summary ="Obtain all the subjects of a course")
    public ResponseEntity<List<SubjectDto>> getAllByCourse(@PathVariable Long cId) {
        return ResponseEntity.ok(subjectService.getAllByCourse(cId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubject() {
        return null;
    }

}
