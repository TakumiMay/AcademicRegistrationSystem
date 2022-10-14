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
    public ResponseEntity<SubjectDto> addSubject(@PathVariable("id") Long id, @Valid @RequestBody SubjectDto subjectDto) {
        return subjectService.addSubject(id, subjectDto).map(subDto -> new ResponseEntity<>(subDto, HttpStatus.CREATED)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    @Operation(summary ="Obtain all the subjects of a course")
    public ResponseEntity<List<SubjectDto>> getAllByCourse(@PathVariable("id") Long id) {
        return ResponseEntity.ok(subjectService.getAllByCourse(id));
    }

    @GetMapping("/{subId}")
    @Operation(summary ="Obtain a subject of a course given its id")
    public ResponseEntity<SubjectDto> getSubject(@PathVariable("id") Long id, @PathVariable("subId") Long subId) {
        return subjectService.getSubjectById(id, subId).map(subjectDto -> new ResponseEntity<>(subjectDto, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{subId}")
    public ResponseEntity<SubjectDto> editSubject(@PathVariable("id") Long id, @Valid @RequestBody SubjectDto subjectDto) {
        return subjectService.editSubject(id, subjectDto).map(sDto -> new ResponseEntity<>(sDto, HttpStatus.ACCEPTED)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{subId}")
    public ResponseEntity<String> deleteSubject(@PathVariable("id") Long id, @PathVariable("subId") Long subId) {
        return (subjectService.deleteSubject(id, subId))?ResponseEntity.ok("Subject "+ subId +" from Course "+ id +" deleted successfully."):new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
