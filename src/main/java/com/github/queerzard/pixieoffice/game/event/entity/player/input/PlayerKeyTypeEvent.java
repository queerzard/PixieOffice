package com.github.queerzard.pixieoffice.game.event.entity.player.input;

import com.github.sebyplays.jevent.api.Event;
import lombok.Getter;

import java.awt.event.KeyEvent;

public class PlayerKeyTypeEvent  extends Event {

    @Getter private KeyEvent keyEvent;
    @Getter private boolean impaired;

    public PlayerKeyTypeEvent() {
    }

    public PlayerKeyTypeEvent(KeyEvent keyEvent, boolean impaired) {
        this.keyEvent = keyEvent;
        this.impaired = impaired;
    }
}