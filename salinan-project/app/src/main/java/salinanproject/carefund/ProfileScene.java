package salinanproject.carefund;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProfileScene extends Parent {

    public ProfileScene(Stage stage, Scene mainScene) {
        this.primaryStage = stage;
        this.mainScene = mainScene; // Tambahkan inisialisasi mainScene
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

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(mainScene)); // Mengarahkan kembali ke mainScene

        usernameLabel.getStyleClass().add("label");
        emailLabel.getStyleClass().add("label");
        usernameDisplayLabel.getStyleClass().add("label-value");
        emailDisplayLabel.getStyleClass().add("label-value");

        layout.getChildren().addAll(usernameLabel, usernameDisplayLabel, emailLabel, emailDisplayLabel, backButton);

        loadUserData();

        BorderPane root = new BorderPane();
        root.setTop(new NavigationBar(primaryStage, mainScene, donationScene, profileScene, historyScene)
                .createNavigationBar());
        root.setCenter(layout);

        profileScene = new Scene(root, 800, 600);
        String cssPath = getClass().getResource("/style.css").toExternalForm();
        profileScene.getStylesheets().add(cssPath);
        return profileScene;
    }
}
