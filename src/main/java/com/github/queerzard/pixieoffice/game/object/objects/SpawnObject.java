package com.github.queerzard.pixieoffice.game.object.objects;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.object.map.Map;
import com.github.queerzard.pixieoffice.game.texture.ETextures;

public class SpawnObject extends AbstractGameObject {
    public SpawnObject(Map map, int defX, int defY, int z) {
        super(map, PixieOffice.getPixieOffice().getTextureCache().getTexture(ETextures.EMPTY), defX, defY, 9999);
    }
}

