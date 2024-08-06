package com.github.queerzard.pixieoffice.game.entity;

import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.texture.Texture;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

public class GameEntity extends AbstractGameObject {

    @Getter
    @Setter
    private int health;
    @Getter
    @Setter
    private int speed;
    @Getter
    @Setter
    private HashMap<String, Object> attributes;

    public GameEntity(Texture texture, int health, int speed, int posX, int posY) {
        super(texture, posX, posY);
        this.attributes = new HashMap<>();
        this.health = health;
        this.speed = speed;
    }

}
