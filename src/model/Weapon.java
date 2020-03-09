package src.model;

/**
 * This file is the base class for all weapon upgrades/pickups
 * @author Nicholas Kalar
 * @version 3/8/2020
 */
public class Weapon {
    private float damage;

    Weapon() {
        this.damage = 1;
    }

    Weapon(float damage) {
        this.damage = damage;
    }
}