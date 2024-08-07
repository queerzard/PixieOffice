package com.github.queerzard.pixieoffice.game.event.render;

import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.sebyplays.jevent.api.Event;
import lombok.Getter;

import java.awt.*;

public class PreDrawingQueue extends Event {

    @Getter
    private AbstractGameObject gameObject;
    @Getter
    private Graphics2D graphics;

    public PreDrawingQueue() {
    }

    public PreDrawingQueue(final AbstractGameObject gameObject, Graphics2D graphics) {
        this.graphics = graphics;
        this.gameObject = gameObject;
    }

}
