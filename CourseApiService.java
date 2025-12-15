package com.example.app.service;

import com.example.app.dto.ApiCourse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import reactor.core.publisher.Mono;

@Service
public class CourseApiService {
    private final WebClient webClient;

    // Default base URL points to local mock server; change via application.properties if needed
    public CourseApiService() {
        this.webClient = WebClient.create("http://localhost:3000");
    }

    public List<ApiCourse> getAllCourses() {
        Mono<List<ApiCourse>> mono = webClient.get()
                .uri("/courses")
                .retrieve()
                .bodyToFlux(ApiCourse.class)
                .collectList();
        return mono.block();
    }

    public ApiCourse getCourseById(Long id) {
        return webClient.get()
                .uri("/courses/{id}", id)
                .retrieve()
                .bodyToMono(ApiCourse.class)
                .block();
    }
}
