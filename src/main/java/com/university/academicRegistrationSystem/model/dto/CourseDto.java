package com.university.academicRegistrationSystem.model.dto;


import java.util.ArrayList;
import java.util.List;

public class CourseDto {

    private Long id;
    private String courseName;
    private List<String> programs;
    private List<SubjectDto> subjects = new ArrayList<>();

    public CourseDto(Long id, String courseName, List<String> programs) {
        this.id = id;
        this.courseName = courseName;
        this.programs = programs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<String> getPrograms() {
        return programs;
    }

    public void setPrograms(List<String> programs) {
        this.programs = programs;
    }

    public List<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDto> subjects) {
        this.subjects = subjects;
    }

}
