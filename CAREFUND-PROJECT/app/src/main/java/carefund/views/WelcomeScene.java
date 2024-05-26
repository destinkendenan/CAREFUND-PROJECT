package main.java.carefund.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import carefund.navigation.NavigationManager;
import carefund.views.RegisterScene;
import carefund.views.LoginScene;

public class WelcomeScene extends Scene {

    public WelcomeScene(NavigationManager navigationManager) {
        super(new VBox(20), 400, 300);

        VBox vbox = (VBox) getRoot();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25));
        vbox.getStyleClass().add("welcome-scene");

        Image backgroundImage = new Image(getClass().getResource("/welcome-background.jpg").toExternalForm());
        BackgroundImage bgImg = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false));
        vbox.setBackground(new Background(bgImg));

        Label welcomeLabel = new Label("Welcome to Carefund!");
        welcomeLabel.setFont(new Font("Arial", 24));
        welcomeLabel.getStyleClass().add("welcome-title");

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        loginButton.setOnAction(event -> {
            LoginScene loginScene = new LoginScene(navigationManager);
            loginScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            navigationManager.navigateTo(loginScene);
        });

        registerButton.setOnAction(event -> {
            RegisterScene registerScene = new RegisterScene(navigationManager);
            registerScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            navigationManager.navigateTo(registerScene);
        });

        vbox.getChildren().addAll(welcomeLabel, loginButton, registerButton);

        this.setUserData("Welcome");
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }
}
