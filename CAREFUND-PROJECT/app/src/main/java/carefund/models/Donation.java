package carefund.models;

import java.time.LocalDate;
import main.java.carefund.models.User;

public class Donation {
    private String organization;
    private double amount;
    private String paymentMethod;
    private LocalDate donationDate;
    private User user;

    // Constructor
    public Donation(User user, String organization, double amount, String paymentMethod) {
        this.user = user;
        this.organization = organization;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.donationDate = LocalDate.now(); // Set the donation date to the current date
    }

    // Getters
    public String getOrganization() {
        return organization;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDate getDonationDate() {
        return donationDate;
    }

    public User getUser() {
        return user;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;

    }
}
