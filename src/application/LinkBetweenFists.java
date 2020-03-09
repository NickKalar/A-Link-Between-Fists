package src.application;

import java.util.HashMap;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.model.Link;
import src.view.ViewManager;

/**
 * This file is the main class for the program and will be used to execute the game
 * @author Nicholas Kalar
 * @version 2/29/2020
 */

public class LinkBetweenFists extends Application {
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    Image image = new Image("http://www.zeldagalaxy.com/wp-content/img/sprites/nes/loz/link.png");
    ImageView imageView = new ImageView(image);
    Link link = new Link(imageView);
    static Pane root = new Pane();
    

    @Override
    public void start(Stage primaryStage) {
        try {
            root.setPrefSize(1280, 720);
            root.getChildren().addAll(link);

            Scene scene = new Scene(root);
            scene.setOnKeyPressed(event->keys.put(event.getCode(),true));
            scene.setOnKeyReleased(event-> {
                keys.put(event.getCode(), false);
            });
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    update();
                }
            };
            timer.start();
            ViewManager manager = new ViewManager();
            primaryStage = manager.getMainStage();
            primaryStage.setTitle("A Link Between Fists");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        launch(args);
    }

    // input handling from a keyboard.
    public void update() {
        if (isPressed(KeyCode.UP)){
            link.animation.play();
            link.animation.setOffsetY(64);
            link.moveY(-2);
        } else if (isPressed(KeyCode.DOWN)){
            link.animation.play();
            link.animation.setOffsetY(0);
            link.moveY(2);
        } else if (isPressed(KeyCode.RIGHT)) {
            link.animation.play();
            link.animation.setOffsetY(92);
            link.moveX(2);
        } else if (isPressed(KeyCode.LEFT)) {
            link.animation.play();
            link.animation.setOffsetY(31);
            link.moveX(-2);
        }
    }

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
}