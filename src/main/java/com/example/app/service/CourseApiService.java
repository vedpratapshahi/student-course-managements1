package com.example.app.service;

import com.example.app.dto.ApiCourse;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseApiService {

    // Mock data instead of external API
    private final List<ApiCourse> mockCourses;

    public CourseApiService() {
        mockCourses = new ArrayList<>();
        mockCourses
                .add(new ApiCourse(1L, "Introduction to Java", "Learn Java programming from scratch", "Dr. Smith", 40));
        mockCourses.add(new ApiCourse(2L, "Web Development with Spring Boot", "Build modern web applications",
                "Prof. Johnson", 60));
        mockCourses.add(new ApiCourse(3L, "Database Design", "Master SQL and database concepts", "Dr. Williams", 35));
        mockCourses.add(new ApiCourse(4L, "Python for Data Science", "Analyze data with Python", "Dr. Brown", 50));
        mockCourses.add(
                new ApiCourse(5L, "React Frontend Development", "Build interactive UIs with React", "Prof. Davis", 45));
    }

    public List<ApiCourse> getAllCourses() {
        return new ArrayList<>(mockCourses);
    }

    public ApiCourse getCourseById(Long id) {
        return mockCourses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<ApiCourse> searchCourses(String searchTerm) {
        String lowerSearch = searchTerm.toLowerCase().trim();
        return mockCourses.stream()
                .filter(course -> course.getTitle().toLowerCase().contains(lowerSearch) ||
                        course.getDescription().toLowerCase().contains(lowerSearch) ||
                        course.getInstructor().toLowerCase().contains(lowerSearch))
                .collect(java.util.stream.Collectors.toList());
    }
}
