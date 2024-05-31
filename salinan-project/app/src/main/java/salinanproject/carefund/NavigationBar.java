package salinanproject.carefund;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class NavigationBar {
    private Stage primaryStage;
    private Scene mainScene, donationScene, profileScene, historyScene;

    public NavigationBar(Stage primaryStage, Scene mainScene, Scene donationScene, Scene profileScene,
            Scene historyScene) {
        this.primaryStage = primaryStage;
        this.mainScene = mainScene;
        this.donationScene = donationScene;
        this.profileScene = profileScene;
        this.historyScene = historyScene;
    }

    public HBox createNavigationBar() {
        HBox navigationBar = new HBox();
        navigationBar.setPadding(new Insets(11, 25, 0, 25));
        navigationBar.getStyleClass().add("navigation");

        // Logo
        ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/image/logo.png")));
        logo.setFitWidth(200); // Atur lebar logo
        logo.setFitHeight(75); // Atur tinggi logo
        logo.getStyleClass().add("logo");

        Label carefundLabel = new Label("CAREFUND");
        carefundLabel.getStyleClass().add("carefund");
        HBox.setMargin(carefundLabel, new Insets(0, 0, 0, 80));

        HBox itemsBox = new HBox();
        itemsBox.setAlignment(Pos.CENTER_RIGHT);
        itemsBox.setSpacing(30);
        itemsBox.getStyleClass().add("items");

        Label donationLabel = new Label("Donation");
        donationLabel.getStyleClass().addAll("donation", "button");
        donationLabel.setOnMouseClicked(e -> primaryStage.setScene(donationScene));

        Label historyLabel = new Label("History");
        historyLabel.getStyleClass().addAll("history", "button");
        historyLabel.setOnMouseClicked(e -> primaryStage.setScene(historyScene));

        Label profileLabel = new Label("Profile");
        profileLabel.getStyleClass().addAll("profile", "button");
        profileLabel.setOnMouseClicked(e -> primaryStage.setScene(profileScene));

        HBox logoutBox = new HBox();
        logoutBox.setAlignment(Pos.CENTER_RIGHT);
        logoutBox.getChildren().add(createLogoutButton());
        HBox.setHgrow(logoutBox, Priority.ALWAYS);

        itemsBox.getChildren().addAll(donationLabel, historyLabel, profileLabel);
        navigationBar.getChildren().addAll(logo, carefundLabel, itemsBox, logoutBox);

        return navigationBar;
    }

    private Label createLogoutButton() {
        Label logoutLabel = new Label("Logout");
        logoutLabel.getStyleClass().addAll("logout", "button");
        logoutLabel.setOnMouseClicked(e -> {
            primaryStage.setScene(mainScene); // Mengarahkan ke homescene saat logout
            System.out.println("Logged out!");
        });
        return logoutLabel;
    }
}
