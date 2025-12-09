package com.cognetix.portfolio;

import java.util.List;

public class Portfolio {
    private String name;
    private String email;
    private String phone;
    private List<String> skills;
    private List<String> achievements;

    public Portfolio(String name, String email, String phone, List<String> skills, List<String> achievements) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.skills = skills;
        this.achievements = achievements;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<String> getSkills() { return skills; }
    public List<String> getAchievements() { return achievements; }

    // You can add setters if needed
}
