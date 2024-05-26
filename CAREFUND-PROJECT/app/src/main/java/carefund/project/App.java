package carefund.project;

import javafx.application.Application;
import javafx.stage.Stage;
import carefund.navigation.NavigationManager;
import main.java.carefund.views.WelcomeScene;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        NavigationManager navigationManager = new NavigationManager(primaryStage);
        WelcomeScene welcomeScene = new WelcomeScene(navigationManager);
        navigationManager.navigateTo(welcomeScene);
        primaryStage.setTitle("Welcome to Carefund");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}