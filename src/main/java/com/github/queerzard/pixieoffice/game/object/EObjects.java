package com.github.queerzard.pixieoffice.game.object;

import com.github.queerzard.pixieoffice.game.object.objects.GrassObject;
import com.github.queerzard.pixieoffice.game.object.objects.WallObject;
import com.github.queerzard.pixieoffice.game.object.objects.WaterObject;
import com.github.queerzard.pixieoffice.game.object.objects.WoodPlankObject;

public enum EObjects {

    WATER(0, WaterObject.class),
    WALL(2, WallObject.class),
    GRASS(3, GrassObject.class),
    WOOD_PLANK(4, WoodPlankObject.class),
    SPAWN(5, WoodPlankObject.class),
    ;

    public int id;
    public Class<? extends AbstractGameObject> abstractGameObject;

    EObjects(int id, Class<? extends AbstractGameObject> abstractGameObject) {
        this.id = id;
        this.abstractGameObject = abstractGameObject;
    }
}
