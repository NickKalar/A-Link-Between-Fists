package src.application;

import com.almasb.fxgl.physics.BoundingShape;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.TypeComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.physics.CollisionResult;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.box2d.collision.Collision;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import static src.application.EntityType.*;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * Player 2 support
 * @author Edwin Hernandez
 * @version 4/1/2020
 */
public class Player2 extends Component {

    private int speed = 0;
    private String direction = "none";
    private CellMoveComponent moveComponent;
    private AStarMoveComponent astar;

    private AnimatedTexture texture;
    private AnimationChannel animIdle, animLeft, animRight;
    private AnimationChannel animUp, animDown;

    // creating new animations for specific actions being used in game.
    // how to use: FXGL.image("file name"), sprite frames per row, single sprite
    // width, single sprite height, duration of full animation, start frame and end
    // frame
    // start frame and end frame are the individual sprite frames in sprite sheet
    // starting from index 0. If there are a total of 50 sprites you can access
    // which ever...
    // with the correct index number
    public Player2() {
        animIdle = new AnimationChannel(FXGL.image("link2.png"), 2, 112, 128, Duration.seconds(1), 0, 1);
        animLeft = new AnimationChannel(FXGL.image("link2.png"), 2, 112, 128, Duration.seconds(1), 6, 7);
        animRight = new AnimationChannel(FXGL.image("link2.png"), 2, 112, 128, Duration.seconds(1), 4, 5);
        animUp = new AnimationChannel(FXGL.image("link2.png"), 2, 112, 128, Duration.seconds(1), 2, 3);
        animDown = new AnimationChannel(FXGL.image("link2.png"), 2, 112, 128, Duration.seconds(1), 0, 1);

        texture = new AnimatedTexture(animIdle);
    }
    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
    }
    // on update can be seen as a loop, it is always checking for input
    @Override
    public void onUpdate(double tpf) {
        var x = moveComponent.getCellX();
        var y = moveComponent.getCellY();

        if (speed != 0) {


            switch (direction) {
                case "right":
                    if (texture.getAnimationChannel() != animRight)
                        texture.loopAnimationChannel(animRight);
                    if (astar.getGrid().getRight(x, y).filter(c -> c.getState().isWalkable()).isPresent()) {
                            entity.translateX(speed * tpf);
                            // astar.moveToRightCell();
                        }
                    
                    break;

                case "left":
                    if (texture.getAnimationChannel() != animLeft)
                        texture.loopAnimationChannel(animLeft);
                        if (astar.getGrid().getLeft(x, y).filter(c -> c.getState().isWalkable()).isPresent()) {
                            entity.translateX(speed * tpf);
                        }
                    break;

                case "up":
                    if (texture.getAnimationChannel() != animUp)
                        texture.loopAnimationChannel(animUp);
                    if (astar.getGrid().getUp(x, y).filter(c -> c.getState().isWalkable()).isPresent()) {
                        entity.translateY(speed * tpf);
                    }
                    break;

                case "down":
                    if (texture.getAnimationChannel() != animDown)
                        texture.loopAnimationChannel(animDown);

                    if (astar.getGrid().getDown(x, y).filter(c -> c.getState().isWalkable()).isPresent()) {
                        entity.translateY(speed * tpf);
                    }
                    
                    break;
            }

            speed = (int) (speed * 0.9);

            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
                texture.loopAnimationChannel(animIdle);
            }

        }



    }

    public void moveRight() {
        speed = 125;
        direction = "right";
    }

    public void moveLeft() {
        speed = -125;
        direction = "left";

    }

    public void moveUp() {
        speed = -125;
        direction = "up";
    }

    public void moveDown() {
        speed = 125;
        direction = "down";
    }


}