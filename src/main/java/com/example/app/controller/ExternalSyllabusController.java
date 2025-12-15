package com.example.app.controller;

import com.example.app.dto.SyllabusTopic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/external/syllabus")
public class ExternalSyllabusController {

    @GetMapping
    public List<SyllabusTopic> getSyllabus(@RequestParam(required = false) String standard,
            @RequestParam String subject) {
        List<SyllabusTopic> syllabus = new ArrayList<>();

        // Flexible matching for "Maths"
        String subjectLower = subject.toLowerCase();
        if (subjectLower.contains("math")) {
            syllabus.add(new SyllabusTopic("Unit I", "Algebra & Calculus",
                    "Matrices, Integrals, Derivatives, Differential Equations", 30,
                    List.of("Types of Matrices", "Inverse of a Matrix", "Continuity and Differentiability",
                            "Applications of Derivatives", "Integrals", "Differential Equations")));
            syllabus.add(new SyllabusTopic("Unit II", "Geometry & Vectors", "3D Geometry, Vector Algebra", 20,
                    List.of("Vectors and Scalars", "Dot and Cross Product", "Direction Cosines", "Equation of a Line",
                            "Equation of a Plane")));
            syllabus.add(new SyllabusTopic("Unit III", "Statistics & Probability",
                    "Bayes Theorem, Probability Distributions", 15,
                    List.of("Conditional Probability", "Bayes' Theorem", "Random Variables", "Bernoulli Trials",
                            "Binomial Distribution")));
            syllabus.add(new SyllabusTopic("Unit IV", "Relations & Functions",
                    "Types of Relations, Inverse Trig Functions", 15,
                    List.of("Reflexive, Symmetric, Transitive Relations", "One-to-One and Onto Functions",
                            "Inverse Trigonometric Functions")));
            return syllabus;
        }

        // Computer Science / Programming
        if (subjectLower.contains("java") || subjectLower.contains("python") || subjectLower.contains("c++")
                || subjectLower.contains("programming") || subjectLower.contains("computer")) {
            syllabus.add(new SyllabusTopic("Unit 1", "Programming Fundamentals", "Variables, Loops, Logic Building", 20,
                    List.of("Data Types & Variables", "Control Structures (If-Else, Loops)", "Functions & Methods",
                            "Input/Output Handling")));
            syllabus.add(new SyllabusTopic("Unit 2", "Object Oriented Programming", "Classes, Objects, Inheritance", 25,
                    List.of("Classes and Objects", "Inheritance & Polymorphism", "Encapsulation & Abstraction",
                            "Exception Handling")));
            syllabus.add(new SyllabusTopic("Unit 3", "Data Structures", "Arrays, Lists, Maps", 25,
                    List.of("Arrays & Strings", "Linked Lists", "Stacks & Queues", "HashMaps & Sets")));
            syllabus.add(new SyllabusTopic("Unit 4", "Web & Database", "Basic Web Dev and SQL", 15,
                    List.of("HTML/CSS Basics", "Database Connectivity (JDBC/SQL)", "REST APIs Overview")));
            syllabus.add(new SyllabusTopic("Unit 5", "Project", "Final Application Development", 15,
                    List.of("Requirement Analysis", "Coding & Implementation", "Testing & Debugging",
                            "Final Presentation")));
            return syllabus;
        }

        // Science (Physics/Chemistry/Biology)
        if (subjectLower.contains("physics") || subjectLower.contains("chemistry") || subjectLower.contains("biology")
                || subjectLower.contains("science")) {
            syllabus.add(new SyllabusTopic("Unit 1", "Fundamental Concepts", "Basic Laws and Theories", 20,
                    List.of("Scientific Method", "Units & Measurements", "Fundamental Laws", "Matter & Energy")));
            syllabus.add(new SyllabusTopic("Unit 2", "Experimental Analysis", "Lab Work and Experiments", 25,
                    List.of("Lab Safety", "Experimental Procedures", "Data Collection", "Error Analysis")));
            syllabus.add(new SyllabusTopic("Unit 3", "Advanced Theory", "In-depth Theoretical Study", 25,
                    List.of("Thermodynamics/Genetics/Mechanics", "Atomic Structure/Cell Biology",
                            "Chemical Bonding/Forces")));
            syllabus.add(new SyllabusTopic("Unit 4", "Applied Science", "Real-world Applications", 15,
                    List.of("Industrial Applications", "Environmental Impact", "Technological Advancements")));
            syllabus.add(new SyllabusTopic("Unit 5", "Research Project", "Independent Research", 15,
                    List.of("Topic Selection", "Literature Review", "Experimentation", "Report Writing")));
            return syllabus;
        }

        // History / Social Studies
        if (subjectLower.contains("history") || subjectLower.contains("civics") || subjectLower.contains("geography")) {
            syllabus.add(new SyllabusTopic("Unit 1", "Ancient Civilizations", "Early Human History", 20,
                    List.of("Mesopotamia & Egypt", "Indus Valley Civilization", "Ancient Greece & Rome",
                            "Early Dynasties")));
            syllabus.add(new SyllabusTopic("Unit 2", "Medieval Period", "Feudalism and Empires", 20,
                    List.of("The Middle Ages", "Rise of Empires", "Cultural Exchanges", "Trade Routes")));
            syllabus.add(new SyllabusTopic("Unit 3", "Modern Era", "Industrialization and Revolutions", 25,
                    List.of("Industrial Revolution", "World Wars", "Cold War Era", "Decolonization")));
            syllabus.add(new SyllabusTopic("Unit 4", "Civics & Governance", "Political Systems", 20,
                    List.of("Constitution & Rights", "Government Structures", "International Relations",
                            "Global Issues")));
            syllabus.add(new SyllabusTopic("Unit 5", "Map Work & Project", "Geographical Analysis", 15,
                    List.of("Map Reading Skills", "Case Study Analysis", "Historical Research", "Presentation")));
            return syllabus;
        }

        // Generative Mock Data for ANY other query with Sub-Topics
        System.out.println("Generating generic syllabus for: " + subject);

        syllabus.add(new SyllabusTopic("Unit 1", "Introduction to " + subject,
                "Core concepts, history, and fundamentals of " + subject, 10,
                List.of("History of " + subject, "Basic Terminology", "Key Principles", "Scope and Importance")));
        syllabus.add(new SyllabusTopic("Unit 2", "Advanced " + subject + " Theory",
                "Deep dive into complex topics and theoretical frameworks", 25,
                List.of("Theoretical Models", "Critical Analysis", "Advanced Methodologies", "Research Perspectives")));
        syllabus.add(new SyllabusTopic("Unit 3", "Applied " + subject,
                "Practical applications and real-world case studies", 20,
                List.of("Case Studies", "Industry Applications", "Problem Solving Techniques",
                        "Tools and Technologies")));
        syllabus.add(
                new SyllabusTopic("Unit 4", "Modern Trends in " + subject, "Current developments and future scope", 15,
                        List.of("Recent Innovations", "Future Trends", "Global Impact", "Ethical Considerations")));
        syllabus.add(
                new SyllabusTopic("Unit 5", "Project Work", "Capstone project demonstrating mastery of " + subject, 30,
                        List.of("Project Planning", "Execution", "Documentation", "Presentation")));

        return syllabus;
    }
}
