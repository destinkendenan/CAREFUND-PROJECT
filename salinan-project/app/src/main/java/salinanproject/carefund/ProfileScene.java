package salinanproject.carefund;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProfileScene extends Parent {

    protected ProfileScene(Stage stage) {
        this.primaryStage = stage;
    }

    protected Scene createScene() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25, 25, 25, 25));
        layout.getStyleClass().add("grid-pane");

        // Labels to Display User Information
        Label usernameLabel = new Label("Username:");
        usernameDisplayLabel = new Label();
        Label emailLabel = new Label("Email:");
        emailDisplayLabel = new Label();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            MainScene main = new MainScene(primaryStage);
                mainScene = main.createScene();
                String cssPath = getClass().getResource("/style.css").toExternalForm();

                Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);
            
                Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);
                mainScene.getStylesheets().add(cssPath);
                primaryStage.setScene(mainScene);
        });
        backButton.getStyleClass().add("button");

        // Style the Labels
        usernameLabel.getStyleClass().add("label");
        emailLabel.getStyleClass().add("label");
        usernameDisplayLabel.getStyleClass().add("label-value");
        emailDisplayLabel.getStyleClass().add("label-value");

        // Add to layout
        layout.getChildren().addAll(usernameLabel, usernameDisplayLabel, emailLabel, emailDisplayLabel, backButton);

        loadUserData();
        profileScene = new Scene(layout, 300, 250);
        return profileScene;
    }
}
