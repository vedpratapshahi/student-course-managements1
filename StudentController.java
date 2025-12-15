package com.example.app.controller;

import com.example.app.dto.ApiCourse;
import com.example.app.model.Student;
import com.example.app.service.CourseApiService;
import com.example.app.service.EnrollmentService;
import com.example.app.repository.StudentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final CourseApiService courseApiService;
    private final EnrollmentService enrollmentService;
    private final StudentRepository studentRepo;

    public StudentController(CourseApiService courseApiService, EnrollmentService enrollmentService, StudentRepository studentRepo) {
        this.courseApiService = courseApiService;
        this.enrollmentService = enrollmentService;
        this.studentRepo = studentRepo;
    }

    @GetMapping("/courses")
    public String showCourses(Model model) {
        model.addAttribute("courses", courseApiService.getAllCourses());
        return "course-list";
    }

    @GetMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId, Authentication auth, Model model) {
        ApiCourse course = courseApiService.getCourseById(courseId);
        if (course == null) {
            model.addAttribute("error", "Course not found");
            return "course-list";
        }
        Student s = studentRepo.findByUsername(auth.getName()).orElseThrow();
        enrollmentService.enroll(s.getId(), course.getId(), course.getName());
        return "redirect:/student/my-enrollments";
    }

    @GetMapping("/my-enrollments")
    public String myEnrollments(Authentication auth, Model model) {
        Student s = studentRepo.findByUsername(auth.getName()).orElseThrow();
        model.addAttribute("enrollments", enrollmentService.getForStudent(s.getId()));
        return "enrollments";
    }
}
