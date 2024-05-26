package main.java.carefund.controllers;

import carefund.models.Donation;
import main.java.carefund.models.User;
import main.java.carefund.data.UserData;
import java.util.List;

public class DonationHistoryController {

    public List<Donation> getDonationHistory(User user) {
        return UserData.getDonationHistory(user);
    }

    public void addDonationToHistory(Donation donation) {
        UserData.addDonationToHistory(donation);
    }
}
