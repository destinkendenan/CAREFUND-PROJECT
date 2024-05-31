package salinanproject.carefund;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MainScene extends Parent {

    public MainScene(Stage stage) {
        this.primaryStage = stage;
    }

    @Override
    protected Scene createScene() {
        donationScene = new DonationScene(primaryStage).createScene();
        profileScene = new ProfileScene(primaryStage, mainScene).createScene();

        historyScene = new HistoryScene(primaryStage).createScene();

        String cssPath = getClass().getResource("/style.css").toExternalForm();

        // Load fonts
        Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);
        Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);

        profileScene.getStylesheets().add(cssPath);
        donationScene.getStylesheets().add(cssPath);
        historyScene.getStylesheets().add(cssPath);

        BorderPane root = new BorderPane();
        root.setTop(new NavigationBar(primaryStage, mainScene, donationScene, profileScene, historyScene)
                .createNavigationBar());

        // Tambahkan label "Welcome"
        Label welcomeLabel = new Label("Welcome To Carefund");
        welcomeLabel.getStyleClass().add("welcome-label");
        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        root.setCenter(welcomeLabel);

        // Tambahkan label untuk kutipan
        Label quoteLabel = new Label(
                "Jangan pernah merasa malu ketika hanya mampu memberi sedikit untuk bersedekah, karena selalu ada kebaikan dalam berbagi, tidak peduli seberapa kecil yang kamu berikan.");
        quoteLabel.getStyleClass().add("quote-label");
        BorderPane.setAlignment(quoteLabel, Pos.CENTER);
        BorderPane.setMargin(quoteLabel, new Insets(20, 0, 0, 0)); // Atur margin atas
        root.setBottom(quoteLabel);

        mainScene = new Scene(root, 800, 600);
        mainScene.getStylesheets().add(cssPath);
        return mainScene;
    }
}
