package src.application;

import javafx.application.Application;
import javafx.stage.Stage;
import src.view.ViewManager;

/**
 * This file is the main class for the program and will be used to execute the game
 * @author Nicholas Kalar
 * @version 2/29/2020
 */

public class LinkBetweenFists extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            ViewManager manager = new ViewManager();
            primaryStage = manager.getMainStage();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        launch(args);
    }
}