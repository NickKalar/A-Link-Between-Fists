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

    public int getHealth() { return health; }

    public int getSword() { return swordLevel; }

    public void onWeaponPickup() {
        //if no weapon, add weapon and remove from screen
        //else, ignore and don't remove weapon from screen
    }

    public void useWeapon() {
        //check for weapon
        //if bomb do placeBomb()
        //if arrow do shootArrow()
    }
    private void updateHealth(int damage) {
        health -= damage;
        if(getHealth() <= 0){
            //kill link
        } else {
            //update UI
        }
    }

    
    public void onDeath() {
        //play death animation
        //respawn after a second or two
    }

    public void onRevive() {
        //make sword level 1, remove weapon, health set to 6
    }
    public void onKill() {
        //increment kill counter
    }

    public void onDamaged(int damage) {
        //play damage animation
        //prevent anymore damage from being take for a short time
        //push character back?
        updateHealth(damage);
    }

    public int swordSwing() {
        //make sure cooldown has passed so you only swing once very so often
        //start cooldown of swing happens
        //if contacts a character return sword level
        //else 
        return 0;
    }

//    private void placeBomb(){
//        //place bomb
//    }
//
//    private void shootArrow() {
//        //check direction link is pointed
//        //shot arrow in that direction
//    }
}