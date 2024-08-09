package com.github.queerzard.pixieoffice.game.entity;

import com.github.queerzard.pixieoffice.game.event.entity.player.PlayerCollisionEvent;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.object.Collidable;
import com.github.queerzard.pixieoffice.game.object.map.Map;
import com.github.queerzard.pixieoffice.game.texture.Texture;
import com.github.sebyplays.jevent.JEvent;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.HashMap;

public class GameEntity extends AbstractGameObject implements Collidable {

    @Getter @Setter private int health;
    @Getter @Setter private int speed;

    @Getter @Setter private EDirection facing = EDirection.SOUTH;

    @Getter private Rectangle bounds;

    @Getter @Setter private HashMap<String, Object> attributes;

    public GameEntity(Map map, Texture texture, int health, int speed, int posX, int posY, int z) {
        super(map, texture, posX, posY, z);
        this.attributes = new HashMap<>();
        this.health = health;
        this.speed = speed;
        setWorldX(map.getSpawnpoint()[0]);
        setWorldY(map.getSpawnpoint()[1]);
        bounds = new Rectangle(posX, posY, 48, 48);
    }

    public void up() {
        if (checkCollision(getPosX(), getPosY() - speed))
            return;
        getTexture().setActive("up");
        facing = EDirection.NORTH;
        setPosY(getPosY() - speed);
    }

    public void down() {
        if (checkCollision(getPosX(), getPosY() + speed))
            return;
        getTexture().setActive("down");
        facing = EDirection.SOUTH;
        setPosY(getPosY() + speed);
    }

    public void left() {
        if (checkCollision(getPosX() - speed, getPosY()))
            return;
        getTexture().setActive("left");
        facing = EDirection.WEST;
        setPosX(getPosX() - speed);
    }

    public void right() {
        if (checkCollision(getPosX() + speed, getPosY()))
            return;
        getTexture().setActive("right");
        facing = EDirection.EAST;
        setPosX(getPosX() + speed);
    }

    public void upIdle() {
        getTexture().setActive("up_idle");
    }

    public void downIdle() {
        getTexture().setActive("down_idle");
    }

    public void leftIdle() {
        getTexture().setActive("left_idle");
    }

    public void rightIdle() {
        getTexture().setActive("right_idle");
    }

    public void teleport(int x, int y) {
    }

    private boolean checkCollision(int newX, int newY) {
        Rectangle newBounds = new Rectangle(newX + 8, newY + 16, 32, 32);
        for (AbstractGameObject obj : getMap().getGameObjects()) {
            if (obj instanceof Collidable && ((Collidable) obj).isCollidable()) {
                Rectangle objBounds = ((Collidable) obj).solidArea();
                if (newBounds.intersects(objBounds)) {
                    PlayerCollisionEvent pce = (PlayerCollisionEvent) new JEvent(new PlayerCollisionEvent((Collidable) obj, newX, newY, newBounds)).callEvent().getEvent();
                    pce.setCancelled(false);
                    return !pce.isCancelled();
                }
            }
        }
        return false;
    }


    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public Rectangle solidArea() {
        return new Rectangle(getPosX() + 8, getPosY() + 16, 32, 32);
    }
}
