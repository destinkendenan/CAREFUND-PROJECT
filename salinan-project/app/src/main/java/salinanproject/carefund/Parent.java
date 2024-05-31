package salinanproject.carefund;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import salinanproject.carefund.controller.CarefundController;
import salinanproject.carefund.model.History;
import salinanproject.carefund.model.UserProfile;

public abstract class Parent {
    protected Stage primaryStage;
    protected Scene homeScene, loginScene, registerScene, mainScene, donationScene, profileScene, historyScene;
    protected Label usernameDisplayLabel, emailDisplayLabel;
    TableView<History> historyTable = new TableView<>();
    CarefundController cf = new CarefundController();

    protected abstract Scene createScene();
    
    void loadData() {
        ObservableList<History> history = cf.selectAll2();
        historyTable.setItems(history);
    }

    void loadUserData() {
        ObservableList<UserProfile> users = cf.selectAll();
        if (!users.isEmpty()) {
            UserProfile user = users.get(0);
            usernameDisplayLabel.setText(user.getUsername());
            emailDisplayLabel.setText(user.getEmail());
        } else {
            usernameDisplayLabel.setText("No user found");
            emailDisplayLabel.setText("No email found");
        }
    }

} 
