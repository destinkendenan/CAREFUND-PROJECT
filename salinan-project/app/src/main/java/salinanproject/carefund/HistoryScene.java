package salinanproject.carefund;

import javafx.scene.control.TableView;

import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import salinanproject.carefund.model.History;

public class HistoryScene extends Parent {

    protected HistoryScene(Stage stage ) {
        this.primaryStage = stage;
    }
        protected Scene createScene() {
            Label title = new Label("Riwayat Donasi");
            title.setAlignment(Pos.CENTER);
            title.setStyle("-fx-font-family: Comic Sans MS; -fx-font-size: 25px; -fx-text-fill: #000000");
    
            Button back = new Button("Back");
            back.setOnAction(e -> {
            MainScene main = new MainScene(primaryStage);
                mainScene = main.createScene();
                String cssPath = getClass().getResource("/style.css").toExternalForm();

                Font.loadFont(getClass().getResourceAsStream("/fonts/JejuHallasan.ttf"), 64);
            
                Font.loadFont(getClass().getResourceAsStream("/fonts/COMICSANS.ttf"), 12);
                mainScene.getStylesheets().add(cssPath);
                primaryStage.setScene(mainScene);
        });
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
            return historyScene;
        }
}
