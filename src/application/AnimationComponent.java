package src.application;

import com.almasb.fxgl.physics.BoundingShape;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.TypeComponent;
import com.almasb.fxgl.pathfinding.Cell;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.physics.CollisionResult;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.box2d.collision.Collision;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import static src.application.EntityType.*;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.util.Duration;
import static com.almasb.fxgl.dsl.FXGL.*;


/*
* Basic sprite animation and movement
* @author Edwin Hernandez
* @version 3/9/20
*/
public class AnimationComponent extends Component {

    private int speed = 0;
    private String direction = null;
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
    public AnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image("link1.png"), 2, 112, 128, Duration.seconds(1), 0, 1);
        animLeft = new AnimationChannel(FXGL.image("link1.png"), 2, 112, 128, Duration.seconds(1), 6, 7);
        animRight = new AnimationChannel(FXGL.image("link1.png"), 2, 112, 128, Duration.seconds(1), 4, 5);
        animUp = new AnimationChannel(FXGL.image("link1.png"), 2, 112, 128, Duration.seconds(1), 2, 3);
        animDown = new AnimationChannel(FXGL.image("link1.png"), 2, 112, 128, Duration.seconds(1), 0, 1);

        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
    }

    public List<Entity> wall;
    // on update can be seen as a loop, it is always checking for input
    @Override
    public void onUpdate(double tpf) {
        if(wall == null)
            wall = FXGL.getGameWorld().getEntitiesByType(BUSH,PLAYER2);
     
        if (speed != 0) {
        switch (direction) {
            case "right":
                if (texture.getAnimationChannel() != animRight)
                    texture.loopAnimationChannel(animRight);
                
                entity.translateX(speed * tpf);      
                for (int i = 0; i < wall.size(); i++) {
                    if(entity.isColliding(wall.get(i))) {
                        entity.translateX(-speed*tpf);
                    } 
                 }            
                break;

            case "left":
                if (texture.getAnimationChannel() != animLeft)
                    texture.loopAnimationChannel(animLeft);                    
                entity.translateX(speed * tpf);
                for (int i = 0; i < wall.size(); i++) {
                    if(entity.isColliding(wall.get(i))) {
                        entity.translateX(-speed*tpf);
                    } 
                 }
                break;

            case "up":
                if (texture.getAnimationChannel() != animUp)
                    texture.loopAnimationChannel(animUp);
                entity.translateY(speed * tpf);
                for (int i = 0; i < wall.size(); i++) {
                    if(entity.isColliding(wall.get(i))) {
                        entity.translateY(-speed*tpf);
                    } 
                 }
                break;

            case "down":
                if (texture.getAnimationChannel() != animDown)
                    texture.loopAnimationChannel(animDown);
                entity.translateY(speed * tpf);
                for (int i = 0; i < wall.size(); i++) {
                    if(entity.isColliding(wall.get(i))) {
                        entity.translateY(-speed*tpf);
                    } 
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
        speed = 110;
        direction = "right";
    }

    public void moveLeft() {
        speed = -110;
        direction = "left";

    }

    public void moveUp() {
        speed = -110;
        direction = "up";
    }

    public void moveDown() {
        speed = 110;
        direction = "down";
    }


}