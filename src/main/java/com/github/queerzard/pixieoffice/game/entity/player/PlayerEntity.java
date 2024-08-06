package com.github.queerzard.pixieoffice.game.entity.player;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.entity.GameEntity;
import com.github.queerzard.pixieoffice.game.texture.Texture;
import lombok.Getter;
import lombok.Setter;

public class PlayerEntity extends GameEntity {

    @Getter
    @Setter
    private String name;

    public PlayerEntity(Texture texture, String name, int health, int speed, int posX, int posY) {
        super(texture, health, speed, posX, posY);
        this.name = name;
    }

    public void update() {

        if (!(PixieOffice.getPixieOffice().getControlsHandler().isUpPressed() ||
                PixieOffice.getPixieOffice().getControlsHandler().isDownPressed() ||
                PixieOffice.getPixieOffice().getControlsHandler().isRightPressed() ||
                PixieOffice.getPixieOffice().getControlsHandler().isLeftPressed())) {


            if (PixieOffice.getPixieOffice().getControlsHandler().isRightLast()) {

            }

            if (PixieOffice.getPixieOffice().getControlsHandler().isUpLast()) {

            }

            if (PixieOffice.getPixieOffice().getControlsHandler().isDownLast())
                getTexture().setActive("down_idle");

            if (PixieOffice.getPixieOffice().getControlsHandler().isDownLast()) {

            }
            return;
        }

        if (PixieOffice.getPixieOffice().getControlsHandler().isUpPressed()) {
            getTexture().setActive("up");
            setPosY(getPosY() - getSpeed());
            return;
        }

        if (PixieOffice.getPixieOffice().getControlsHandler().isDownPressed()) {
            getTexture().setActive("down");
            setPosY(getPosY() + getSpeed());
            return;
        }

        if (PixieOffice.getPixieOffice().getControlsHandler().isLeftPressed()) {
            getTexture().setActive("left");
            setPosX(getPosX() - getSpeed());
            return;
        }

        if (PixieOffice.getPixieOffice().getControlsHandler().isRightPressed()) {
            getTexture().setActive("right");
            setPosX(getPosX() + getSpeed());
            return;
        }


    }

}
