package com.github.queerzard.pixieoffice.game.event.entity.player.input;

import com.github.sebyplays.jevent.api.Event;
import lombok.Getter;

import java.awt.event.MouseEvent;

public class PlayerMouseClickEvent extends Event {


    @Getter private MouseEvent mouseEvent;
    @Getter private boolean impaired;

    public PlayerMouseClickEvent() {}

    public PlayerMouseClickEvent(MouseEvent mouseEvent, boolean impaired) {
        this.mouseEvent = mouseEvent;
        this.impaired = impaired;
    }

}
