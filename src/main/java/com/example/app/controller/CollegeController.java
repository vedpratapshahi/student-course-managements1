package com.example.app.controller;

import com.example.app.model.College;
import com.example.app.repository.CollegeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class CollegeController {

        private final CollegeRepository collegeRepository;

        public CollegeController(CollegeRepository collegeRepository) {
                this.collegeRepository = collegeRepository;
        }

        @PostConstruct
        public void initData() {
                if (collegeRepository.count() == 0) {
                        List<College> colleges = Arrays.asList(
                                        // IITs
                                        new College("IIT Bombay", 19.1334, 72.9133, "Powai, Mumbai, Maharashtra 400076",
                                                        "Indian Institute of Technology Bombay is a public technical and research university."),
                                        new College("IIT Delhi", 28.5450, 77.1926, "Hauz Khas, New Delhi, Delhi 110016",
                                                        "Indian Institute of Technology Delhi is a public technical university."),
                                        new College("IIT Madras", 12.9915, 80.2337, "Chennai, Tamil Nadu 600036",
                                                        "Indian Institute of Technology Madras is a public technical university."),
                                        new College("IIT Kanpur", 26.5113, 80.2329,
                                                        "Kalyanpur, Kanpur, Uttar Pradesh 208016",
                                                        "Indian Institute of Technology Kanpur is a public technical university."),
                                        new College("IIT Kharagpur", 22.3149, 87.3105, "Kharagpur, West Bengal 721302",
                                                        "Indian Institute of Technology Kharagpur is the oldest IIT."),
                                        new College("IIT Roorkee", 29.8644, 77.8964, "Roorkee, Uttarakhand 247667",
                                                        "Indian Institute of Technology Roorkee."),
                                        new College("IIT Guwahati", 26.1930, 91.6958, "Guwahati, Assam 781039",
                                                        "Indian Institute of Technology Guwahati."),
                                        new College("IIT Hyderabad", 17.5934, 78.1210,
                                                        "Kandi, Sangareddy, Telangana 502285",
                                                        "Indian Institute of Technology Hyderabad."),

                                        // NITs
                                        new College("NIT Trichy", 10.7596, 78.8145,
                                                        "Tiruchirappalli, Tamil Nadu 620015",
                                                        "National Institute of Technology Tiruchirappalli."),
                                        new College("NIT Warangal", 17.9869, 79.5320, "Warangal, Telangana 506004",
                                                        "National Institute of Technology Warangal."),
                                        new College("NIT Surathkal", 13.0109, 74.7951, "Surathkal, Karnataka 575025",
                                                        "National Institute of Technology Karnataka."),
                                        new College("NIT Calicut", 11.2588, 75.7804, "Calicut, Kerala 673601",
                                                        "National Institute of Technology Calicut."),

                                        // IISc and Central Universities
                                        new College("IISc Bangalore", 13.0219, 77.5671,
                                                        "CV Raman Rd, Bengaluru, Karnataka 560012",
                                                        "Indian Institute of Science is a premier research institution."),
                                        new College("University of Delhi", 28.6904, 77.2072, "Delhi 110007",
                                                        "A premier central university located in New Delhi."),
                                        new College("Jawaharlal Nehru University", 28.5400, 77.1668,
                                                        "New Delhi, Delhi 110067",
                                                        "JNU is a public central university."),
                                        new College("Banaras Hindu University", 25.2677, 82.9913,
                                                        "Varanasi, Uttar Pradesh 221005",
                                                        "BHU is a central university."),
                                        new College("Aligarh Muslim University", 27.8974, 78.0880,
                                                        "Aligarh, Uttar Pradesh 202002",
                                                        "AMU is a public central university."),

                                        // State Universities
                                        new College("Anna University", 13.0102, 80.2357,
                                                        "Guindy, Chennai, Tamil Nadu 600025",
                                                        "A public state university in Tamil Nadu."),
                                        new College("Pune University", 18.5470, 73.8277, "Pune, Maharashtra 411007",
                                                        "Savitribai Phule Pune University."),
                                        new College("Mumbai University", 18.9750, 72.8258, "Mumbai, Maharashtra 400032",
                                                        "University of Mumbai."),

                                        // Medical Colleges
                                        new College("AIIMS Delhi", 28.5672, 77.2100,
                                                        "Ansari Nagar, New Delhi, Delhi 110029",
                                                        "All India Institute of Medical Sciences, Delhi."),
                                        new College("CMC Vellore", 12.9252, 79.1353, "Vellore, Tamil Nadu 632004",
                                                        "Christian Medical College Vellore."),

                                        // Management Institutes
                                        new College("IIM Ahmedabad", 23.0225, 72.5714, "Ahmedabad, Gujarat 380015",
                                                        "Indian Institute of Management Ahmedabad."),
                                        new College("IIM Bangalore", 13.0097, 77.5680, "Bangalore, Karnataka 560076",
                                                        "Indian Institute of Management Bangalore."),
                                        new College("IIM Calcutta", 22.5726, 88.3639, "Kolkata, West Bengal 700104",
                                                        "Indian Institute of Management Calcutta."),

                                        // Other Premier Institutions
                                        new College("BITS Pilani", 28.3670, 75.5870, "Pilani, Rajasthan 333031",
                                                        "Birla Institute of Technology and Science."),
                                        new College("VIT Vellore", 12.9692, 79.1559, "Vellore, Tamil Nadu 632014",
                                                        "Vellore Institute of Technology."),
                                        new College("Manipal Institute of Technology", 13.3500, 74.7833,
                                                        "Manipal, Karnataka 576104",
                                                        "Manipal Institute of Technology."),
                                        new College("SRM Institute Chennai", 12.8230, 80.0444,
                                                        "Kattankulathur, Tamil Nadu 603203",
                                                        "SRM Institute of Science and Technology."));
                        collegeRepository.saveAll(colleges);
                }
        }

        @GetMapping("/student/colleges")
        public String showCollegeLocator(Model model) {
                return "college-locator";
        }

        @GetMapping("/api/colleges")
        @ResponseBody
        public List<College> getColleges() {
                return collegeRepository.findAll();
        }
}
