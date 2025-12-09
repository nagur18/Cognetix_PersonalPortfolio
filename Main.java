package com.cognetix.portfolio;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PortfolioManager manager = new PortfolioManager();

    public static void main(String[] args) {
        System.out.println("=== Personal Portfolio Application ===");
        mainMenuLoop();
        System.out.println("Application exited. Goodbye!");
    }

    private static void mainMenuLoop() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addPortfolioFlow();
                    break;
                case "2":
                    listAllFlow();
                    break;
                case "3":
                    return; // exit
                default:
                    System.out.println("Invalid option. Please choose 1, 2 or 3.");
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Add Portfolio");
        System.out.println("2. List All Portfolios");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addPortfolioFlow() {
        System.out.println("\n-- Add New Portfolio --");

        String name = askNonEmpty("Full Name");
        String email = askValidEmail("Email");
        String phone = askValidPhone("Phone Number (digits only)");
        System.out.print("Skills (comma-separated): ");
        String skillsRaw = scanner.nextLine();
        System.out.print("Achievements (comma-separated): ");
        String achRaw = scanner.nextLine();

        List<String> skills = PortfolioManager.parseList(skillsRaw);
        List<String> achievements = PortfolioManager.parseList(achRaw);

        Portfolio p = new Portfolio(name, email, phone, skills, achievements);
        manager.addRecord(p);
        System.out.println("Record added successfully.");
    }

    // Keep asking until non-empty
    private static String askNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine();
            if (PortfolioManager.isValidName(input)) return input.trim();
            System.out.println("Error: Field cannot be empty. Please re-enter.");
        }
    }

    // Ask until email valid
    private static String askValidEmail(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine();
            if (PortfolioManager.isValidEmail(input)) return input.trim();
            System.out.println("Error: Invalid email format. Must contain '@' and a '.' after it. Please re-enter.");
        }
    }

    // Ask until phone valid
    private static String askValidPhone(String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = scanner.nextLine();
            if (PortfolioManager.isValidPhone(input)) return input.trim();
            System.out.println("Error: Phone must contain digits only (7-15 digits). Please re-enter.");
        }
    }

    private static void listAllFlow() {
        List<Portfolio> all = manager.listAllRecords();
        if (all.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        System.out.println("\n-- All Portfolios --");
        for (Portfolio p : all) {
            PortfolioManager.displayRecord(p);
            System.out.println(); // blank line between records
        }
    }
}
