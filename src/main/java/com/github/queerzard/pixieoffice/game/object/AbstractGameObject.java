package com.github.queerzard.pixieoffice.game.object;

import com.github.queerzard.pixieoffice.PixieOffice;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public abstract class AbstractGameObject {

    @Getter
    @Setter
    private int posX;
    @Getter
    @Setter
    private int posY;
    @Getter
    @Setter
    private Texture texture;

    public AbstractGameObject(Texture texture, int defX, int defY) {
        this.posX = defX;
        this.posY = defY;
        this.texture = texture;
    }

    public void draw(Graphics2D graphics) {
        graphics.drawImage(texture.getTexture(), posX, posY,
                PixieOffice.getPixieOffice().getGameWindow().getRescaledTileSize(),
                PixieOffice.getPixieOffice().getGameWindow().getRescaledTileSize(), null);
    }

}
