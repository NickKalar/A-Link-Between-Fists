package src.model;

public class Arrow extends Weapon {
    Arrow() {
        updateDamage(1);
        setCharges(10);
        int direction = 0;
        // update direction based on which way link is facing
        setSprite("bomb.png", 4, 64, 64, direction);
    }
}