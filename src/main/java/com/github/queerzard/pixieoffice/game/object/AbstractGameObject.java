package com.github.queerzard.pixieoffice.game.object;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.entity.GameEntity;
import com.github.queerzard.pixieoffice.game.event.entity.EntityMovementEvent;
import com.github.queerzard.pixieoffice.game.object.map.Map;
import com.github.queerzard.pixieoffice.game.texture.Texture;
import com.github.sebyplays.jevent.JEvent;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Arrays;

public abstract class AbstractGameObject {

    @Getter
    private int posX;
    @Getter
    private int posY;

    @Getter
    @Setter
    private int zIndex;

    @Getter
    @Setter
    private int worldX = 0;
    @Getter
    @Setter
    private int worldY = 0;


    @Getter
    private Map map;

    @Getter
    @Setter
    private Texture texture;

    public AbstractGameObject(Map map, Texture texture, int defX, int defY, int zIndex) {
        this.map = map;
        this.posX = defX;
        this.posY = defY;
        this.zIndex = zIndex;
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


    public void setPosX(int x) {
        if (!(this instanceof GameEntity)) {
            this.posX = x;
            return;
        }
        if (!new JEvent(new EntityMovementEvent((GameEntity) this)).callEvent().getEvent().isCancelled())
            this.posX = x;
    }

    public void setPosY(int y) {
        if (!(this instanceof GameEntity)) {
            this.posY = y;
            return;
        }
        if (!new JEvent(new EntityMovementEvent((GameEntity) this)).callEvent().getEvent().isCancelled())
            this.posY = y;

    }

}
