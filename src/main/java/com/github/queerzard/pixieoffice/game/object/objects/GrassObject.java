package com.github.queerzard.pixieoffice.game.object.objects;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.object.map.Map;
import com.github.queerzard.pixieoffice.game.texture.ETextures;

public class GrassObject extends AbstractGameObject {
    public GrassObject(Map map, int defX, int defY, int z) {
        super(map, PixieOffice.getPixieOffice().getTextureCache().getTexture(ETextures.GRASS), defX, defY, z);
    }
}
