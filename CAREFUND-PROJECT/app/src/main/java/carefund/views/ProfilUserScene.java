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
import main.java.carefund.data.UserData;
import java.util.regex.Pattern;

public class ProfilUserScene extends Scene {

    private TextField usernameField;
    private TextField emailField;
    private PasswordField passwordField;
    private Label errorLabel;
    private boolean isEditing = false;

    private final NavigationManager navigationManager;
    private final User currentUser;

    public ProfilUserScene(NavigationManager navigationManager, User user) {
        super(new GridPane(), 400, 300);
        this.navigationManager = navigationManager;
        this.currentUser = user;

        GridPane grid = (GridPane) getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("container");

        Label usernameLabel = new Label("Username:");
        usernameLabel.getStyleClass().add("label");
        usernameField = new TextField(user.getUsername());
        usernameField.setEditable(false);
        usernameField.getStyleClass().add("text-field");

        Label emailLabel = new Label("Email:");
        emailLabel.getStyleClass().add("label");
        emailField = new TextField(user.getEmail());
        emailField.setEditable(false);
        emailField.getStyleClass().add("text-field");

        Label passwordLabel = new Label("Password:");
        passwordLabel.getStyleClass().add("label");
        passwordField = new PasswordField();
        passwordField.setEditable(false);
        passwordField.getStyleClass().add("text-field");

        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.getStyleClass().add("error-message");

        // Buttons
        Button editButton = new Button("Edit");
        editButton.setOnAction(event -> toggleEditMode());
        editButton.getStyleClass().add("button");

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> navigationManager.goBack());
        backButton.getStyleClass().addAll("button", "secondary-button");

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(editButton, backButton);

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(hbBtn, 1, 3);
        grid.add(errorLabel, 1, 4);

        this.setUserData("Profil User");
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void toggleEditMode() {
        isEditing = !isEditing;
        usernameField.setEditable(isEditing);
        emailField.setEditable(isEditing);
        passwordField.setEditable(isEditing);

        if (isEditing) {
            errorLabel.setText("");
        } else {
            handleSaveProfile();
        }
    }

    private void handleSaveProfile() {
        if (isEditing) {
            String newUsername = usernameField.getText();
            String newEmail = emailField.getText();
            String newPassword = passwordField.getText();

            if (!isValidInput(newUsername, newEmail, newPassword)) {
                return;
            }

            currentUser.setUsername(newUsername);
            currentUser.setEmail(newEmail);
            if (!newPassword.isEmpty()) {
                currentUser.setPassword(newPassword);
            }

            UserData.updateUserProfile(currentUser);

            toggleEditMode();
        }
    }

    private boolean isValidInput(String username, String email, String password) {

        if (username.isEmpty() || email.isEmpty()) {
            errorLabel.setText("All fields are required.");
            return false;
        }

        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);
        if (!emailPattern.matcher(email).matches()) {
            errorLabel.setText("Invalid email format.");
            return false;
        }

        if (!password.isEmpty() && password.length() < 6) {
            errorLabel.setText("Password must be at least 6 characters.");
            return false;
        }

        if (!username.equals(currentUser.getUsername()) && UserData.isUsernameTaken(username)) {
            errorLabel.setText("Username already taken.");
            return false;
        }
        if (!email.equals(currentUser.getEmail()) && UserData.isEmailTaken(email)) {
            errorLabel.setText("Email already taken.");
            return false;
        }

        return true;
    }
}
