package src.application;

import static com.almasb.fxgl.dsl.FXGL.entityBuilder;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;

import javafx.geometry.Point2D;

import static src.application.EntityType.*;
import com.almasb.fxgl.core.util.LazyValue;
import static com.almasb.fxgl.dsl.FXGL.*;

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

    @Spawns("bush")
    public Entity newBush(SpawnData data) {
        return entityBuilder()
                .type(BUSH)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(40, 40)))
                .zIndex(-1)
                .build();
    }
/**
 * players are now entities that can be spawned when needed instead of automatically being spawned when game begins 
 * also added player 2 support
 * @author Edwin Hernandez
 * @version 4/1/2020
 */

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {
        return entityBuilder()
                .type(PLAYER)
                .bbox(new HitBox(new Point2D(4,4), BoundingShape.box(40,40)))
                .at(150,150)
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(64, 64, 100).allowRotation(false))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(new AnimationComponent())
                .build();
    }

    @Spawns("player2")
    public Entity newPlayer2(SpawnData data) {
        return entityBuilder()
                .type(PLAYER)
                .bbox(new HitBox(new Point2D(4,4), BoundingShape.box(55,55)))
                .at(900,100)
                .with(new CollidableComponent(true))
                .with(new CellMoveComponent(64, 64, 100).allowRotation(false))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(new Player2())
                // .with(new AnimationComponent())
                .build();
    }
}
