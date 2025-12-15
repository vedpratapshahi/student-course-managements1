package com.example.app.config;

import com.example.app.model.Student;
import com.example.app.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(StudentRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                Student admin = new Student();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("adminpass"));
                admin.setRole("ROLE_ADMIN");
                repo.save(admin);
            }
            if (repo.findByUsername("student").isEmpty()) {
                Student s = new Student();
                s.setUsername("student");
                s.setPassword(encoder.encode("studentpass"));
                s.setRole("ROLE_STUDENT");
                repo.save(s);
            }
        };
    }
}
