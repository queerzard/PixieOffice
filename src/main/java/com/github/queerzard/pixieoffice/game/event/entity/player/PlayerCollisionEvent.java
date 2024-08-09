package com.github.queerzard.pixieoffice.game.event.entity.player;

import com.github.queerzard.pixieoffice.game.object.Collidable;
import com.github.sebyplays.jevent.api.Event;
import lombok.Getter;

import java.awt.*;

public class PlayerCollisionEvent extends Event {

    @Getter private Collidable collidable;
    @Getter private int newX,newY;
    @Getter private Rectangle newBounds;

    public PlayerCollisionEvent(){}
    public PlayerCollisionEvent(Collidable collidable, int newX, int newY, Rectangle newBounds){
        this.collidable = collidable;
        this.newX = newX;
        this.newY = newY;
        this.newBounds = newBounds;
    }

}
