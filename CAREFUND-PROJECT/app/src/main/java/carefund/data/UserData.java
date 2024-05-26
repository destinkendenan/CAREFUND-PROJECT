package main.java.carefund.data;

import main.java.carefund.models.User;
import carefund.models.Donation;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private static List<User> users = new ArrayList<>();
    private static List<Donation> donationHistory = new ArrayList<>();

    static {
        users.add(new User("user1", "user1@example.com", "Password1"));
        users.add(new User("user2", "user2@example.com", "Password2"));
    }

    public static User authenticate(String username, String password) {
        for (User user : users) {
            System.out.println(username + password);
            System.out.println("- " + user.getUsername() + "\n- " + user.getPassword());
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // Registration
    public static boolean registerUser(User user) {

        if (isUsernameTaken(user.getUsername()) || isEmailTaken(user.getEmail())) {
            return false;
        }

        users.add(user);
        return true;
    }

    public static void updateUserProfile(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(updatedUser.getUsername())) {
                users.set(i, updatedUser);
                break;
            }
        }
    }

    public static boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailTaken(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static void addDonationToHistory(Donation donation) {
        donationHistory.add(donation);
    }

    public static List<Donation> getDonationHistory(User user) {
        List<Donation> userDonations = new ArrayList<>();
        for (Donation donation : donationHistory) {
            if (donation.getUser().equals(user)) {
                userDonations.add(donation);
            }
        }
        return userDonations;
    }
}
