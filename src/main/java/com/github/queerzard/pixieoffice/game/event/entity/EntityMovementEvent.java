package com.github.queerzard.pixieoffice.game.event.entity;

import com.github.queerzard.pixieoffice.game.entity.GameEntity;
import com.github.sebyplays.jevent.api.Event;
import lombok.Getter;

public class EntityMovementEvent extends Event {

    @Getter
    private GameEntity entity;

    public EntityMovementEvent() {
    }

    public EntityMovementEvent(GameEntity entity) {
        this.entity = entity;
    }

}
