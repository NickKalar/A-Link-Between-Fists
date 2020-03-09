package src.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * This file is the class for the player character
 * @author Nicholas Kalar
 * @version 3/8/2020
 */
public class Link extends Pane {
    static int health = 6;
    static int swordLevel = 0;
    ImageView imageView;
    int count = 3;
    int colums = 4;
    int offsetX = 0;
    int offsetY = 0;
    int width = 30; // Size of character
    int height = 24; // Size of character
    Rectangle removeRect = null;
    public SpriteAnimation animation;

    Link(){}

    public Link(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
        animation = new SpriteAnimation(imageView, Duration.millis(200), count, colums, offsetX, offsetY, width, height);
        getChildren().addAll(imageView);
    }

    public void moveX(int x){
        boolean right = x>0?true:false;
        for(int i = 0; i < Math.abs(x); i++){
            if(right) this.setTranslateX(this.getTranslateX() + 1);
                else this.setTranslateX(this.getTranslateX() - 1); 
        }
    }
    
    public void moveY(int y){
        boolean right = y>0?true:false;
        for(int i = 0; i < Math.abs(y); i++){
            if(right) this.setTranslateY(this.getTranslateY() + 1);
                else this.setTranslateY(this.getTranslateY() - 1);
        }
    }
}