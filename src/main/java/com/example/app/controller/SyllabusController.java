package com.example.app.controller;

import com.example.app.service.AIService;
import com.example.app.service.SyllabusService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student/syllabus")
public class SyllabusController {

    private final SyllabusService syllabusService;
    private final AIService aiService;

    public SyllabusController(SyllabusService syllabusService, AIService aiService) {
        this.syllabusService = syllabusService;
        this.aiService = aiService;
    }

    @GetMapping
    public String showSyllabusPage(@RequestParam(required = false) String standard,
            @RequestParam(required = false) String subject,
            Model model) {
        if (subject != null && !subject.isEmpty()) {
            // If standard is null, pass empty string
            String std = (standard != null) ? standard : "";
            model.addAttribute("syllabus", syllabusService.getSyllabus(std, subject));
            model.addAttribute("selectedStandard", std);
            model.addAttribute("selectedSubject", subject);
        }
        return "syllabus";
    }

    @PostMapping("/generate-ai")
    @ResponseBody
    public String generateAISyllabus(@RequestParam String courseTitle,
            @RequestParam(required = false) String courseDescription) {
        String description = (courseDescription != null && !courseDescription.isEmpty())
                ? courseDescription
                : "A comprehensive course on " + courseTitle;
        return aiService.generateSyllabus(courseTitle, description);
    }
}
