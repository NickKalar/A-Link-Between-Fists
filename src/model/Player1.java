package src.model;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import static src.application.EntityType.*;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.util.Duration;


/*
* Basic sprite animation and movement
* @author Edwin Hernandez
* @version 3/9/20
*/
public class Player1 extends Component {

    private int speed = 0;
    private String direction = null;
    private String attack = null;
    private AnimatedTexture texture;
    private AnimationChannel animIdle, animLeft, animRight, 
                             animUp, animDown, swordAttackLeft, 
                             swordAttackRight, swordAttackUp, 
                             swordAttackDown;

    // creating new animations for specific actions being used in game.
    // how to use: FXGL.image("file name"), sprite frames per row, single sprite
    // width, single sprite height, duration of full animation, start frame and end
    // frame
    // start frame and end frame are the individual sprite frames in sprite sheet
    // starting from index 0. If there are a total of 50 sprites you can access
    // which ever...
    // with the correct index number
    public Player1() {
        animIdle = new AnimationChannel(FXGL.image("link1_walk.png"), 2, 64, 64, Duration.seconds(1), 0, 1);
        animLeft = new AnimationChannel(FXGL.image("link1_walk.png"), 2, 64, 64, Duration.seconds(1), 6, 7);
        animRight = new AnimationChannel(FXGL.image("link1_walk.png"), 2, 64, 64, Duration.seconds(1), 4, 5);
        animUp = new AnimationChannel(FXGL.image("link1_walk.png"), 2, 64, 64, Duration.seconds(1), 2, 3);
        animDown = new AnimationChannel(FXGL.image("link1_walk.png"), 2, 64, 64, Duration.seconds(1), 0, 1);
        swordAttackLeft = new AnimationChannel(FXGL.image("link1_item.png"), 1, 64, 64, Duration.seconds(1.5), 3, 3);
        swordAttackRight = new AnimationChannel(FXGL.image("link1_item.png"), 1, 64, 64, Duration.seconds(1.5), 2, 2);
        swordAttackUp = new AnimationChannel(FXGL.image("link1_item.png"), 1, 64, 64, Duration.seconds(1.5), 1, 1);
        swordAttackDown = new AnimationChannel(FXGL.image("link1_item.png"), 1, 64, 64, Duration.seconds(1.5), 0, 0);
        
        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
    }

    public List<Entity> wall;
    // on update can be seen as a loop, it is always checking for input
    /**
     * polished up wall collisions as well as added collisions for players
     * @author Edwin Hernandez
     * @version 4/6/20
     */
    @Override
    public void onUpdate(double tpf) {
        if(wall == null)
            wall = FXGL.getGameWorld().getEntitiesByType(BUSH,PLAYER2);
     
        if (speed != 0) {
        switch (direction) {
            case "right":
                direction = "right"; 
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
                direction = "left"; 
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
                direction = "up"; 
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
                direction = "down";
                if (texture.getAnimationChannel() != animDown)
                    texture.loopAnimationChannel(animDown);
                entity.translateY(speed * tpf);
                for (int i = 0; i < wall.size(); i++) {
                    if(entity.isColliding(wall.get(i))) {
                        entity.translateY(-speed*tpf);
                    } 
                 }
                break;

            case "sword":
                    if(texture.getAnimationChannel() != swordAttackRight)
                        texture.loopAnimationChannel(swordAttackRight);
                    break;
            }
        } 
            speed = (int) (speed * 0.9);

            if (FXGLMath.abs(speed) < 1) {
                speed = 0;
                if(texture.getAnimationChannel() != animIdle)
                    texture.loopAnimationChannel(animIdle);
            }
            if(attack != null){
                switch(attack) {
                    case "sword": 
                        if(texture.getAnimationChannel() != swordAttackRight && direction == "right")
                            texture.loopAnimationChannel(swordAttackRight);
                        if(texture.getAnimationChannel() != swordAttackLeft && direction == "left")
                            texture.loopAnimationChannel(swordAttackLeft);
                        if(texture.getAnimationChannel() != swordAttackUp && direction == "up")
                            texture.loopAnimationChannel(swordAttackUp);
                        if(texture.getAnimationChannel() != swordAttackDown && direction == "down")
                            texture.loopAnimationChannel(swordAttackDown);
                        attack = null;
                    break;
            }}
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

    public void swordAttack() {
        attack = "sword";
        // System.out.println("att");
    }
}