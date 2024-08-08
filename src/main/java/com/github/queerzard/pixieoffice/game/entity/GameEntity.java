package com.github.queerzard.pixieoffice.game.entity;

import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.object.Collidable;
import com.github.queerzard.pixieoffice.game.texture.Texture;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.HashMap;

public class GameEntity extends AbstractGameObject implements Collidable {

    @Getter
    @Setter
    private int health;
    @Getter
    @Setter
    private int speed;
    @Getter
    @Setter
    private int worldX = 100;
    @Getter
    @Setter
    private int worldY = 100;

    @Getter
    @Setter
    private EDirection facing = EDirection.SOUTH;

    @Getter
    private Rectangle bounds;

    @Getter
    @Setter
    private HashMap<String, Object> attributes;

    public GameEntity(Texture texture, int health, int speed, int posX, int posY, int z) {
        super(texture, posX, posY, z);
        this.attributes = new HashMap<>();
        this.health = health;
        this.speed = speed;
        bounds = new Rectangle(posX, posY, 48, 48);
    }

    public void up() {
        getTexture().setActive("up");
        facing = EDirection.NORTH;
        setWorldY(worldX - speed);
    }

    public void down() {
        getTexture().setActive("down");
        facing = EDirection.SOUTH;
        setWorldY(worldY + speed);
    }

    public void left() {
        getTexture().setActive("left");
        facing = EDirection.WEST;
        setWorldX(worldX - speed);
    }

    public void right() {
        getTexture().setActive("right");
        facing = EDirection.EAST;
        setWorldX(worldX + speed);
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

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public Rectangle solidArea() {
        return null;
    }
}
