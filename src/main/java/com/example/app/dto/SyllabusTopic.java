package com.example.app.dto;

public class SyllabusTopic {
    private String unit;
    private String topicName;
    private String description;
    private int weightage;
    private java.util.List<String> subTopics;

    public SyllabusTopic() {
    }

    public SyllabusTopic(String unit, String topicName, String description, int weightage) {
        this.unit = unit;
        this.topicName = topicName;
        this.description = description;
        this.weightage = weightage;
        this.subTopics = new java.util.ArrayList<>();
    }

    public SyllabusTopic(String unit, String topicName, String description, int weightage,
            java.util.List<String> subTopics) {
        this.unit = unit;
        this.topicName = topicName;
        this.description = description;
        this.weightage = weightage;
        this.subTopics = subTopics;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeightage() {
        return weightage;
    }

    public void setWeightage(int weightage) {
        this.weightage = weightage;
    }

    public java.util.List<String> getSubTopics() {
        return subTopics;
    }

    public void setSubTopics(java.util.List<String> subTopics) {
        this.subTopics = subTopics;
    }
}
