package com.example.app.service;

import com.example.app.model.Enrollment;
import com.example.app.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> getForStudent(Long studentId) {
        return enrollmentRepository.findAll()
                .stream()
                .filter(e -> e.getStudentId().equals(studentId))
                .collect(Collectors.toList());
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void enroll(Long studentId, Long courseId, String courseName) {
        Enrollment enrollment = new Enrollment(studentId, courseId, java.time.LocalDate.now());
        enrollmentRepository.save(enrollment);
    }
}
