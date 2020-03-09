package src.model;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * This file is to handle animating the sprite sheet.
 * @author Nicholas Kalar
 * @version 2/29/2020
 */

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int colums;
    private int offsetX;
    private int offsetY;
    private final int width;
    private final int height;

    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            int count,
            int colums,
            int offsetx,
            int offsety,
            int width,
            int height ) {
                this.imageView = imageView;
                this.count = count;
                this.colums = colums;
                this.offsetX = offsetx;
                this.offsetY = offsety;
                this.width = width;
                this.height = height;

                setCycleDuration(duration);
                setCycleCount(Animation.INDEFINITE);
                setInterpolator(Interpolator.LINEAR);
                this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    }

    @Override
    protected void interpolate(double frac) {
        final int index = Math.min((int)Math.floor(count*frac), count-1);
        final int x = (index%colums)*width+offsetX;
        final int y = (index/colums)*height+offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
        
    }
    
    public void setOffsetX(int x){
        this.offsetX = x;
    }

    public void setOffsetY(int y){
        this.offsetY = y;
    }
}