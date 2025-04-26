package dev.clerdmy.itemhub.ui;

import dev.clerdmy.itemhub.control.SessionManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SceneManager {

    private static final Logger logger = LoggerFactory.getLogger(SceneManager.class);

    private static Stage primaryStage;
    private static Scene mainScene;
    private static final Map<String, Parent> rootMap = new HashMap<>();

    public static void setStage(Stage stage) {
        primaryStage = stage;
        mainScene = new Scene(new Pane(), UIConstants.DEFAULT_WIDTH, UIConstants.DEFAULT_HEIGHT);
        primaryStage.setScene(mainScene);
    }

    private static void loadScene(String fxmlPath) throws IOException {
        logger.info("Loading scene {}", fxmlPath);
        FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
        Parent root = loader.load();
        rootMap.put(fxmlPath, root);
        logger.info("Loaded scene {}", fxmlPath);
    }

    public static void switchTo(String fxmlPath) throws IOException {
        if (!rootMap.containsKey(fxmlPath)) {
            loadScene(fxmlPath);
        }
        mainScene.setRoot(rootMap.get(fxmlPath));
    }

    private static void unloadUselessScenes(String current) {
        Set<String> scenesToUnload = new HashSet<>(rootMap.keySet());
        scenesToUnload.remove(current);

        for (String fxmlPath : scenesToUnload) {
            unloadScene(fxmlPath);
        }
    }

    private static void unloadScene(String fxmlPath) {
        logger.info("Unloading scene {}", fxmlPath);
        rootMap.remove(fxmlPath);
        System.gc();
        logger.info("Unloaded scene {}", fxmlPath);
    }

    public static void showLoginOrMain() throws IOException {
        if (SessionManager.isLogged()) {
            switchTo(UIConstants.MAIN_FXML_PATH);
            unloadUselessScenes(UIConstants.MAIN_FXML_PATH);
        } else {
            switchTo(UIConstants.SIGN_IN_FXML_PATH);
            unloadUselessScenes(UIConstants.SIGN_IN_FXML_PATH);
        }
    }

}