package com.cognetix.portfolio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PortfolioManager {

    private final List<Portfolio> records = new ArrayList<>();

    // Add record (assumes already validated)
    public void addRecord(Portfolio p) {
        records.add(p);
    }

    public List<Portfolio> listAllRecords() {
        // return a copy to prevent external modification
        return new ArrayList<>(records);
    }

    // Validation: name non-empty
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // Email must contain '@' (simple check per spec)
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        email = email.trim();
        int at = email.indexOf('@');
        int dot = email.lastIndexOf('.');
        return at > 0 && dot > at + 1 && dot < email.length() - 1;
    }

    // Phone must be digits only (7-15 digits as earlier spec recommended)
    public static boolean isValidPhone(String phone) {
        if (phone == null) return false;
        String trimmed = phone.trim();
        return trimmed.matches("\\d{7,15}");
    }

    // Parse comma-separated input into List<String>, trimming and removing empties
    public static List<String> parseList(String csv) {
        if (csv == null) return new ArrayList<>();
        return java.util.Arrays.stream(csv.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    // Display a single record in the exact required format
    public static void displayRecord(Portfolio p) {
        System.out.println("=========================");
        System.out.println("PERSONAL DETAILS");
        System.out.println("=========================");
        System.out.printf("Name    : %s%n", p.getName());
        System.out.printf("Email   : %s%n", p.getEmail());
        System.out.printf("Phone   : %s%n", p.getPhone());
        System.out.println("------------------------------------------");
        System.out.print("Skills: ");
        if (p.getSkills() == null || p.getSkills().isEmpty()) {
            System.out.println("None");
        } else {
            System.out.println();
            for (String s : p.getSkills()) {
                System.out.printf(" - %s%n", s);
            }
        }
        System.out.println("------------------------------------------");
        System.out.print("Achievements: ");
        if (p.getAchievements() == null || p.getAchievements().isEmpty()) {
            System.out.println("None");
        } else {
            System.out.println();
            for (String a : p.getAchievements()) {
                System.out.printf(" - %s%n", a);
            }
        }
        System.out.println("------------------------------------------");
    }
}
