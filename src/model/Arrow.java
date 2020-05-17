package src.model;

public class Arrow extends SwordComponent {
    Arrow() {
        updateDamage(1);
        setCharges(10);
        int direction = 0;
        // update direction based on which way link is facing
        setSprite("arrow.png", 4, 64, 64, direction);
    }

    public void onShot() {
        // spawns the arrow sprite moving the direction Link is facing
    }
    public void onHit() {
        // causes damage to an enemy if collision, and disapears
    }
}