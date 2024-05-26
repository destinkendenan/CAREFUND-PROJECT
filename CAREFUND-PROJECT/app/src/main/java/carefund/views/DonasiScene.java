package main.java.carefund.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.java.carefund.models.User;
import carefund.navigation.NavigationManager;
import main.java.carefund.controllers.DonationController;

import java.util.ArrayList;
import java.util.List;

public class DonasiScene extends Scene {
    private ComboBox<String> organizationComboBox;
    private TextField amountField;
    private ComboBox<String> paymentMethodComboBox;
    private Label errorLabel;
    private final NavigationManager navigationManager;

    public DonasiScene(NavigationManager navigationManager, User user) {
        super(new GridPane(), 400, 300);
        this.navigationManager = navigationManager;

        GridPane grid = (GridPane) getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("container");

        Label orgLabel = new Label("Organization:");
        orgLabel.getStyleClass().add("label");
        organizationComboBox = new ComboBox<>();
        organizationComboBox.getStyleClass().add("combobox");

        List<String> organizations = new ArrayList<>();
        organizations.add("Yayasan A");
        organizations.add("Yayasan B");
        organizations.add("Yayasan C");
        organizationComboBox.getItems().addAll(organizations);

        Label amountLabel = new Label("Amount:");
        amountLabel.getStyleClass().add("label");
        amountField = new TextField();
        amountField.getStyleClass().add("text-field");

        Label paymentLabel = new Label("Payment Method:");
        paymentLabel.getStyleClass().add("label");
        paymentMethodComboBox = new ComboBox<>();
        paymentMethodComboBox.getStyleClass().add("combobox");
        List<String> paymentMethods = new ArrayList<>();
        paymentMethods.add("Credit Card");
        paymentMethods.add("Bank Transfer");
        paymentMethodComboBox.getItems().addAll(paymentMethods);

        // Buttons
        Button donateButton = new Button("Donate");
        donateButton.getStyleClass().add("button");
        Button backButton = new Button("Back");
        backButton.getStyleClass().addAll("button", "secondary-button");

        // Error Label
        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.getStyleClass().add("error-message");

        // Layout for buttons
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(donateButton, backButton);

        // GridPane Layout
        grid.add(orgLabel, 0, 0);
        grid.add(organizationComboBox, 1, 0);
        grid.add(amountLabel, 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(paymentLabel, 0, 2);
        grid.add(paymentMethodComboBox, 1, 2);
        grid.add(hbBtn, 1, 3);
        grid.add(errorLabel, 1, 4);

        donateButton.setOnAction(event -> handleDonation(user));
        backButton.setOnAction(event -> navigationManager.goBack());

        this.setUserData("Donasi");
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void handleDonation(User user) {
        String organization = organizationComboBox.getValue();
        String amountString = amountField.getText();
        String paymentMethod = paymentMethodComboBox.getValue();

        // Input Validation
        if (organization == null || amountString.isEmpty() || paymentMethod == null) {
            errorLabel.setText("Please fill in all fields.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountString);
            if (amount <= 0) {
                errorLabel.setText("Amount must be positive.");
                return;
            }

            DonationController donationController = new DonationController();
            boolean success = donationController.processDonation(user, organization, amount, paymentMethod);

            if (success) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Donation Successful");
                alert.setHeaderText(null);
                alert.setContentText("Thank you for your donation!");
                alert.showAndWait();
                navigationManager.goBack();
            } else {
                errorLabel.setText("Donation failed. Please try again.");
            }
        } catch (NumberFormatException e) {
            errorLabel.setText("Invalid amount format.");
        }
    }
}
