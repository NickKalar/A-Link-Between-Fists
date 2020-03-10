package src.application;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

/*
* Basic sprite animation and movement
* @author Edwin Hernandez
* @version 3/9/20
*/

public class AnimationComponent extends Component {

    private int speed = 0;
    private String direction = null;

    private AnimatedTexture texture;
    private AnimationChannel animIdle, animWalk;
    private AnimationChannel animUp, animDown;

    // creating new animations for specific actions being used in game.
    // how to use: FXGL.image("file name"), sprite frames per row, single sprite width, single sprite height, duration of full animation, start frame and end frame
    // start frame and end frame are the individual sprite frames in sprite sheet starting from index 0. If there are a total of 50 sprites you can access which ever...
    // with the correct index number
    public AnimationComponent() {
        animIdle = new AnimationChannel(FXGL.image("link1.png"), 2, 112, 128, Duration.seconds(1), 0, 1);
        animWalk = new AnimationChannel(FXGL.image("link1.png"), 2, 112, 128, Duration.seconds(1), 4, 5);
        animUp = new AnimationChannel(FXGL.image("link1.png"), 2, 112, 128, Duration.seconds(1), 2, 3);
        animDown = new AnimationChannel(FXGL.image("link1.png"), 2, 112, 128, Duration.seconds(1), 0, 1);


        texture = new AnimatedTexture(animIdle);
    }

    @Override
    public void onAdded() {
        entity.getViewComponent().addChild(texture);
    }

    //on update can be seen as a loop, it is always checking for input
    @Override
    public void onUpdate(double tpf) {

        if (speed != 0) {

            switch(direction) {
                case "right":
                    entity.translateX(speed * tpf);

                    if(texture.getAnimationChannel() == animIdle) 
                        texture.loopAnimationChannel(animWalk);

                break;

                case "left":
                    entity.translateX(speed * tpf);

                    if(texture.getAnimationChannel() == animIdle) 
                        texture.loopAnimationChannel(animWalk);
                
                break;

                case "up":
                entity.translateY(speed * tpf);

                if(texture.getAnimationChannel() == animIdle)
                    texture.loopAnimationChannel(animUp);

                break;
                
                case "down":
                entity.translateY(speed * tpf);

                if(texture.getAnimationChannel() == animIdle)
                    texture.loopAnimationChannel(animDown);

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

        getEntity().setScaleX(1);
    }

    public void moveLeft() {
        speed = -125;
        direction = "left";

        getEntity().setScaleX(-1);
    }

    public void moveUp() {
        speed = -125;
        direction = "up";

        getEntity().setScaleY(1);
    }

    public void moveDown() {
        speed = 125;
        direction = "down";

        getEntity().setScaleY(1);
    }

}