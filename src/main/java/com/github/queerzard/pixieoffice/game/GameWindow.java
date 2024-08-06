package com.github.queerzard.pixieoffice.game;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.object.map.Map;
import com.github.queerzard.pixieoffice.utils.ControlsHandler;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {

    @Getter
    private final int originalTileSize = 16;
    @Getter
    private int scaleFactor = 3;
    @Getter
    private int rescaledTileSize = originalTileSize * scaleFactor;

    @Getter
    private final int displayColumns = 18;
    @Getter
    private final int displayRows = 10;
    @Getter
    private final int displayWidth = displayColumns * rescaledTileSize;
    @Getter
    private final int displayHeight = displayRows * rescaledTileSize;

    @Getter
    private final int maxWorldColumns = 60;
    @Getter
    private final int maxWorldRows = 60;
    @Getter
    private final int worldWidth = rescaledTileSize * maxWorldColumns;
    @Getter
    private final int worldHeight = rescaledTileSize * maxWorldRows;


    public GameWindow(ControlsHandler controlsHandler) {
        this.setPreferredSize(new Dimension(this.displayWidth, this.displayHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(controlsHandler);
        this.setFocusable(true);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        if (PixieOffice.getPixieOffice().getMap() != null) {
            PixieOffice.getPixieOffice().getMap().draw(graphics2D);
        } else {
            PixieOffice.getPixieOffice().setMap(Map.loadMap("/assets/maps/map1.txt"));
        }



        PixieOffice.getPixieOffice().getGamePlayer().draw(graphics2D);
        graphics2D.dispose();
    }


}
