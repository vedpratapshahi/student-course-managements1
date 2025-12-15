package com.example.app.service;

import com.example.app.model.Enrollment;
import com.example.app.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollmentRepository repo;

    public EnrollmentService(EnrollmentRepository repo) {
        this.repo = repo;
    }

    public Enrollment enroll(Long studentId, Long courseId, String courseName) {
        Enrollment e = new Enrollment();
        e.setStudentId(studentId);
        e.setCourseId(courseId);
        e.setCourseName(courseName);
        return repo.save(e);
    }

    public List<Enrollment> getForStudent(Long studentId) {
        return repo.findByStudentId(studentId);
    }
}
