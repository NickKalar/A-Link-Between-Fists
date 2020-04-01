package src.model;

import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.dsl.FXGL;
import javafx.util.Duration;

/**
 * This file is the base class for all weapon upgrades/pickups
 * 
 * @author Nicholas Kalar
 * @version 3/8/2020
 */
public class Weapon {
    private int damage;
    private int charges;
    private AnimationChannel sprite;

    public void updateDamage(int newDamage) {
        damage = newDamage;
    }

    public void setCharges(int initialCharges) {
        charges = initialCharges;
    }

    public void updateCharges() {
        charges -= 1;
    }

    public void setSprite(String file, int count, int height, int width, int location) {
        sprite = new AnimationChannel(FXGL.image(file), count, height, width, Duration.seconds(1), location, location);
    }
}