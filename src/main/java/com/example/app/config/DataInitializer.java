package com.example.app.config;

import com.example.app.service.StudentService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final StudentService studentService;

    public DataInitializer(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostConstruct
    public void initData() {
        // Create a default student account for testing if it doesn't exist
        if (studentService.findByUsername("student").isEmpty()) {
            studentService.register("student", "password");
            System.out.println("Default student account created: username=student, password=password");
        }
    }
}
