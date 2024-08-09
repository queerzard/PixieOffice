package com.github.queerzard.pixieoffice.game.object.objects;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.object.Collidable;
import com.github.queerzard.pixieoffice.game.object.map.Map;
import com.github.queerzard.pixieoffice.game.texture.ETextures;

import java.awt.*;

public class BarrierObject extends AbstractGameObject implements Collidable {
    public BarrierObject(Map map, int defX, int defY, int z) {
        super(map, PixieOffice.getPixieOffice().getTextureCache().getTexture(ETextures.EMPTY), defX, defY, 9999);
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public Rectangle solidArea() {
        return new Rectangle(getPosX(), getPosY(), PixieOffice.getPixieOffice().getGameWindow().getRescaledTileSize(),
                PixieOffice.getPixieOffice().getGameWindow().getRescaledTileSize());
    }
}

