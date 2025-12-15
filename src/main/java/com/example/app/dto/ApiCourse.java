package com.example.app.dto;

public class ApiCourse {

    private Long id;
    private String title;
    private String description;
    private String instructor;
    private int durationHours;

    public ApiCourse() {
    }

    public ApiCourse(Long id, String title, String description, String instructor, int durationHours) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.durationHours = durationHours;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public int getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(int durationHours) {
        this.durationHours = durationHours;
    }
}
