package carefund.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.java.carefund.models.User;
import carefund.navigation.NavigationManager;
import main.java.carefund.views.DonasiScene;
import main.java.carefund.views.RiwayatDonasiScene;
import main.java.carefund.views.ProfilUserScene;

public class HomeScreen extends Scene {

    private final User currentUser;
    private final NavigationManager navigationManager;

    public HomeScreen(NavigationManager navigationManager, User user) {
        super(new VBox(20), 400, 300);
        this.currentUser = user;
        this.navigationManager = navigationManager;

        VBox vbox = (VBox) getRoot();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25));
        vbox.getStyleClass().add("container");

        Label welcomeLabel = new Label("Welcome, " + user.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", 18));
        welcomeLabel.getStyleClass().add("welcome-message");

        Button donasiButton = new Button("Donasi");
        donasiButton.setOnAction(event -> {
            DonasiScene donasiScene = new DonasiScene(navigationManager, currentUser);
            donasiScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            navigationManager.navigateTo(donasiScene);
        });
        donasiButton.getStyleClass().add("button");

        Button riwayatButton = new Button("Riwayat Donasi");
        riwayatButton.setOnAction(event -> {
            RiwayatDonasiScene riwayatScene = new RiwayatDonasiScene(navigationManager, currentUser);
            riwayatScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            navigationManager.navigateTo(riwayatScene);
        });
        riwayatButton.getStyleClass().add("button");

        Button profilButton = new Button("Profil");
        profilButton.setOnAction(event -> {
            ProfilUserScene profilScene = new ProfilUserScene(navigationManager, currentUser);
            profilScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            navigationManager.navigateTo(profilScene);
        });
        profilButton.getStyleClass().add("button");

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(event -> navigationManager.goBack());
        logoutButton.getStyleClass().add("button");

        vbox.getChildren().addAll(welcomeLabel, donasiButton, riwayatButton, profilButton, logoutButton);

        this.setUserData("Home");
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }
}
