package com.github.queerzard.pixieoffice.game.object.objects;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.texture.ETextures;

public class WaterObject extends AbstractGameObject {
    public WaterObject(int defX, int defY) {
        super(PixieOffice.getPixieOffice().getTextureCache().getTexture(ETextures.WATER), defX, defY);
    }
}
