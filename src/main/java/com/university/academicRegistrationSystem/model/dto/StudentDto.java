package com.university.academicRegistrationSystem.model.dto;

import java.util.ArrayList;
import java.util.List;

public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String program;
    private Double average;
    private List<SubjectDto> subjects = new ArrayList<>();

    public StudentDto(Long id, String firstName, String lastName, String program, Double average) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.program = program;
        this.average = average;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public List<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDto> subjects) {
        this.subjects = subjects;
    }

}
