package dev.clerdmy.itemhub;

import dev.clerdmy.itemhub.ui.SceneManager;
import dev.clerdmy.itemhub.ui.UIConstants;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class Main extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage stage) throws IOException {

        Group root = new Group();
        Scene scene = new Scene(root, UIConstants.DEFAULT_WIDTH, UIConstants.DEFAULT_HEIGHT);
        stage.setMinWidth(UIConstants.MIN_WIDTH);
        stage.setMinHeight(UIConstants.MIN_HEIGHT);
        stage.setTitle("ItemHub");
        stage.setScene(scene);
        stage.show();
        SceneManager.setStage(stage);
        SceneManager.loadNewScene("/view/signin.fxml");
    }

    public static void main(String[] args) {
        logger.info("Application launched");
        launch();
    }

}