package src.application;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import static src.application.EntityType.*;
import src.model.*;

/* Create spawns for different items to be called when needed
* @Author Edwin Hernandez
* @Version 3/9/20
*/

public class ItemFactory implements EntityFactory {
    
    @Spawns("bow")
    public Entity newBow(SpawnData data) {
        return entityBuilder()
            .type(BOW)
            .from(data)
            .viewWithBBox("bow.png")
            .collidable()
            .build();
    }

    @Spawns("bomb")
    public Entity newBomb(SpawnData data) {
        return entityBuilder()
        .type(BOMB)
        .from(data)
        .viewWithBBox("bomb.png")
        .collidable()
        .build();
    }

    /**
     * fixed issue on why wall detections we're working, was using tile size vs object size created from map maker (SMH!)
     * @author Edwin Hernandez
     * @version 4/6/20
     */

    @Spawns("bush")
    public Entity newBush(SpawnData data) {
        return entityBuilder()
                .type(BUSH)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .build();
    }
/**
 * players are now entities that can be spawned when needed instead of automatically being spawned when game begins 
 * also added player 2 support
 * @author Edwin Hernandez
 * @version 4/1/2020
 */

    @Spawns("player1")
    public Entity newPlayer(SpawnData data) {
        return entityBuilder()
                .type(PLAYER1)
                .bbox(new HitBox(BoundingShape.box(60,60)))
                .at(128,128)
                .with(new CollidableComponent(true))
                .with(new Player1())
                .build();
    }

    @Spawns("player2")
    public Entity newPlayer2(SpawnData data) {
        return entityBuilder()
                .type(PLAYER2)
                .bbox(new HitBox(BoundingShape.box(60,60)))
                .at(1344,128)
                .with(new CollidableComponent(true))
                .with(new Player2())
                .build();
    }

    @Spawns("player3")
    public Entity newPlayer3(SpawnData data) {
        return entityBuilder()
                .type(PLAYER1)
                .bbox(new HitBox(BoundingShape.box(60,60)))
                .at(128,832)
                .with(new CollidableComponent(true))
                .with(new Player3())
                .build();
    }

    @Spawns("player4")
    public Entity newPlayer4(SpawnData data) {
        return entityBuilder()
                .type(PLAYER1)
                .bbox(new HitBox(BoundingShape.box(60,60)))
                .at(1344,832)
                .with(new CollidableComponent(true))
                .with(new Player4())
                .build();
    }
}
