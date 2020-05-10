package src.application;

import src.model.*;
import src.view.*;
import java.util.Arrays;
import java.util.EnumSet;
import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;
import static com.almasb.fxgl.dsl.FXGL.onCollisionBegin;
import static com.almasb.fxgl.dsl.FXGL.run;
import static com.almasb.fxgl.dsl.FXGL.spawn;
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

    private Entity player1, player2, player3, player4;
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
        player3 = getGameWorld().spawn("player3");
        player4 = getGameWorld().spawn("player4");

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
            protected void onAction() {
                player1.getComponent(Player1.class).moveUp();

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
            protected void onAction() {
                player1.getComponent(Player1.class).moveDown();
            }
        }, KeyCode.S);

        FXGL.getInput().addAction(new UserAction("P1SwordAttack") {
            @Override
            protected void onAction() {
                player1.getComponent(Player1.class).swordAttack();
                FXGL.play("LOZ_Sword_Slash.wav");
            }
        }, KeyCode.E);

        // player2
        FXGL.getInput().addAction(new UserAction("P2Right") {
            @Override
            protected void onAction() {
                player2.getComponent(Player2.class).moveRight();

            }
        }, KeyCode.L);

        FXGL.getInput().addAction(new UserAction("P2Up") {

            @Override
            protected void onAction() {
                player2.getComponent(Player2.class).moveUp();

            }

        }, KeyCode.I);

        FXGL.getInput().addAction(new UserAction("P2Left") {
            @Override
            protected void onAction() {
                player2.getComponent(Player2.class).moveLeft();
            }
        }, KeyCode.J);

        FXGL.getInput().addAction(new UserAction("P2Down") {
            @Override
            protected void onAction() {
                player2.getComponent(Player2.class).moveDown();

            }

        }, KeyCode.K);

        FXGL.getInput().addAction(new UserAction("P2SwordAttack") {
            @Override
            protected void onAction() {
                player2.getComponent(Player2.class).swordAttack();
                FXGL.play("LOZ_Sword_Slash.wav");
            }
        }, KeyCode.O);

        // player3
        FXGL.getInput().addAction(new UserAction("P3Right") {
            @Override
            protected void onAction() {
                player3.getComponent(Player3.class).moveRight();

            }
        }, KeyCode.H);

        FXGL.getInput().addAction(new UserAction("P3Up") {

            @Override
            protected void onAction() {
                player3.getComponent(Player3.class).moveUp();

            }

        }, KeyCode.T);

        FXGL.getInput().addAction(new UserAction("P3Left") {
            @Override
            protected void onAction() {
                player3.getComponent(Player3.class).moveLeft();
            }
        }, KeyCode.F);

        FXGL.getInput().addAction(new UserAction("P3Down") {
            @Override
            protected void onAction() {
                player3.getComponent(Player3.class).moveDown();

            }
        }, KeyCode.G);

        FXGL.getInput().addAction(new UserAction("P3SwordAttack") {
            @Override
            protected void onAction() {
                player3.getComponent(Player3.class).swordAttack();
                FXGL.play("LOZ_Sword_Slash.wav");
            }
        }, KeyCode.Y);

        // player4
        FXGL.getInput().addAction(new UserAction("P4Right") {
            @Override
            protected void onAction() {
                player4.getComponent(Player4.class).moveRight();

            }
        }, KeyCode.NUMPAD6);

        FXGL.getInput().addAction(new UserAction("P4Up") {
            @Override
            protected void onAction() {
                player4.getComponent(Player4.class).moveUp();

            }
        }, KeyCode.NUMPAD8);

        FXGL.getInput().addAction(new UserAction("P4Left") {
            @Override
            protected void onAction() {
                player4.getComponent(Player4.class).moveLeft();
            }
        }, KeyCode.NUMPAD4);

        FXGL.getInput().addAction(new UserAction("P4Down") {
            @Override
            protected void onAction() {
                player4.getComponent(Player4.class).moveDown();

            }

        }, KeyCode.NUMPAD5);

        FXGL.getInput().addAction(new UserAction("P4SwordAttack") {
            @Override
            protected void onAction() {
                player4.getComponent(Player4.class).swordAttack();
                FXGL.play("LOZ_Sword_Slash.wav");
            }
        }, KeyCode.NUMPAD9);
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