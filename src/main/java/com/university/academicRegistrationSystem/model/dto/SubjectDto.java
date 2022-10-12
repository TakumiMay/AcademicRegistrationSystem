package com.university.academicRegistrationSystem.model.dto;

import java.util.ArrayList;
import java.util.List;

public class SubjectDto {

    private Long id;
    private String name;
    private String schedule;
    private String professor;
    private int credits;
    private Long course;
    private List<StudentDto> students;

    public SubjectDto(Long id, String name, String schedule, String professor, int credits, Long course) {
        this.id = id;
        this.name = name;
        this.schedule = schedule;
        this.professor = professor;
        this.credits = credits;
        this.course = course;
        this.students = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getCredits() {
        return credits;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }

}
