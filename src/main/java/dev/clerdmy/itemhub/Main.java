package dev.clerdmy.itemhub;

import dev.clerdmy.itemhub.ui.SceneManager;
import dev.clerdmy.itemhub.ui.UIConstants;
import dev.clerdmy.itemhub.util.HibernateUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;

public class Main extends Application {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage stage) throws IOException {

        //improve cold start?
        logger.info("Initializing Hibernate");
        HibernateUtil.getSessionFactory();
        logger.info("Hibernate initialized");

        SceneManager.setStage(stage);

        stage.setMinWidth(UIConstants.MIN_WIDTH);
        stage.setMinHeight(UIConstants.MIN_HEIGHT);
        stage.setTitle("ItemHub");
        stage.show();

        SceneManager.showLoginOrMain();
    }

    public static void main(String[] args) {
        logger.info("Application launched");
        launch();
    }

}