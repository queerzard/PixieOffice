package com.github.queerzard.pixieoffice.game.object.objects;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.object.Collidable;
import com.github.queerzard.pixieoffice.game.texture.ETextures;

import java.awt.*;

public class WallObject extends AbstractGameObject implements Collidable {
    public WallObject(int defX, int defY, int z) {
        super(PixieOffice.getPixieOffice().getTextureCache().getTexture(ETextures.WALL), defX, defY, z);
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public Rectangle solidArea() {
        return null;
    }
}
