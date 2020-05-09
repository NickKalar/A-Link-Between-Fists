package src.model;

import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.component.Required;

import javafx.util.Duration;

/**
 * @author Nicholas Kalar
 * @version 3/8/2020
 */

@Required(OwnerComponent.class)
public class SwordComponent extends Component  {
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