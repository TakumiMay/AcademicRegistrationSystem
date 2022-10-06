package com.university.academicRegistrationSystem.model.dto;

public class CourseDto {

    private Long id;
    private String courseName;
    private String[] programs;

    public CourseDto(){
    }

    public CourseDto(Long id, String courseName, String[] programs) {
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

    public String[] getPrograms() {
        return programs;
    }

    public void setPrograms(String[] programs) {
        this.programs = programs;
    }

}
