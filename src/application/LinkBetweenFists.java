package src.application;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGL.run;
import static com.almasb.fxgl.dsl.FXGL.spawn;

import java.util.Arrays;
import java.util.EnumSet;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.MenuItem;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import src.model.*;

/**
 * This file is the main class for the program and will be used to execute the
 * game
 * 
 * @author Nicholas Kalar
 * @version 2/29/2020
 */

public class LinkBetweenFists extends GameApplication {

    

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(24 * 64);
        settings.setHeight(16 * 64);
        settings.setTitle("A Link Between Fists");
        settings.setVersion("1.0");
        settings.setAppIcon("icon.png");
        settings.setMenuEnabled(true);
        settings.setEnabledMenuItems(EnumSet.of(MenuItem.EXTRA));
        settings.getCredits().addAll(Arrays.asList(
                "Menus by Nancy Castillejos",
                "Custom Sprites by Nicholas Kalar",
                "Mapping and Collsion by Edwin Hernandez",
                "Gameplay Mechanics by Nancy Castillejos,", 
                "\t\t\tEdwin Hernandez, and Nicholas Kalar",
                "Based on The Legend of Zelda by Nintendo Co., Ltd."
        ));
        settings.setSceneFactory(new SceneFactory() {
            public FXGLMenu newMainMenu() {
                return new LBTMainMenu();
            }
        });

    }

    /**
     * Began adding basic wall collision, still needs to be polished
     * 
     * @author Edwin Hernandez
     * @version 4/1/2020
     */

    private Entity player1;
    private Entity player2;
    private ControllerManager controllers;

    @Override
    protected void onPreInit() {
        FXGL.getSettings().setGlobalSoundVolume(0.5);
        FXGL.getSettings().setGlobalMusicVolume(0.5);
        FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("LOZ_Title.mp3"));

        FXGL.onEvent(GameEvent.PLAYER1_GOT_HIT, this::player1Hit);
        FXGL.onEvent(GameEvent.PLAYER2_GOT_HIT, this::player2Hit);
        FXGL.onEvent(GameEvent.PLAYER3_GOT_HIT, this::player3Hit);
        FXGL.onEvent(GameEvent.PLAYER4_GOT_HIT, this::player4Hit);

    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new ItemFactory());
        FXGL.setLevelFromMap("forest.tmx");
        FXGL.getAudioPlayer().stopMusic(FXGL.getAssetLoader().loadMusic("LOZ_Title.mp3"));
        FXGL.getAudioPlayer().loopMusic(FXGL.getAssetLoader().loadMusic("LOZ_Forest.mp3"));


        player1 = getGameWorld().spawn("player1");
        player2 = getGameWorld().spawn("player2");

        run(() -> spawn("bow", FXGLMath.random(10, 1100), FXGLMath.random(10, 700)), Duration.seconds(5));

        controllers = new ControllerManager();
        controllers.initSDLGamepad();
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.PLAYER1, EntityType.BOW, (player, bow) -> {
            bow.removeFromWorld();
        });
    }

    // ties in the animation class with the inputs from keyboard.
    @Override
    protected void initInput() {
         // player 1
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player1.getComponent(Player1.class).moveRight();
            }
        }, KeyCode.D);

        FXGL.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onActionBegin() {
                // System.out.println("begin");
            }

            @Override
            protected void onAction() {
                player1.getComponent(Player1.class).moveUp();

            }

            @Override
            protected void onActionEnd() {
                // System.out.println("end");

            }
        }, KeyCode.W);

        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player1.getComponent(Player1.class).moveLeft();
            }
        }, KeyCode.A);

        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onActionBegin() {
                System.out.println("begin");
            }

            @Override
            protected void onAction() {
                player1.getComponent(Player1.class).moveDown();
            }

            @Override
            protected void onActionEnd() {
                System.out.println("end");

            }
        }, KeyCode.S);

        FXGL.getInput().addAction(new UserAction("SwordAttack") {
            @Override
            protected void onAction() {
                player1.getComponent(Player1.class).swordAttack();
                FXGL.play("LOZ_Sword_Slash.wav");
            }
        }, KeyCode.E);

        // player2
        FXGL.getInput().addAction(new UserAction("CRight") {
            @Override
            protected void onAction() {
                player2.getComponent(Player2.class).moveRight();

            }
        }, KeyCode.L);

        FXGL.getInput().addAction(new UserAction("CUp") {

            @Override
            protected void onAction() {
                player2.getComponent(Player2.class).moveUp();

            }

        }, KeyCode.I);

        FXGL.getInput().addAction(new UserAction("CLeft") {
            @Override
            protected void onAction() {
                player2.getComponent(Player2.class).moveLeft();
            }
        }, KeyCode.J);

        FXGL.getInput().addAction(new UserAction("CDown") {
            @Override
            protected void onAction() {
                player2.getComponent(Player2.class).moveDown();

            }

        }, KeyCode.K);
    }

    public void player1Hit(GameEvent event) {
        //Add code to take damage

        //remove 1 heart
        //set invicibility
        //remove invicibility after set time
        //play hurt sound
        FXGL.play("LOZ_Link_Hurt.wav");
        //if hearts == 0, die
    }

    public void player2Hit(GameEvent event) {

    }

    public void player3Hit(GameEvent event) {

    }

    public void player4Hit(GameEvent event) {

    }

    /*
     * Controller support
     * @author Edwin Hernandez
     * @version 3/16/20
     */
    @Override
    protected void onUpdate(double tpf) {
        ControllerState currState = controllers.getState(0);

        if (currState.a) {
            System.out.println("currState.a");
            getInput().mockKeyPress(KeyCode.Q);
        }
        if (currState.b) {
            System.out.println("currState.b");
            getInput().mockKeyPress(KeyCode.E);
        }
        if (currState.dpadDown) {
            getInput().mockKeyPress(KeyCode.S);
        } else {
            getInput().mockKeyRelease(KeyCode.S);
        }
        if (currState.dpadLeft) {
            getInput().mockKeyPress(KeyCode.A);
        } else {
            getInput().mockKeyRelease(KeyCode.A);
        }
        if (currState.dpadRight) {
            getInput().mockKeyPress(KeyCode.D);
        } else {
            getInput().mockKeyRelease(KeyCode.D);
        }
        if (currState.dpadUp) {
            getInput().mockKeyPress(KeyCode.W);
        } else {
            getInput().mockKeyRelease(KeyCode.W);
        }
        if (currState.rb) {
            System.out.println("currState.rb");
        }
        if (currState.x) {
            System.out.println("currState.x");
        }
        if (currState.y) {
            System.out.println("currState.y");
        }
        if (currState.start) {
            System.out.println("currState.start");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
// private HashMap<KeyCode, Boolean> keys = new HashMap<>();
// Image image = new
// Image("http://www.zeldagalaxy.com/wp-content/img/sprites/nes/loz/link.png");
// ImageView imageView = new ImageView(image);
// Link link = new Link(imageView);
// static Pane root = new Pane();

// @Override
// public void start(Stage primaryStage) {
// try {
// root.setPrefSize(1280, 720);
// root.getChildren().addAll(link);

// Scene scene = new Scene(root);
// scene.setOnKeyPressed(event->keys.put(event.getCode(),true));
// scene.setOnKeyReleased(event-> {
// keys.put(event.getCode(), false);
// });
// AnimationTimer timer = new AnimationTimer() {
// @Override
// public void handle(long now) {
// update();
// }
// };
// timer.start();
// ViewManager manager = new ViewManager();
// primaryStage = manager.getMainStage();
// primaryStage.setTitle("A Link Between Fists");
// primaryStage.setScene(scene);
// primaryStage.show();
// } catch (Exception e) {
// e.printStackTrace();
// }
// }

// public static void main(String args[]) {
// launch(args);
// }

// // input handling from a keyboard.
// public void update() {
// if (isPressed(KeyCode.UP)){
// link.animation.play();
// link.animation.setOffsetY(64);
// link.moveY(-2);
// } if (isPressed(KeyCode.DOWN)){
// link.animation.play();
// link.animation.setOffsetY(0);
// link.moveY(2);
// } if (isPressed(KeyCode.RIGHT)) {
// link.animation.play();
// link.animation.setOffsetY(92);
// link.moveX(2);
// } if (isPressed(KeyCode.LEFT)) {
// link.animation.play();
// link.animation.setOffsetY(31);
// link.moveX(-2);
// }
// }

// public boolean isPressed(KeyCode key) {
// return keys.getOrDefault(key, false);
// }
// }