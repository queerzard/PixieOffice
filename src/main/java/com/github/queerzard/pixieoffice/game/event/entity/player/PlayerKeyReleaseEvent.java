package com.github.queerzard.pixieoffice.game.event.entity.player;

import com.github.sebyplays.jevent.api.Event;
import lombok.Getter;

import java.awt.event.KeyEvent;

public class PlayerKeyReleaseEvent extends Event {

    @Getter
    private KeyEvent keyEvent;
    @Getter
    private boolean impaired;

    public PlayerKeyReleaseEvent() {
    }

    public PlayerKeyReleaseEvent(KeyEvent keyEvent, boolean impaired) {
        this.keyEvent = keyEvent;
        this.impaired = impaired;
    }
}
