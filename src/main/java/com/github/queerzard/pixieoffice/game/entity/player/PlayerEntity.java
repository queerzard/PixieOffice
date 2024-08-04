package com.github.queerzard.pixieoffice.game.entity.player;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.entity.GameEntity;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

public class PlayerEntity extends GameEntity {

    @Getter
    @Setter
    private String name;

    public PlayerEntity(BufferedImage texture, String name, int health, int posX, int posY) {
        super(texture, health, 4, posX, posY);
        this.name = name;
    }

    public void update() {
        if (PixieOffice.getPixieOffice().getControlsHandler().isUpPressed()) setPosY(getPosY() - getSpeed());
        if (PixieOffice.getPixieOffice().getControlsHandler().isDownPressed()) setPosY(getPosY() + getSpeed());
        if (PixieOffice.getPixieOffice().getControlsHandler().isLeftPressed()) setPosX(getPosX() - getSpeed());
        if (PixieOffice.getPixieOffice().getControlsHandler().isRightPressed()) setPosX(getPosX() + getSpeed());
    }

}
