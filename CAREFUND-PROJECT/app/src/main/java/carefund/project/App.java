package carefund.project;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import carefund.project.controller.CarefundController;
import carefund.project.model.History;
import carefund.project.model.User;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;

public class App extends Application {

    private Stage primaryStage;
    private Label titleLabel = new Label("CAREFUND APP");
    private Scene homeScene, loginScene, registerScene, mainScene, donationScene, profileScene, historyScene;
    private Label usernameDisplayLabel, emailDisplayLabel;
    CarefundController cf = new CarefundController();
    ObservableList<History> historyData;
    TableView<History> historyTable;

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

        profileButton.setOnAction(e -> {
            User loggedInUser = cf.getLoggedInUser(); // Assuming this method exists
            if (loggedInUser != null) {
                usernameDisplayLabel.setText(loggedInUser.getUsername());
                emailDisplayLabel.setText(loggedInUser.getEmail());
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No user is currently logged in.");
                alert.showAndWait();
                // Optionally, redirect to login scene:
                primaryStage.setScene(loginScene);
            }
            primaryStage.setScene(profileScene);
        });

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
            loadData();
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
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(25, 25, 25, 25));
        layout.getStyleClass().add("grid-pane");

        // Labels to Display User Information
        Label usernameLabel = new Label("Username:");
        usernameDisplayLabel = new Label();
        Label emailLabel = new Label("Email:");
        emailDisplayLabel = new Label();

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> primaryStage.setScene(mainScene));
        backButton.getStyleClass().add("button");

        // Style the Labels
        usernameLabel.getStyleClass().add("label");
        emailLabel.getStyleClass().add("label");
        usernameDisplayLabel.getStyleClass().add("label-value");
        emailDisplayLabel.getStyleClass().add("label-value");

        // Add to layout
        layout.getChildren().addAll(usernameLabel, usernameDisplayLabel, emailLabel, emailDisplayLabel, backButton);

        profileScene = new Scene(layout, 300, 250);
    }

    private void createHistoryScene() {
        Label title = new Label("Riwayat Donasi");
        title.setAlignment(Pos.CENTER);
        title.setStyle("-fx-font-family: Comic Sans MS; -fx-font-size: 25px; -fx-text-fill: #000000");

        Button back = new Button("Back");
        back.setOnAction(e -> primaryStage.setScene(mainScene));
        back.getStyleClass().addAll("back-button");
        
        TableColumn<History, String> yayasanCol = new TableColumn<>("Yayasan");
        yayasanCol.setCellValueFactory(new PropertyValueFactory<>("yayasan"));
        TableColumn<History, Double> nominalCol = new TableColumn<>("Nominal");
        nominalCol.setCellValueFactory(new PropertyValueFactory<>("nominal"));
        TableColumn<History, String> metodeCol = new TableColumn<>("Metode Pembayaran");
        metodeCol.setCellValueFactory(new PropertyValueFactory<>("metode"));
        
        historyTable = new TableView<>();
        historyTable.getColumns().add(yayasanCol);
        historyTable.getColumns().add(nominalCol);
        historyTable.getColumns().add(metodeCol);
        historyTable.getStyleClass().add("history-table");

        DoubleBinding columnwidth = historyTable.widthProperty().divide(3);
        yayasanCol.prefWidthProperty().bind(columnwidth);
        nominalCol.prefWidthProperty().bind(columnwidth);
        metodeCol.prefWidthProperty().bind(columnwidth);

        historyTable.setPrefHeight(900);

        VBox layout = new VBox(10, title, historyTable);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(back);
        mainLayout.setCenter(scrollPane);

        BorderPane.setAlignment(back, Pos.TOP_LEFT);
        BorderPane.setMargin(back, new Insets(10));

        loadData();
        historyScene = new Scene(mainLayout, 600, 900);
    }

    private void loadData() {
        ObservableList<History> history = cf.selectAll2();
        historyTable.setItems(history);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
