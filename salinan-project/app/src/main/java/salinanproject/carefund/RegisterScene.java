package salinanproject.carefund;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RegisterScene extends Parent {

    protected RegisterScene(Stage stage) {
        this.primaryStage = stage;
    }

    protected Scene createScene() {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        Button registerButton = new Button("Register");
        Button backButton = new Button("Back");

        // Tambahkan style class ke elemen-elemen UI
        usernameLabel.getStyleClass().add("label");
        usernameField.getStyleClass().add("text-field");
        emailLabel.getStyleClass().add("label");
        emailField.getStyleClass().add("text-field");
        passwordLabel.getStyleClass().add("label");
        passwordField.getStyleClass().add("text-field");
        registerButton.getStyleClass().add("button");
        backButton.getStyleClass().add("button");

        backButton.setOnAction(e -> {
            HomeScene home = new HomeScene(primaryStage);
            homeScene = home.createScene();
            String cssPath = getClass().getResource("/style.css").toExternalForm();

            Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);

            Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);
            homeScene.getStylesheets().add(cssPath);
            primaryStage.setScene(homeScene);
        });

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText(null);
                alert.setContentText("Input is empty");
                alert.showAndWait();
                return;
            }
            if (cf.cekUser(username)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText(null);
                alert.setContentText("Username already exists");
                alert.showAndWait();
                return;
            }
            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (!email.matches(emailRegex)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid email");
                alert.showAndWait();
                return;
            }
            cf.register(username, email, password);

            UserSession.getInstance().setEmail(email);

            LoginScene login = new LoginScene(primaryStage);
            loginScene = login.createScene();
            String cssPath = getClass().getResource("/style.css").toExternalForm();

            Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);

            Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);
            loginScene.getStylesheets().add(cssPath);
            primaryStage.setScene(loginScene);

        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("grid-pane");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(registerButton, 1, 3);
        grid.add(backButton, 0, 3);

        registerScene = new Scene(grid, 350, 300);
        return registerScene;
    }
}
