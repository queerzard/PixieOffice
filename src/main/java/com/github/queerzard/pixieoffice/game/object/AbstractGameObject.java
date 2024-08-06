package com.github.queerzard.pixieoffice.game.object;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.texture.Texture;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Arrays;

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

    public static Class<? extends AbstractGameObject> getObject(int id) {
        EObjects objects = Arrays.stream(EObjects.values()).filter(objects1 -> objects1.id == id).findFirst()
                .orElse(null);
        System.out.println(objects);
        return objects.abstractGameObject;
    }


}
