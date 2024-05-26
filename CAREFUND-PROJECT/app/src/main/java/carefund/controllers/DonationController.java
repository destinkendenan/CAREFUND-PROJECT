package main.java.carefund.controllers;

import main.java.carefund.models.User;
import carefund.models.Donation;

import java.sql.*;
import java.time.LocalDate;

public class DonationController {

    private static final String DB_URL = "jdbc:sqlite:your_database_file.db"; // Replace with your DB path

    public boolean processDonation(User user, String organization, double amount, String paymentMethod) {
        // 1. Input Validation (More comprehensive)
        if (organization == null || organization.isEmpty() || amount <= 0 || paymentMethod == null
                || paymentMethod.isEmpty()) {
            throw new IllegalArgumentException("Invalid donation details.");
        }

        // 2. Simulate Payment Processing (Replace with actual payment gateway
        // integration)
        try {
            Thread.sleep(2000); // Simulate payment processing time
        } catch (InterruptedException e) {
            // Handle interruption (shouldn't happen in this simple example)
            e.printStackTrace();
            return false;
        }

        // 3. Store Donation in Database
        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO donations (user_id, organization, amount, payment_method, donation_date) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, user.getUsername()); // Assuming username is the primary key
            stmt.setString(2, organization);
            stmt.setDouble(3, amount);
            stmt.setString(4, paymentMethod);
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Log the error properly and return false for failure
            System.err.println("Error saving donation to database: " + e.getMessage());
            return false;
        }

        // 4. Optionally Update User's Donation History in Database
        // (This could be done in a separate method if you want to keep this controller
        // more focused)
        // ...

        return true;
    }
}
