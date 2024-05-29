package carefund.project;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import carefund.project.controller.CarefundController;
import carefund.project.model.History;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;

public class App extends Application {

    private Stage primaryStage;
    private Label titleLabel = new Label("CAREFUND APP");
    private Scene homeScene, loginScene, registerScene, mainScene, donationScene, profileScene, historyScene;
    CarefundController cf = new CarefundController();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Aplikasi");

        createHomeScene();
        createLoginScene();
        createRegisterScene();
        createMainScene();
        createDonationScene();
        createProfileScene();
        createHistoryScene();

        // Tambahkan CSS ke semua scene
        String cssPath = getClass().getResource("/style.css").toExternalForm();

        Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);
        titleLabel.getStyleClass().add("carefund-app-title");
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

    private void createHomeScene() {
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Label titleLabel = new Label("CAREFUND APP");

        Font comicSansFont = Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 18);
        loginButton.setFont(comicSansFont);
        registerButton.setFont(comicSansFont);

        titleLabel.getStyleClass().add("label-title");
        loginButton.getStyleClass().add("button");
        registerButton.getStyleClass().add("button");

        loginButton.setOnAction(e -> primaryStage.setScene(loginScene));
        registerButton.setOnAction(e -> primaryStage.setScene(registerScene));

        VBox layout = new VBox(20, titleLabel, loginButton, registerButton);
        layout.setAlignment(Pos.CENTER);
        homeScene = new Scene(layout, 300, 250);
    }

    private void createLoginScene() {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        Button loginButton = new Button("Login");
        Button backButton = new Button("Back");

        // Tambahkan style class ke elemen-elemen UI
        usernameLabel.getStyleClass().add("label");
        usernameField.getStyleClass().add("text-field");
        passwordLabel.getStyleClass().add("label");
        passwordField.getStyleClass().add("text-field");
        loginButton.getStyleClass().add("button");
        backButton.getStyleClass().add("button");

        backButton.setOnAction(e -> primaryStage.setScene(homeScene));

        // Logika validasi login
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (cf.login(username, password)) {
                primaryStage.setScene(mainScene); // Arahkan ke mainScene jika login valid
            } else {
                // Tampilkan pesan error (misalnya, menggunakan Alert)
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password");
                alert.showAndWait();
            }
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("grid-pane");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(backButton, 0, 2);

        loginScene = new Scene(grid, 300, 250);
    }

    private void createRegisterScene() {
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField();
        Button registerButton = new Button("Register");
        Button backButton = new Button("Back");

        // Tambahkan style class ke elemen-elemen UI
        usernameLabel.getStyleClass().add("label");
        usernameField.getStyleClass().add("text-field");
        emailLabel.getStyleClass().add("label");
        emailField.getStyleClass().add("text-field");
        passwordLabel.getStyleClass().add("label");
        passwordField.getStyleClass().add("text-field");
        registerButton.getStyleClass().add("button");
        backButton.getStyleClass().add("button");

        backButton.setOnAction(e -> primaryStage.setScene(homeScene));

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText(null);
                alert.setContentText("Input is empty");
                alert.showAndWait();
                return;
            }
            if (cf.cekUser(username)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText(null);
                alert.setContentText("Username already exists");
                alert.showAndWait();
                return;
            }
            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            if (!email.matches(emailRegex)) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Register Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid email");
                alert.showAndWait();
                return;
            }
            cf.register(usernameField.getText(), emailField.getText(), passwordField.getText());
            primaryStage.setScene(loginScene);
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("grid-pane");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(registerButton, 1, 3);
        grid.add(backButton, 0, 3);

        registerScene = new Scene(grid, 350, 300);
    }

    private void createMainScene() {
        Button donationButton = new Button("Donation");
        Button profileButton = new Button("Profile");
        Button historyButton = new Button("History");

        donationButton.setOnAction(e -> primaryStage.setScene(donationScene));
        profileButton.setOnAction(e -> primaryStage.setScene(profileScene));
        historyButton.setOnAction(e -> primaryStage.setScene(historyScene));

        donationButton.getStyleClass().add("button");
        profileButton.getStyleClass().add("button");
        historyButton.getStyleClass().add("button");

        VBox layout = new VBox(20, donationButton, profileButton, historyButton);
        layout.setAlignment(Pos.CENTER);
        mainScene = new Scene(layout, 300, 250);
    }

    private void createDonationScene() {
        // Labels
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
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));
        // Button to confirm donation
        Button donateButton = new Button("Donasi");
        donateButton.setOnAction(e -> {
            String yayasan = yayasanComboBox.getValue();
            String nominalText = nominalField.getText();
            double nominal = Double.parseDouble(nominalText);
            RadioButton selectedMetode = (RadioButton) metodeGroup.getSelectedToggle();
            String metode = selectedMetode.getText();

            // logika untuk donation confirmation (call a controller method, display
            // confirmation alert)
            cf.donate(yayasan, nominal, metode);
            Alert alert = new Alert(AlertType.INFORMATION);
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

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.getStyleClass().add("grid-pane");

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

        donationScene = new Scene(grid, 400, 350);
    }

    private void createProfileScene() {
        Label profileLabel = new Label("Profile Scene");
        profileLabel.getStyleClass().add("label");

        VBox layout = new VBox(20, profileLabel);
        layout.setAlignment(Pos.CENTER);
        profileScene = new Scene(layout, 300, 250);
    }

    private void createHistoryScene() {
        TableView<History> historyTable = new TableView<>();
        historyTable.setItems(cf.selectAll2());

        TableColumn<History, String> yayasanCol = new TableColumn<>("Yayasan");
        yayasanCol.setCellValueFactory(new PropertyValueFactory<>("yayasan"));

        TableColumn<History, Double> nominalCol = new TableColumn<>("Nominal");
        nominalCol.setCellValueFactory(new PropertyValueFactory<>("nominal"));

        TableColumn<History, String> metodeCol = new TableColumn<>("Metode Pembayaran");
        metodeCol.setCellValueFactory(new PropertyValueFactory<>("metode"));

        historyTable.getColumns().addAll(yayasanCol, nominalCol, metodeCol);
        historyTable.getStyleClass().add("history-table");

        VBox layout = new VBox(20, historyTable);
        layout.setAlignment(Pos.CENTER);
        historyScene = new Scene(layout, 0, 0);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
