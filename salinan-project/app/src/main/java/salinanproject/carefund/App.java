package salinanproject.carefund;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {
    private Scene homeScene, loginScene, registerScene, mainScene, donationScene, profileScene, historyScene;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Aplikasi");

        HomeScene home = new HomeScene(primaryStage);
        LoginScene login = new LoginScene(primaryStage);
        RegisterScene regis = new RegisterScene(primaryStage);
        MainScene main = new MainScene(primaryStage);
        DonationScene donation = new DonationScene(primaryStage);
        profileScene = new ProfileScene(primaryStage).createScene();

        HistoryScene history = new HistoryScene(primaryStage);

        homeScene = home.createScene();
        loginScene = login.createScene();
        registerScene = regis.createScene();
        mainScene = main.createScene();
        donationScene = donation.createScene();
        profileScene = new ProfileScene(primaryStage).createScene();

        historyScene = history.createScene();

        // Tambahkan CSS ke semua scene
        String cssPath = getClass().getResource("/style.css").toExternalForm();

        Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);
        Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);

        homeScene.getStylesheets().add(cssPath);
        loginScene.getStylesheets().add(cssPath);
        registerScene.getStylesheets().add(cssPath);
        mainScene.getStylesheets().add(cssPath);
        donationScene.getStylesheets().add(cssPath);
        profileScene.getStylesheets().add(cssPath);
        historyScene.getStylesheets().add(cssPath);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());
        primaryStage.setMaximized(true);
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
