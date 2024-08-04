package com.github.queerzard.pixieoffice;

import com.github.queerzard.pixieoffice.config.GameConfig;
import com.github.queerzard.pixieoffice.game.GameWindow;
import com.github.queerzard.pixieoffice.game.entity.player.PlayerEntity;
import com.github.queerzard.pixieoffice.game.loop.GameLoop;
import com.github.queerzard.pixieoffice.utils.ControlsHandler;
import com.github.sebyplays.logmanager.utils.Logger;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class PixieOffice {

    @Getter
    @Setter
    private GameWindow gameWindow;
    @Getter
    @Setter
    private JFrame gameFrame;
    @Getter
    @Setter
    private GameLoop gameLoop;
    @Getter
    @Setter
    private GameConfig gameConfig;

    @Getter
    @Setter
    private PlayerEntity gamePlayer;
    @Getter
    @Setter
    private ControlsHandler controlsHandler = new ControlsHandler();
    ;

    @Getter
    @Setter
    private Logger logger;
    @Getter
    @Setter
    private static PixieOffice pixieOffice;

    @SneakyThrows
    public PixieOffice() {
        this.logger = new Logger(this.getClass().getName());
        this.gameConfig = new GameConfig();

        this.gameFrame = new JFrame();
        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameFrame.setResizable(true);
        this.gameFrame.setTitle("PixieOffice");
        this.controlsHandler = new ControlsHandler();
        this.gameWindow = new GameWindow(this.controlsHandler);
        this.gameFrame.add(this.gameWindow);
        this.gameFrame.pack();
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setVisible(true);

        this.gamePlayer = new PlayerEntity(ImageIO.read(getClass().getResourceAsStream("/assets/JasSprite.png")), "Jasmin", 10, 100, 100);

        initLoop();
    }

    private void initLoop() {

        this.gameLoop = new GameLoop() {
            @Override
            public void loop() {
                update();
                gameWindow.repaint();
            }
        };

    }

    private void update() {
        this.getGamePlayer().update();
    }

    private void paintComponent(Graphics graphics) {
        this.gameWindow.paintComponent(graphics);
    }

    public static void main(String[] args) {
        PixieOffice.setPixieOffice(new PixieOffice());
    }

}
