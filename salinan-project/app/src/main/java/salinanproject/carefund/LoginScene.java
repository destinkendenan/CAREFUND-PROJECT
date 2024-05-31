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

public class LoginScene extends Parent {

    protected LoginScene(Stage stage) {
        this.primaryStage = stage;
    }

    protected Scene createScene() {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        Button loginButton = new Button("Login");
        Button backButton = new Button("Back");

        // Tambahkan style class ke elemen-elemen UI
        usernameLabel.getStyleClass().add("label");
        usernameField.getStyleClass().add("text-field");
        passwordLabel.getStyleClass().add("label");
        passwordField.getStyleClass().add("text-field");
        loginButton.getStyleClass().add("button");
        backButton.getStyleClass().add("button");

        // homeScene = new HomeScene(primaryStage).createScene();
        

        backButton.setOnAction(e -> {
            HomeScene home = new HomeScene(primaryStage);
                homeScene = home.createScene();
                String cssPath = getClass().getResource("/style.css").toExternalForm();

                Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);
            
                Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);
                homeScene.getStylesheets().add(cssPath);
                primaryStage.setScene(homeScene);
        });

        // Logika validasi login
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (cf.login(username, password)) {
            
                MainScene main = new MainScene(primaryStage);
                mainScene = main.createScene();
                String cssPath = getClass().getResource("/style.css").toExternalForm();

                Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);
            
                Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);
                mainScene.getStylesheets().add(cssPath);
                primaryStage.setScene(mainScene); // Arahkan ke mainScene jika login valid
            } else {
                // Tampilkan pesan error (misalnya, menggunakan Alert)
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password");
                alert.showAndWait();
            }
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("grid-pane");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(backButton, 0, 2);

        loginScene = new Scene(grid, 300, 250);
        return loginScene;
    }

}
