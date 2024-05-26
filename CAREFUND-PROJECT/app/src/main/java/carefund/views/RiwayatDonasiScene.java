package main.java.carefund.views;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import main.java.carefund.models.User;
import carefund.models.Donation;
import carefund.navigation.NavigationManager;
import main.java.carefund.controllers.DonationHistoryController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RiwayatDonasiScene extends Scene {
    private TableView<Donation> donationTableView;
    private Label errorLabel;
    private final NavigationManager navigationManager;
    private final User currentUser;

    public RiwayatDonasiScene(NavigationManager navigationManager, User user) {
        super(new VBox(20), 600, 400); // Adjust size for table view
        this.navigationManager = navigationManager;
        this.currentUser = user;

        VBox vbox = (VBox) getRoot();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25, 25, 25, 25));
        vbox.getStyleClass().add("container");

        Label titleLabel = new Label("Riwayat Donasi");
        titleLabel.setFont(new Font("Arial", 18));
        titleLabel.getStyleClass().add("welcome-title");

        donationTableView = new TableView<>();

        // Table columns
        TableColumn<Donation, String> organizationColumn = new TableColumn<>("Organization");
        organizationColumn.setCellValueFactory(new PropertyValueFactory<>("organization"));

        TableColumn<Donation, Double> amountColumn = new TableColumn<>("Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        TableColumn<Donation, String> paymentMethodColumn = new TableColumn<>("Payment Method");
        paymentMethodColumn.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

        TableColumn<Donation, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getDonationDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        donationTableView.getColumns().addAll(organizationColumn, amountColumn, paymentMethodColumn, dateColumn);
        donationTableView.setEditable(false);

        errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        errorLabel.getStyleClass().add("error-message");

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> navigationManager.goBack());
        backButton.getStyleClass().addAll("button", "secondary-button");

        vbox.getChildren().addAll(titleLabel, donationTableView, errorLabel, backButton);

        loadDonationHistory();

        this.setUserData("Riwayat Donasi");
        this.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    }

    private void loadDonationHistory() {
        DonationHistoryController donationHistoryController = new DonationHistoryController();
        List<Donation> donationHistory = donationHistoryController.getDonationHistory(currentUser);

        if (donationHistory == null || donationHistory.isEmpty()) {
            errorLabel.setText("You have no donation history.");
        } else {
            ObservableList<Donation> donationData = FXCollections.observableArrayList(donationHistory);
            donationTableView.setItems(donationData);
        }
    }
}
