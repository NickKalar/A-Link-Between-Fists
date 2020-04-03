package src.model;

public class Bomb extends Weapon {
    Bomb(){
        updateDamage(2);
        setCharges(3);
        setSprite("bomb.png", 1, 64, 64, 0);
    }

    public void onPlaced() {
        //set bomb on tile
        //set timer to explode after 3 seconds
    }

    public void onExplosion() {
        //update sprite to bomb explosion in a 9x9 grid centering on the original bomb placement
        //deal damage to anyone within the blast radius
        updateCharges();
    }
}