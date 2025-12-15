package com.example.app.controller;

import com.example.app.dto.ApiCourse;
import com.example.app.model.Student;
import com.example.app.service.AIService;
import com.example.app.service.CourseApiService;
import com.example.app.service.EnrollmentService;
import com.example.app.repository.StudentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final CourseApiService courseApiService;
    private final EnrollmentService enrollmentService;
    private final StudentRepository studentRepo;
    private final AIService aiService;

    public StudentController(CourseApiService courseApiService, EnrollmentService enrollmentService,
            StudentRepository studentRepo, AIService aiService) {
        this.courseApiService = courseApiService;
        this.enrollmentService = enrollmentService;
        this.studentRepo = studentRepo;
        this.aiService = aiService;
    }

    @GetMapping("/courses")
    public String showCourses(@RequestParam(required = false) String search, Model model) {
        List<ApiCourse> courses;
        if (search != null && !search.trim().isEmpty()) {
            courses = courseApiService.searchCourses(search);
        } else {
            courses = courseApiService.getAllCourses();
        }
        model.addAttribute("courses", courses);
        model.addAttribute("search", search);
        return "course-list";
    }

    @PostMapping(value = "/courses/ai-search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> aiSearchCourses(@RequestParam String query) {
        try {
            // Get all courses
            List<ApiCourse> allCourses = courseApiService.getAllCourses();

            // Convert courses to JSON string for AI context
            String coursesJson = allCourses.stream()
                    .map(c -> String.format(
                            "ID: %d, Title: \"%s\", Description: \"%s\", Instructor: %s, Duration: %d hours",
                            c.getId(), c.getTitle(), c.getDescription(), c.getInstructor(), c.getDurationHours()))
                    .collect(Collectors.joining("\n"));

            // Get AI recommendations
            String aiResponse = aiService.searchCoursesWithAI(query, coursesJson);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(aiResponse);

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error\": \"" + e.getMessage().replace("\"", "'") + "\"}");
        }
    }

    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId, Authentication auth, Model model) {
        ApiCourse course = courseApiService.getCourseById(courseId);
        if (course == null) {
            model.addAttribute("error", "Course not found");
            return "course-list";
        }
        Student s = studentRepo.findByUsername(auth.getName()).orElseThrow();
        enrollmentService.enroll(s.getId(), course.getId(), course.getTitle());
        return "redirect:/student/my-enrollments";
    }

    @GetMapping("/my-enrollments")
    public String myEnrollments(Authentication auth, Model model) {
        Student s = studentRepo.findByUsername(auth.getName()).orElseThrow();
        model.addAttribute("enrollments", enrollmentService.getForStudent(s.getId()));
        return "enrollments";
    }
}
