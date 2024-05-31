package salinanproject.carefund;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import salinanproject.carefund.model.User;

public class MainScene extends Parent  {

    protected MainScene(Stage stage) {
        this.primaryStage = stage;
    }

    protected Scene createScene() {
        Button donationButton = new Button("Donation");
        Button profileButton = new Button("Profile");
        Button historyButton = new Button("History");

        donationScene = new DonationScene(primaryStage).createScene();
        profileScene = new ProfileScene(primaryStage).createScene();
        historyScene = new HistoryScene(primaryStage).createScene();

        String cssPath = getClass().getResource("/style.css").toExternalForm();

        Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);
        // titleLabel.getStyleClass().add("carefund-app-title");
        Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);
            
        profileScene.getStylesheets().add(cssPath);
        donationScene.getStylesheets().add(cssPath);
        historyScene.getStylesheets().add(cssPath);

        donationButton.setOnAction(e -> primaryStage.setScene(donationScene));

        profileButton.setOnAction(e -> {
            User loggedInUser = cf.getLoggedInUser(); // Assuming this method exists
            if (loggedInUser != null) {
                usernameDisplayLabel.setText(loggedInUser.getUsername());
                emailDisplayLabel.setText(loggedInUser.getEmail());
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No user is currently logged in.");
                alert.showAndWait();
                // Optionally, redirect to login scene:
                primaryStage.setScene(loginScene);
            }
            primaryStage.setScene(profileScene);
        });

        historyButton.setOnAction(e -> primaryStage.setScene(historyScene));

        donationButton.getStyleClass().add("button");
        profileButton.getStyleClass().add("button");
        historyButton.getStyleClass().add("button");

        VBox layout = new VBox(20, donationButton, profileButton, historyButton);
        layout.setAlignment(Pos.CENTER);
        mainScene = new Scene(layout, 300, 250);
        return mainScene;
    }
}
