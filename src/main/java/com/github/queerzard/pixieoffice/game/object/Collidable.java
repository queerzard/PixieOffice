package com.github.queerzard.pixieoffice.game.object;

import java.awt.*;

public interface Collidable {

    boolean isCollidable();

    Rectangle solidArea();

}
