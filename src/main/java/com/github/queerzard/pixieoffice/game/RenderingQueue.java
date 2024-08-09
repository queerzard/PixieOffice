package com.github.queerzard.pixieoffice.game;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.entity.player.PlayerEntity;
import com.github.queerzard.pixieoffice.game.event.render.PreRenderingQueueEvent;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.object.VisuallyUnadjusted;
import com.github.sebyplays.jevent.JEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RenderingQueue {

    private static ArrayList<AbstractGameObject> queue = new ArrayList<>();


    public static ArrayList<AbstractGameObject> getQueue() {
        ArrayList<AbstractGameObject> ordered = new ArrayList<>(queue);

        // Use Collections.sort with a custom comparator to sort by z index
        Collections.sort(ordered, new Comparator<AbstractGameObject>() {
            @Override
            public int compare(AbstractGameObject o1, AbstractGameObject o2) {
                return Integer.compare(o1.getZIndex(), o2.getZIndex());
            }
        });
        queue.clear();

        if (PixieOffice.getPixieOffice().getGamePlayer() == null)
            return ordered;

        GameWindow gameWindow = PixieOffice.getPixieOffice().getGameWindow();
        PlayerEntity player = PixieOffice.getPixieOffice().getGamePlayer();
        int tileSize = gameWindow.getRescaledTileSize();

        // Calculate the camera offset to center the player
        int cameraOffsetX = gameWindow.getDisplayWidth() / 2 - player.getPosX();
        int cameraOffsetY = gameWindow.getDisplayHeight() / 2 - player.getPosY();


        ArrayList<AbstractGameObject> visibleObjects = new ArrayList<>();
        for (AbstractGameObject obj : ordered) {
            if (obj instanceof VisuallyUnadjusted) {
                visibleObjects.add(obj);
                continue;
            }
            int objX = obj.getPosX();
            int objY = obj.getPosY();

            // Calculate the object's screen position based on camera offset
            int screenX = objX + cameraOffsetX;
            int screenY = objY + cameraOffsetY;

            // Check if the object is within the visible screen area
            if (screenX + tileSize > 0 &&
                    screenX < gameWindow.getDisplayWidth() &&
                    screenY + tileSize > 0 &&
                    screenY < gameWindow.getDisplayHeight()) {

                // Create a copy of the object with adjusted screen positions for rendering
                AbstractGameObject adjustedObject = new AbstractGameObject(obj.getMap(), obj.getTexture(), obj.getPosX(), obj.getPosY(), obj.getZIndex()) {
                    @Override
                    public void draw(Graphics2D graphics) {
                        graphics.drawImage(getTexture().getTexture(), screenX, screenY,
                                tileSize, tileSize, null);
                    }
                };

                visibleObjects.add(adjustedObject);
            }
        }

        // Sort by z-index to ensure correct rendering order

        return visibleObjects;
    }

/*    public static ArrayList<AbstractGameObject> getQueue() {
        ArrayList<AbstractGameObject> ordered = new ArrayList<>(queue);

        // Use Collections.sort with a custom comparator to sort by z index
        Collections.sort(ordered, new Comparator<AbstractGameObject>() {
            @Override
            public int compare(AbstractGameObject o1, AbstractGameObject o2) {
                return Integer.compare(o1.getZIndex(), o2.getZIndex());
            }
        });
        queue.clear();
        return ordered;
    }*/

    public static void addGameObject(AbstractGameObject gameObject) {
        if (!(new JEvent(new PreRenderingQueueEvent(gameObject)).callEvent().getEvent().isCancelled()))
            queue.add(gameObject);
    }

}
