package com.github.queerzard.pixieoffice.game.entity.player;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.entity.EDirection;
import com.github.queerzard.pixieoffice.game.entity.GameEntity;
import com.github.queerzard.pixieoffice.game.texture.Texture;
import lombok.Getter;
import lombok.Setter;

public class PlayerEntity extends GameEntity {

    @Getter
    @Setter
    private String name;

    public PlayerEntity(Texture texture, String name, int health, int speed, int posX, int posY, int z) {
        super(texture, health, speed, posX, posY, z);
        this.name = name;
    }

    public void update() {

        if (!(PixieOffice.getPixieOffice().getControlsHandler().isUpPressed() ||
                PixieOffice.getPixieOffice().getControlsHandler().isDownPressed() ||
                PixieOffice.getPixieOffice().getControlsHandler().isRightPressed() ||
                PixieOffice.getPixieOffice().getControlsHandler().isLeftPressed())) {


            if (PixieOffice.getPixieOffice().getControlsHandler().isRightLast())
                getTexture().setActive("right_idle");

            if (PixieOffice.getPixieOffice().getControlsHandler().isLeftLast())
                getTexture().setActive("left_idle");

            if (PixieOffice.getPixieOffice().getControlsHandler().isUpLast())
                getTexture().setActive("up_idle");

            if (PixieOffice.getPixieOffice().getControlsHandler().isDownLast())
                getTexture().setActive("down_idle");

            return;
        }

        if (PixieOffice.getPixieOffice().getControlsHandler().isUpPressed()) {
            getTexture().setActive("up");
            setFacing(EDirection.NORTH);
            if ((getPosY() - getSpeed() > 0))
                setPosY(getPosY() - getSpeed());
            return;
        }

        if (PixieOffice.getPixieOffice().getControlsHandler().isDownPressed()) {
            getTexture().setActive("down");
            setFacing(EDirection.SOUTH);
            if (!(getPosY() + PixieOffice.getPixieOffice().getGameWindow().getRescaledTileSize() + getSpeed() > PixieOffice.getPixieOffice().getGameWindow().getDisplayHeight()))
                setPosY(getPosY() + getSpeed());
            return;
        }

        if (PixieOffice.getPixieOffice().getControlsHandler().isLeftPressed()) {
            getTexture().setActive("left");
            setFacing(EDirection.WEST);
            if ((getPosX() - getSpeed() > 0))
                setPosX(getPosX() - getSpeed());
            return;
        }

        if (PixieOffice.getPixieOffice().getControlsHandler().isRightPressed()) {
            getTexture().setActive("right");
            setFacing(EDirection.EAST);
            if (!(getPosX() + PixieOffice.getPixieOffice().getGameWindow().getRescaledTileSize() + getSpeed() > PixieOffice.getPixieOffice().getGameWindow().getDisplayWidth()))
                setPosX(getPosX() + getSpeed());
            return;
        }


    }

}
