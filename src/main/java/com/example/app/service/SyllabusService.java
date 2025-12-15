package com.example.app.service;

import com.example.app.dto.SyllabusTopic;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class SyllabusService {
    private final WebClient webClient;

    public SyllabusService() {
        // In a real scenario, this URL would be the external API URL
        this.webClient = WebClient.create("http://localhost:8080");
    }

    public List<SyllabusTopic> getSyllabus(String standard, String subject) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/external/syllabus")
                            .queryParam("standard", standard)
                            .queryParam("subject", subject)
                            .build())
                    .retrieve()
                    .bodyToFlux(SyllabusTopic.class)
                    .collectList()
                    .block();
        } catch (Exception e) {
            // Fallback if API fails (e.g. connection refused)
            System.err.println("API Call Failed: " + e.getMessage());
            return List.of(
                    new SyllabusTopic("Error", "Could not fetch syllabus", "Please try again later. (API Error)", 0));
        }
    }
}
