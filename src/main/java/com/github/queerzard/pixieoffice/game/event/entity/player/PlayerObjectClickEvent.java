package com.github.queerzard.pixieoffice.game.event.entity.player;

import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.sebyplays.jevent.api.Event;
import lombok.Getter;

public class PlayerObjectClickEvent extends Event {


    @Getter private AbstractGameObject object;

    public PlayerObjectClickEvent() {}

    public PlayerObjectClickEvent(AbstractGameObject object) {
        this.object = object;
    }


}
