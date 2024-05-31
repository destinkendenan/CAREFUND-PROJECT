package salinanproject.carefund;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DonationScene extends Parent {

    protected DonationScene(Stage stage) {
        this.primaryStage = stage;
    }

    protected Scene createScene() {
        // Create NavigationBar
        NavigationBar navigationBar = new NavigationBar(primaryStage, mainScene, donationScene, profileScene,
                historyScene);
        HBox navigationBarBox = navigationBar.createNavigationBar();

        // Labels and other components
        Label yayasanLabel = new Label("Pilih Yayasan:");
        Label nominalLabel = new Label("Nominal Donasi:");
        Label metodeLabel = new Label("Metode Pembayaran:");

        ComboBox<String> yayasanComboBox = new ComboBox<>();
        yayasanComboBox.getItems().addAll(
                "Yayasan A", "Yayasan B", "Yayasan C", "Yayasan Lainnya");
        yayasanComboBox.getSelectionModel().selectFirst();

        TextField nominalField = new TextField();
        nominalField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nominalField.setText(oldValue);
            }
        });

        RadioButton transferButton = new RadioButton("Transfer Bank");
        RadioButton eWalletButton = new RadioButton("E-Wallet");
        RadioButton kartuKreditButton = new RadioButton("Kartu Kredit");
        ToggleGroup metodeGroup = new ToggleGroup();
        transferButton.setToggleGroup(metodeGroup);
        eWalletButton.setToggleGroup(metodeGroup);
        kartuKreditButton.setToggleGroup(metodeGroup);
        transferButton.setSelected(true);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            MainScene main = new MainScene(primaryStage);
            mainScene = main.createScene();
            String cssPath = getClass().getResource("/style.css").toExternalForm();
            Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);
            Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);
            mainScene.getStylesheets().add(cssPath);
            primaryStage.setScene(mainScene);
        });

        // Button to confirm donation
        Button donateButton = new Button("Donasi");
        donateButton.setOnAction(e -> {
            String yayasan = yayasanComboBox.getValue();
            String nominalText = nominalField.getText();
            double nominal = Double.parseDouble(nominalText);
            RadioButton selectedMetode = (RadioButton) metodeGroup.getSelectedToggle();
            String metode = selectedMetode.getText();

            // Donation confirmation logic (call a controller method, display confirmation
            // alert)
            cf.donate(yayasan, nominal, metode);
            loadData();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Donasi Berhasil");
            alert.setHeaderText(null);
            alert.setContentText("Terima kasih atas donasi Anda!");
            alert.showAndWait();
        });

        yayasanLabel.getStyleClass().add("label");
        yayasanComboBox.getStyleClass().add("combo-box");
        nominalLabel.getStyleClass().add("label");
        nominalField.getStyleClass().add("text-field");
        metodeLabel.getStyleClass().add("label");
        donateButton.getStyleClass().add("button");
        backButton.getStyleClass().add("button");

        // GridPane layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("grid-pane");

        // Adding components to GridPane
        grid.add(yayasanLabel, 0, 0);
        grid.add(yayasanComboBox, 1, 0);
        grid.add(nominalLabel, 0, 1);
        grid.add(nominalField, 1, 1);
        grid.add(metodeLabel, 0, 2);
        grid.add(transferButton, 1, 2);
        grid.add(eWalletButton, 1, 3);
        grid.add(kartuKreditButton, 1, 4);
        grid.add(backButton, 0, 5);
        grid.add(donateButton, 1, 5);

        // Set up the main layout with VBox
        VBox layout = new VBox();
        layout.getStyleClass().add("background");
        layout.getChildren().addAll(navigationBarBox, grid);

        donationScene = new Scene(layout, 400, 450); // Adjust size as needed
        return donationScene;
    }
}
