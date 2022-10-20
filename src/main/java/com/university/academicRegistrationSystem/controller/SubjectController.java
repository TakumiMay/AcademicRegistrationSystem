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
@RequestMapping(value = "/courses/{id}/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/create")
    @Operation(summary = "Add a new subject to a course")
    public ResponseEntity<SubjectDto> addSubject(@PathVariable("id") Long id, @Valid @RequestBody SubjectDto subjectDto) {
        return subjectService.addSubject(id, subjectDto).map(subDto -> new ResponseEntity<>(subDto, HttpStatus.CREATED)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    @Operation(summary = "Obtain all the subjects of a course its id")
    public ResponseEntity<List<SubjectDto>> getAllByCourse(@PathVariable("id") Long id) {
        return subjectService.getAllByCourse(id).map(subjectDto -> new ResponseEntity<>(subjectDto, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{subId}")
    @Operation(summary = "Obtain a subject of a course given the ids")
    public ResponseEntity<SubjectDto> getSubject(@PathVariable("id") Long id, @PathVariable("subId") Long subId) {
        return subjectService.getSubjectById(id, subId).map(subjectDto -> new ResponseEntity<>(subjectDto, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{subId}")
    @Operation(summary = "Edit a subject of a course given the ids and data")
    public ResponseEntity<SubjectDto> editSubject(@PathVariable("id") Long id, @PathVariable("subId") Long subId, @Valid @RequestBody SubjectDto subjectDto) {
        return subjectService.editSubject(id, subId, subjectDto).map(sDto -> new ResponseEntity<>(sDto, HttpStatus.ACCEPTED)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{subId}")
    @Operation(summary = "Delete a subject of a course given the ids")
    public ResponseEntity<String> deleteSubject(@PathVariable("id") Long id, @PathVariable("subId") Long subId) {
        return (subjectService.deleteSubject(id, subId))?ResponseEntity.ok("Subject "+ subId +" from Course "+ id +" deleted successfully."):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
