package com.github.queerzard.pixieoffice.game.listeners;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.RenderingQueue;
import com.github.queerzard.pixieoffice.game.event.render.PrePaintingRender;
import com.github.sebyplays.jevent.api.EventHandler;
import com.github.sebyplays.jevent.api.Listener;

public class PaintingListener implements Listener {

    @EventHandler
    public void onPaint(PrePaintingRender prePaintingRender) {
        if (PixieOffice.getPixieOffice().getMap() != null)
            PixieOffice.getPixieOffice().getMap().queueDrawing(prePaintingRender.getGraphics());
        RenderingQueue.addGameObject(PixieOffice.getPixieOffice().getGamePlayer());
    }

}
