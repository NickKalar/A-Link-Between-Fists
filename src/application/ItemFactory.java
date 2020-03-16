package src.application;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;

import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Map;

import static com.almasb.fxgl.dsl.FXGL.*;

/* Create spawns for different items to be called when needed
* @Author Edwin Hernandez
* @Version 3/9/20
*/

public class ItemFactory implements EntityFactory {
    
    @Spawns("bow")
    public Entity newBow(SpawnData data) {
        return entityBuilder()
            .type(EntityType.BOW)
            .from(data)
            .viewWithBBox("bow.png")
            .collidable()
            .build();
    }
}
