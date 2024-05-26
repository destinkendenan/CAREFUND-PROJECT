package carefund.navigation;

import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Stack;

public class NavigationManager {
    private final Stack<Scene> sceneStack = new Stack<>();
    private final Stage primaryStage;

    public NavigationManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void navigateTo(Scene scene) {
        if (scene != null) {
            sceneStack.push(scene);
            primaryStage.setScene(scene);
            primaryStage.setTitle(getSceneTitle(scene));
        } else {
            System.err.println("Error: Cannot navigate to a null scene.");
        }
    }

    public void goBack() {
        if (!sceneStack.isEmpty()) {
            sceneStack.pop(); // Remove the current scene
            if (!sceneStack.isEmpty()) {
                Scene previousScene = sceneStack.peek(); // Get the previous scene
                primaryStage.setScene(previousScene);
                primaryStage.setTitle(getSceneTitle(previousScene));
            } else {
                // Stack is empty after popping, exit the application
                primaryStage.close();
            }
        } else {
            System.err.println("Error: Cannot go back, navigation stack is empty.");
        }
    }

    private String getSceneTitle(Scene scene) {
        Object userData = scene.getUserData();
        return userData != null ? userData.toString() : "CAREFUND";
    }

    public void navigateToRoot() {
        while (sceneStack.size() > 1) {
            sceneStack.pop();
        }
        primaryStage.setScene(sceneStack.peek());
        primaryStage.setTitle(getSceneTitle(sceneStack.peek()));
    }

    public void clearHistory() {
        sceneStack.clear();
    }
}
