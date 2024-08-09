package com.github.queerzard.pixieoffice.game.event.render;

import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.sebyplays.jevent.api.Event;
import lombok.Getter;

public class PreRenderingQueueEvent extends Event {

    @Getter private AbstractGameObject gameObject;

    public PreRenderingQueueEvent() {
    }

    public PreRenderingQueueEvent(final AbstractGameObject gameObject) {
        this.gameObject = gameObject;
    }

}
