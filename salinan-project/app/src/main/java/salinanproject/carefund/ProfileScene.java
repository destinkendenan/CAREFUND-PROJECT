package salinanproject.carefund;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import salinanproject.carefund.model.UserProfile;

public class ProfileScene extends Parent {

    public ProfileScene(Stage stage) {
        this.primaryStage = stage;
        // this.mainScene = mainScene; // Tambahkan inisialisasi mainScene
    }

    @Override
    protected Scene createScene() {
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25, 25, 25, 25));
        layout.getStyleClass().add("grid-pane");

        Label usernameLabel = new Label("Username:");
        usernameDisplayLabel = new Label();
        Label emailLabel = new Label("Email:");
        emailDisplayLabel = new Label();

        UserProfile currentUser = UserSession.getInstance().getUser();
        if (currentUser != null) {
            usernameDisplayLabel.setText(currentUser.getUsername());
            emailDisplayLabel.setText(currentUser.getEmail());
        } else {
            usernameDisplayLabel.setText("No user found");
            emailDisplayLabel.setText("No email found");
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            MainScene main = new MainScene(primaryStage);
            mainScene = main.createScene();
            String cssPath = getClass().getResource("/style.css").toExternalForm();
            Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);
            Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);
            mainScene.getStylesheets().add(cssPath);
            primaryStage.setScene(mainScene);
        }); // Mengarahkan kembali ke mainScene
        backButton.getStyleClass().add("button");

        usernameLabel.getStyleClass().add("label");
        emailLabel.getStyleClass().add("label");
        usernameDisplayLabel.getStyleClass().add("label-value");
        emailDisplayLabel.getStyleClass().add("label-value");

        layout.getChildren().addAll(usernameLabel, usernameDisplayLabel, emailLabel, emailDisplayLabel, backButton);


        BorderPane root = new BorderPane();
        root.setTop(new NavigationBar(primaryStage, homeScene,  mainScene, donationScene, profileScene, historyScene)
                .createNavigationBar());
        root.setCenter(layout);

        profileScene = new Scene(root, 800, 600);
        String cssPath = getClass().getResource("/style.css").toExternalForm();
        profileScene.getStylesheets().add(cssPath);
        return profileScene;
    }
}

class UserSession {
    private static UserSession instance;
    private UserProfile user;
    private String email;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }

    public UserProfile getUser() {
        return user;
    }

    public static void setInstance(UserSession instance) {
        UserSession.instance = instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}