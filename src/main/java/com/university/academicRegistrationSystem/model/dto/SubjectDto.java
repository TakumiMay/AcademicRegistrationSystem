package com.university.academicRegistrationSystem.model.dto;

public class SubjectDto {

    private Long id;
    private String name;
    private String schedule;
    private String professor;
    private int credits;
    private String course;

    public SubjectDto() {
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

}
