package com.example.app.service;

import com.example.app.model.Student;
import com.example.app.repository.StudentRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository repo;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public Student register(String username, String rawPassword) {
        Student s = new Student();
        s.setUsername(username);
        s.setPassword(passwordEncoder.encode(rawPassword));
        s.setRole("ROLE_STUDENT");
        return repo.save(s);
    }

    public Optional<Student> findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
