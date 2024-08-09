package com.github.queerzard.pixieoffice.game.entity.player;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.entity.GameEntity;
import com.github.queerzard.pixieoffice.game.object.map.Map;
import com.github.queerzard.pixieoffice.game.texture.Texture;
import lombok.Getter;
import lombok.Setter;

public class PlayerEntity extends GameEntity {

    @Getter @Setter private String name;

    public PlayerEntity(Map map, Texture texture, String name, int health, int speed, int posX, int posY, int z) {
        super(map, texture, health, speed, posX, posY, z);
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
            up();
            return;
        }

        if (PixieOffice.getPixieOffice().getControlsHandler().isDownPressed()) {
            down();
            return;
        }

        if (PixieOffice.getPixieOffice().getControlsHandler().isLeftPressed()) {
            left();
            return;
        }

        if (PixieOffice.getPixieOffice().getControlsHandler().isRightPressed()) {
            right();
            return;
        }


    }

}
