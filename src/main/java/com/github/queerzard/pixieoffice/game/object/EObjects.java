package com.github.queerzard.pixieoffice.game.object;

import com.github.queerzard.pixieoffice.game.object.objects.*;

public enum EObjects {

    WATER(0, WaterObject.class),
    WALL(2, WallObject.class),
    GRASS(3, GrassObject.class),
    WOOD_PLANK(4, WoodPlankObject.class),
    SPAWN(5, SpawnObject.class),
    ;

    public int id;
    public Class<? extends AbstractGameObject> abstractGameObject;

    EObjects(int id, Class<? extends AbstractGameObject> abstractGameObject) {
        this.id = id;
        this.abstractGameObject = abstractGameObject;
    }
}
