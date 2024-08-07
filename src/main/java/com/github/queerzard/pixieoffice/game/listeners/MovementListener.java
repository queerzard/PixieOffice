package com.github.queerzard.pixieoffice.game.listeners;

import com.github.queerzard.pixieoffice.game.entity.GameEntity;
import com.github.queerzard.pixieoffice.game.event.entity.EntityMovementEvent;
import com.github.sebyplays.jevent.api.EventHandler;
import com.github.sebyplays.jevent.api.Listener;

public class MovementListener implements Listener {

    @EventHandler
    public void onMove(EntityMovementEvent event) {
        System.out.println(((GameEntity) event.getEntity()).getFacing());
    }

}
