package carefund.views;

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
import main.java.carefund.data.UserData;
import main.java.carefund.views.ProfilUserScene;

public class LoginScene extends Scene {

    private TextField usernameField;
    private PasswordField passwordField;
    private Label errorLabel;
    private final NavigationManager navigationManager;

    public LoginScene(NavigationManager navigationManager) {
        super(new GridPane(), 400, 300);

        this.navigationManager = navigationManager;

        GridPane grid = (GridPane) getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("container");

        Label usernameLabel = new Label("Username:");
        usernameLabel.getStyleClass().add("label");
        usernameField = new TextField();
        usernameField.getStyleClass().add("text-field");

        Label passwordLabel = new Label("Password:");
        passwordLabel.getStyleClass().add("label");
        passwordField = new PasswordField();
        passwordField.getStyleClass().add("text-field");

        Button loginButton = new Button("Login");
        loginButton.getStyleClass().add("button");
        Button backButton = new Button("Back");
        backButton.getStyleClass().addAll("button", "secondary-button");

        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.getStyleClass().add("error-message");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(loginButton, backButton);

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(hbBtn, 1, 2);
        grid.add(errorLabel, 1, 3);

        loginButton.setOnAction(event -> handleLogin());
        backButton.setOnAction(event -> navigationManager.goBack());

        this.setUserData("Login");
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        User user = UserData.authenticate(username, password);
        if (user != null) {

            navigationManager.navigateTo(new HomeScreen(navigationManager, user));
        } else {
            errorLabel.setText("Invalid username or password");
        }
    }
}
