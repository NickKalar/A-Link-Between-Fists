package src.application;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import javafx.scene.input.KeyCode;
import static com.almasb.fxgl.dsl.FXGL.*;
import javafx.util.Duration;
/**
 * This file is the main class for the program and will be used to execute the game
 * @author Nicholas Kalar
 * @version 2/29/2020
 */

public class LinkBetweenFists extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) { 
        settings.setWidth(1280);
        settings.setHeight(720);
        settings.setTitle("A Link Between Fists");
        settings.setVersion("1.0");
    }

    private Entity player;
    private ControllerManager controllers;
    @Override
    protected void initGame() {
        player = FXGL.entityBuilder()
                .type(EntityType.PLAYER) 
                .at(200, 200) //where to spawn (px,px)
                .bbox(new HitBox(BoundingShape.box(70, 60))) //creating a hitbox around the player in order to use collisions
                .with(new AnimationComponent()) //tell entity builder we have created animations for player
                .with(new CollidableComponent(true)) //collisions = true
                .buildAndAttach(); //build and attach to game world

        getGameWorld().addEntityFactory(new ItemFactory());
        run(() -> spawn("bow", FXGLMath.random(10, 1100), FXGLMath.random(10, 700)), Duration.seconds(1));
        
        controllers = new ControllerManager();
        controllers.initSDLGamepad();        
    }

    @Override
    protected void initPhysics() {
        onCollisionBegin(EntityType.PLAYER, EntityType.BOW, (player, bow) -> {
            bow.removeFromWorld();
        });

    }   



    // ties in the animation class with the inputs from keyboard.
    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("Right") {
            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveRight();
            }
        }, KeyCode.D);

        FXGL.getInput().addAction(new UserAction("Up") {
            @Override
            protected void onActionBegin() {
                System.out.println("begin");
            }
            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveUp();
            }
            @Override
            protected void onActionEnd() {
                System.out.println("end");

            }
        }, KeyCode.W);

        FXGL.getInput().addAction(new UserAction("Left") {
            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveLeft();
            }
        }, KeyCode.A);

        FXGL.getInput().addAction(new UserAction("Down") {
            @Override
            protected void onActionBegin() {
                System.out.println("begin");
            }
            @Override
            protected void onAction() {
                player.getComponent(AnimationComponent.class).moveDown();
            }
            @Override
            protected void onActionEnd() {
                System.out.println("end");

            }
        }, KeyCode.S);
    }

    /* Controller support
    * @author Edwin Hernandez
    * @version 3/16/20
    */

    @Override
    protected void onUpdate(double tpf) {
        ControllerState currState = controllers.getState(0);

        if(currState.a) {
            System.out.println("currState.a");
        }
        if(currState.b) {
            System.out.println("currState.b");
        }
        if(currState.dpadDown) {
            getInput().mockKeyPress(KeyCode.S);
        } else {
            getInput().mockKeyRelease(KeyCode.S);
        }
        if(currState.dpadLeft) {
            getInput().mockKeyPress(KeyCode.A);
        } else {
            getInput().mockKeyRelease(KeyCode.A);
        }
        if(currState.dpadRight) {
            getInput().mockKeyPress(KeyCode.D);
        } else {
            getInput().mockKeyRelease(KeyCode.D);
        }
        if(currState.dpadUp) {
            getInput().mockKeyPress(KeyCode.W);
        } else {
            getInput().mockKeyRelease(KeyCode.W);
        }
        if(currState.rb) {
            System.out.println("currState.rb");
        }
        if(currState.x) {
            System.out.println("currState.x");
        }
        if(currState.y) {
            System.out.println("currState.y");
        }
        if(currState.start) {
            System.out.println("currState.start");
        }
    }



    public static void main(String[] args) {
        launch(args);
    }
}
    // private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    // Image image = new Image("http://www.zeldagalaxy.com/wp-content/img/sprites/nes/loz/link.png");
    // ImageView imageView = new ImageView(image);
    // Link link = new Link(imageView);
    // static Pane root = new Pane();
    

    // @Override
    // public void start(Stage primaryStage) {
    //     try {
    //         root.setPrefSize(1280, 720);
    //         root.getChildren().addAll(link);

    //         Scene scene = new Scene(root);
    //         scene.setOnKeyPressed(event->keys.put(event.getCode(),true));
    //         scene.setOnKeyReleased(event-> {
    //             keys.put(event.getCode(), false);
    //         });
    //         AnimationTimer timer = new AnimationTimer() {
    //             @Override
    //             public void handle(long now) {
    //                 update();
    //             }
    //         };
    //         timer.start();
    //         ViewManager manager = new ViewManager();
    //         primaryStage = manager.getMainStage();
    //         primaryStage.setTitle("A Link Between Fists");
    //         primaryStage.setScene(scene);
    //         primaryStage.show();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static void main(String args[]) {
    //     launch(args);
    // }

    // // input handling from a keyboard.
    // public void update() {
    //     if (isPressed(KeyCode.UP)){
    //         link.animation.play();
    //         link.animation.setOffsetY(64);
    //         link.moveY(-2);
    //     } if (isPressed(KeyCode.DOWN)){
    //         link.animation.play();
    //         link.animation.setOffsetY(0);
    //         link.moveY(2);
    //     } if (isPressed(KeyCode.RIGHT)) {
    //         link.animation.play();
    //         link.animation.setOffsetY(92);
    //         link.moveX(2);
    //     } if (isPressed(KeyCode.LEFT)) {
    //         link.animation.play();
    //         link.animation.setOffsetY(31);
    //         link.moveX(-2);
    //     }
    // }

    // public boolean isPressed(KeyCode key) {
    //     return keys.getOrDefault(key, false);
//     }
// }