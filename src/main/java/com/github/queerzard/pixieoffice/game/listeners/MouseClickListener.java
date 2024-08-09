package com.github.queerzard.pixieoffice.game.listeners;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.GameWindow;
import com.github.queerzard.pixieoffice.game.entity.player.PlayerEntity;
import com.github.queerzard.pixieoffice.game.event.entity.player.PlayerObjectClickEvent;
import com.github.queerzard.pixieoffice.game.event.entity.player.input.PlayerMouseClickEvent;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.object.Clickable;
import com.github.queerzard.pixieoffice.game.object.StaticElement;
import com.github.sebyplays.jevent.JEvent;
import com.github.sebyplays.jevent.api.EventHandler;
import com.github.sebyplays.jevent.api.Listener;

import java.awt.*;

public class MouseClickListener implements Listener {

    @EventHandler
    public void onClick(PlayerMouseClickEvent event){
        // Get the mouse click coordinates
        int mouseX = event.getMouseEvent().getX();
        int mouseY = event.getMouseEvent().getY();

        // Convert the screen coordinates to world coordinates
        GameWindow gameWindow = PixieOffice.getPixieOffice().getGameWindow();
        PlayerEntity player = PixieOffice.getPixieOffice().getGamePlayer();

        int cameraOffsetX = gameWindow.getDisplayWidth() / 2 - player.getPosX();
        int cameraOffsetY = gameWindow.getDisplayHeight() / 2 - player.getPosY();

        int worldX = mouseX;
        int worldY = mouseY;

        // Iterate through all game objects to find the clickable one under the click
        Clickable clickedObject = null;
        for (AbstractGameObject obj : PixieOffice.getPixieOffice().getGamePlayer().getMap().getGameObjects()) {
            if (obj instanceof Clickable) {

                if (!(obj instanceof StaticElement)) {
                    worldX -= cameraOffsetX;
                    worldY -= cameraOffsetY;
                }

                Rectangle clickableArea = ((Clickable) obj).clickableArea();
                if (clickableArea != null && clickableArea.contains(worldX, worldY)) {
                    clickedObject = (Clickable) obj;
                    break;
                }
            }
        }

        // Handle the clicked object
        if (clickedObject != null) {
            // Do something with the clicked object, like selecting it, interacting with it, etc.
            new JEvent(new PlayerObjectClickEvent((AbstractGameObject) clickedObject)).callEvent();
            System.out.println("Clicked on a clickable object: " + clickedObject);
        } else {
            System.out.println("No clickable object was clicked.");
        }
    }

}
