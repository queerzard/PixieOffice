package com.github.queerzard.pixieoffice.game.object.objects;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.object.Collidable;
import com.github.queerzard.pixieoffice.game.texture.ETextures;

public class WoodPlankObject extends AbstractGameObject implements Collidable {
    public WoodPlankObject(int defX, int defY) {
        super(PixieOffice.getPixieOffice().getTextureCache().getTexture(ETextures.WOOD_PLANK), defX, defY);
    }

    @Override
    public boolean isCollidable() {
        return true;
    }
}
