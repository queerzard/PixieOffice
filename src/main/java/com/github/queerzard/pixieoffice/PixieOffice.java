package com.github.queerzard.pixieoffice;

import com.github.queerzard.pixieoffice.config.GameConfig;
import com.github.queerzard.pixieoffice.game.GameWindow;
import com.github.queerzard.pixieoffice.game.entity.player.PlayerEntity;
import com.github.queerzard.pixieoffice.game.event.entity.EntityMovementEvent;
import com.github.queerzard.pixieoffice.game.event.render.PrePaintingRender;
import com.github.queerzard.pixieoffice.game.listeners.MovementListener;
import com.github.queerzard.pixieoffice.game.listeners.PaintingListener;
import com.github.queerzard.pixieoffice.game.loop.GameLoop;
import com.github.queerzard.pixieoffice.game.object.map.Map;
import com.github.queerzard.pixieoffice.game.texture.ETextures;
import com.github.queerzard.pixieoffice.game.texture.TextureCache;
import com.github.queerzard.pixieoffice.utils.AsyncExecutor;
import com.github.queerzard.pixieoffice.utils.ControlsHandler;
import com.github.queerzard.pixieoffice.utils.IAsyncTask;
import com.github.sebyplays.jevent.JEvent;
import com.github.sebyplays.logmanager.utils.Logger;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

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
    private Map map;
    @Getter
    @Setter
    private ControlsHandler controlsHandler = new ControlsHandler();
    @Getter
    @Setter
    private TextureCache textureCache;

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
    }

    public void postConstruct() {
        this.gameFrame = new JFrame();
        this.gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.gameFrame.setResizable(false);
        this.gameFrame.setTitle("PixieOffice");
        this.controlsHandler = new ControlsHandler();
        this.textureCache = new TextureCache();
        this.gameWindow = new GameWindow(this.controlsHandler);
        this.gameFrame.add(this.gameWindow);
        this.gameFrame.pack();
        this.gameFrame.setLocationRelativeTo(null);
        this.gameFrame.setVisible(true);
        initEvents();


        new AsyncExecutor(TimeUnit.SECONDS.toMillis(2)) {
            @Override
            public Object runAsync() {
                PixieOffice.getPixieOffice().setMap(Map.loadMap("/assets/maps/map1.txt"));
                return map;
            }
        };
        setMap(Map.loadMap("/assets/maps/map1.txt"));

        AsyncExecutor.task(new IAsyncTask() {
            @Override
            public Object runAsync() {
                while (map == null) {
                }
                return gamePlayer = new PlayerEntity(textureCache.getTexture(ETextures.JASMIN), "Jasmin", 10, 2, 100, 100, 100);
            }
        });
        initLoop();
    }

    private void initEvents() {
        new JEvent(new PrePaintingRender()).registerListener(new PaintingListener());
        new JEvent(new EntityMovementEvent()).registerListener(new MovementListener());
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
        pixieOffice.postConstruct();
    }

}
