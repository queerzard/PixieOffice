package com.github.queerzard.pixieoffice.game.event.render;

import com.github.sebyplays.jevent.api.Event;
import lombok.Getter;

import java.awt.*;

public class PrePaintingRender extends Event {
    @Getter private Graphics2D graphics;

    public PrePaintingRender() {
    }

    public PrePaintingRender(Graphics2D graphics) {
        this.graphics = graphics;
    }

}
