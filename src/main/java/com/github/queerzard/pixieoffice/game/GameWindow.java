package com.github.queerzard.pixieoffice.game;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.utils.ControlsHandler;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {

    @Getter
    private final int originalTileSize = 32;
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
        PixieOffice.getPixieOffice().getGamePlayer().draw(graphics2D);
        graphics2D.dispose();
    }


}
