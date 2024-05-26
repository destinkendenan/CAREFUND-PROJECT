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

import java.util.regex.Pattern;

public class RegisterScene extends Scene {
    private TextField usernameField;
    private TextField emailField;
    private PasswordField passwordField;
    private Label errorLabel;
    private final NavigationManager navigationManager;

    public RegisterScene(NavigationManager navigationManager) {
        super(new GridPane(), 400, 350);

        this.navigationManager = navigationManager;

        GridPane grid = (GridPane) getRoot();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("container");

        // Labels and Fields (with styling)
        Label usernameLabel = new Label("Username:");
        usernameLabel.getStyleClass().add("label");
        usernameField = new TextField();
        usernameField.getStyleClass().add("text-field");

        Label emailLabel = new Label("Email:");
        emailLabel.getStyleClass().add("label");
        emailField = new TextField();
        emailField.getStyleClass().add("text-field");

        Label passwordLabel = new Label("Password:");
        passwordLabel.getStyleClass().add("label");
        passwordField = new PasswordField();
        passwordField.getStyleClass().add("text-field");

        // Register and Back Buttons
        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("button");
        Button backButton = new Button("Back");
        backButton.getStyleClass().addAll("button", "secondary-button");

        // Error Label
        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.getStyleClass().add("error-message");

        // Layout for buttons
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(registerButton, backButton);

        // Layout GridPane
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(hbBtn, 1, 3);
        grid.add(errorLabel, 1, 4);

        // Event Handling
        registerButton.setOnAction(event -> handleRegister());
        backButton.setOnAction(event -> navigationManager.goBack());

        this.setUserData("Register");
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String plainTextPassword = passwordField.getText();

        if (isValidInput(username, email, plainTextPassword)) {

            User newUser = new User(username, email);
            boolean success = UserData.registerUser(newUser);
            if (success) {
                // ... (clear input fields and show success message)
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration Successful");
                alert.setHeaderText(null);
                alert.setContentText("You have successfully registered! Please log in.");
                alert.showAndWait();
                navigationManager.navigateTo(new LoginScene(navigationManager));
            } else {
                errorLabel.setText("Username or email already exists.");
            }
        } else {
            errorLabel.setText("Invalid input. Please check your input.");
        }
    }

    private boolean isValidInput(String username, String email, String password) {
        errorLabel.setText(""); // Clear any previous errors

        // Username Validation
        if (username.isEmpty()) {
            errorLabel.setText("Username is required.");
            return false;
        } else if (!username.matches("^[a-zA-Z0-9_]{3,15}$")) { // Alphanumeric and underscore, 3-15 chars
            errorLabel.setText("Username must be 3-15 characters and contain only letters, numbers, and underscores.");
            return false;
        } else if (username.contains(" ")) { // Check for spaces
            errorLabel.setText("Username cannot contain spaces.");
            return false;
        }

        // Email Validation
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);
        if (email.isEmpty()) {
            errorLabel.setText("Email is required.");
            return false;
        } else if (!emailPattern.matcher(email).matches()) {
            errorLabel.setText("Invalid email format.");
            return false;
        }

        // Password Validation
        if (password.isEmpty()) {
            errorLabel.setText("Password is required.");
            return false;
        } else if (password.length() < 8) {
            errorLabel.setText("Password must be at least 8 characters.");
            return false;
        } else if (!password.matches(".*[A-Z].*")) { // At least one uppercase
            errorLabel.setText("Password must contain at least one uppercase letter.");
            return false;
        } else if (!password.matches(".*[a-z].*")) { // At least one lowercase
            errorLabel.setText("Password must contain at least one lowercase letter.");
            return false;
        } else if (!password.matches(".*\\d.*")) { // At least one digit
            errorLabel.setText("Password must contain at least one digit.");
            return false;
        } else if (password.contains(" ")) { // Check for spaces
            errorLabel.setText("Password cannot contain spaces.");
            return false;
        }

        return true;
    }

}
