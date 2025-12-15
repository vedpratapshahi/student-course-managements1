package com.example.app.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class AIService {

        @Value("${openai.api.key:}")
        private String apiKey;

        public String generateSyllabus(String courseTitle, String courseDescription) {
                if (apiKey == null || apiKey.isEmpty()) {
                        return "OpenAI API key not configured. Please add openai.api.key to application.properties";
                }

                try {
                        OpenAiService service = new OpenAiService(apiKey, Duration.ofSeconds(60));

                        String prompt = String.format(
                                        "Generate a detailed syllabus for the course: %s\n" +
                                                        "Description: %s\n\n" +
                                                        "Format the response as a structured syllabus with:\n" +
                                                        "1. Main topics (numbered)\n" +
                                                        "2. Sub-topics under each main topic (lettered)\n" +
                                                        "3. Key learning objectives for each sub-topic\n\n" +
                                                        "Make it comprehensive and educational. Use clear formatting with headers and bullet points.",
                                        courseTitle, courseDescription);

                        List<ChatMessage> messages = new ArrayList<>();
                        messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(),
                                        "You are an expert curriculum designer. Create detailed, structured course syllabi."));
                        messages.add(new ChatMessage(ChatMessageRole.USER.value(), prompt));

                        ChatCompletionRequest request = ChatCompletionRequest.builder()
                                        .model("gpt-3.5-turbo")
                                        .messages(messages)
                                        .maxTokens(2000)
                                        .temperature(0.7)
                                        .build();

                        String response = service.createChatCompletion(request)
                                        .getChoices()
                                        .get(0)
                                        .getMessage()
                                        .getContent();

                        service.shutdownExecutor();
                        return response;

                } catch (Exception e) {
                        return "Error generating syllabus: " + e.getMessage() +
                                        "\n\nPlease check your API key and internet connection.";
                }
        }

        public String generateTopicDetails(String topic) {
                if (apiKey == null || apiKey.isEmpty()) {
                        return "API key not configured";
                }

                try {
                        OpenAiService service = new OpenAiService(apiKey, Duration.ofSeconds(30));

                        String prompt = String.format(
                                        "Explain the topic '%s' in detail. Include:\n" +
                                                        "1. Definition and overview\n" +
                                                        "2. Key concepts and sub-topics\n" +
                                                        "3. Real-world applications\n" +
                                                        "4. Learning objectives\n\n" +
                                                        "Keep it educational and well-structured.",
                                        topic);

                        List<ChatMessage> messages = new ArrayList<>();
                        messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(),
                                        "You are an expert educator. Explain topics clearly and comprehensively."));
                        messages.add(new ChatMessage(ChatMessageRole.USER.value(), prompt));

                        ChatCompletionRequest request = ChatCompletionRequest.builder()
                                        .model("gpt-3.5-turbo")
                                        .messages(messages)
                                        .maxTokens(1000)
                                        .temperature(0.7)
                                        .build();

                        String response = service.createChatCompletion(request)
                                        .getChoices()
                                        .get(0)
                                        .getMessage()
                                        .getContent();

                        service.shutdownExecutor();
                        return response;

                } catch (Exception e) {
                        return "Error: " + e.getMessage();
                }
        }

        public String searchCoursesWithAI(String query, String coursesJson) {
                if (apiKey == null || apiKey.isEmpty()) {
                        return "{\"error\": \"OpenAI API key not configured. Please add openai.api.key to application.properties\"}";
                }

                try {
                        OpenAiService service = new OpenAiService(apiKey, Duration.ofSeconds(30));

                        String prompt = String.format(
                                        "A student is searching for courses with this query: \"%s\"\n\n" +
                                                        "Here are the available courses:\n%s\n\n" +
                                                        "Analyze the student's query and recommend the most relevant courses. "
                                                        +
                                                        "Return a JSON response with this exact format:\n" +
                                                        "{\n" +
                                                        "  \"recommendations\": [\n" +
                                                        "    {\n" +
                                                        "      \"courseId\": <course_id>,\n" +
                                                        "      \"relevanceScore\": <1-10>,\n" +
                                                        "      \"explanation\": \"<why this course matches>\"\n" +
                                                        "    }\n" +
                                                        "  ],\n" +
                                                        "  \"summary\": \"<brief summary of recommendations>\"\n" +
                                                        "}\n\n" +
                                                        "Only recommend courses that are actually relevant. If no courses match well, return an empty recommendations array.",
                                        query, coursesJson);

                        List<ChatMessage> messages = new ArrayList<>();
                        messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(),
                                        "You are an expert educational advisor. Analyze student queries and recommend the most relevant courses. Always respond with valid JSON only, no additional text."));
                        messages.add(new ChatMessage(ChatMessageRole.USER.value(), prompt));

                        ChatCompletionRequest request = ChatCompletionRequest.builder()
                                        .model("gpt-3.5-turbo")
                                        .messages(messages)
                                        .maxTokens(1500)
                                        .temperature(0.5)
                                        .build();

                        String response = service.createChatCompletion(request)
                                        .getChoices()
                                        .get(0)
                                        .getMessage()
                                        .getContent();

                        service.shutdownExecutor();
                        return response;

                } catch (Exception e) {
                        return "{\"error\": \"Error processing AI search: " + e.getMessage().replace("\"", "'") + "\"}";
                }
        }
}
