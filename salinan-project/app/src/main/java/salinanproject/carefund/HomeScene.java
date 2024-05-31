package salinanproject.carefund;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomeScene extends Parent {

    public HomeScene(Stage stage) {
        this.primaryStage = stage;
    }

    @Override
    protected Scene createScene() {
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Label titleLabel = new Label("CAREFUND APP");

        Font comicSansFont = Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 18);
        loginButton.setFont(comicSansFont);
        registerButton.setFont(comicSansFont);

        titleLabel.getStyleClass().add("label-title");
        loginButton.getStyleClass().add("button");
        registerButton.getStyleClass().add("button");

        loginScene = new LoginScene(primaryStage).createScene();
        registerScene = new RegisterScene(primaryStage).createScene();

        String cssPath = getClass().getResource("/style.css").toExternalForm();

        Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);
        titleLabel.getStyleClass().add("carefund-app-title");
        Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);

        loginScene.getStylesheets().add(cssPath);
        registerScene.getStylesheets().add(cssPath);

        loginButton.setOnAction(e -> primaryStage.setScene(loginScene));
        registerButton.setOnAction(e -> primaryStage.setScene(registerScene));

        VBox layout = new VBox(20, titleLabel, loginButton, registerButton);
        layout.setAlignment(Pos.CENTER);
        homeScene = new Scene(layout, 300, 250);
        return homeScene;
    }
}
