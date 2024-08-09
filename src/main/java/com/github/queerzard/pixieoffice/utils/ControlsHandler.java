package com.github.queerzard.pixieoffice.utils;

import com.github.queerzard.pixieoffice.game.event.entity.player.input.*;
import com.github.sebyplays.jevent.JEvent;
import lombok.Getter;
import lombok.Setter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControlsHandler implements KeyListener, MouseListener {

    @Getter private boolean upPressed, downPressed, rightPressed, leftPressed;
    @Getter private boolean upLast, downLast, rightLast, leftLast;

    @Getter private boolean mouseRightPressed, mouseLeftPressed;
    @Getter private boolean mouseRightLast, mouseLeftLast;

    @Getter @Setter private boolean impair = false;

    @Override
    public void keyPressed(KeyEvent e) {
        if(new JEvent(new PlayerKeyPressEvent(e, impair)).callEvent().getEvent().isCancelled())
            return;

        if (impair)
            return;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                this.upPressed = true;
                break;
            case KeyEvent.VK_A:
                this.leftPressed = true;
                break;
            case KeyEvent.VK_S:
                this.downPressed = true;
                break;
            case KeyEvent.VK_D:
                this.rightPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(new JEvent(new PlayerKeyReleaseEvent(e, impair)).callEvent().getEvent().isCancelled())
            return;

        if (impair)
            return;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                this.upPressed = false;
                this.upLast = true;
                this.downLast = false;
                this.rightLast = false;
                this.leftLast = false;
                break;
            case KeyEvent.VK_A:
                this.leftPressed = false;
                this.upLast = false;
                this.downLast = false;
                this.rightLast = false;
                this.leftLast = true;
                break;
            case KeyEvent.VK_S:
                this.downPressed = false;
                this.upLast = false;
                this.downLast = true;
                this.rightLast = false;
                this.leftLast = false;
                break;
            case KeyEvent.VK_D:
                this.rightPressed = false;
                this.upLast = false;
                this.downLast = false;
                this.rightLast = true;
                this.leftLast = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

        new JEvent(new PlayerKeyTypeEvent(e, impair)).callEvent();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(new JEvent(new PlayerMouseClickEvent(e, impair)).callEvent().getEvent().isCancelled())
            return;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(new JEvent(new PlayerMousePressEvent(e, impair)).callEvent().getEvent().isCancelled())
            return;

        switch (e.getButton()){
            case 1:
                this.mouseLeftPressed = true;
                break;

            case 3:
                this.mouseRightPressed = true;
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(new JEvent(new PlayerMouseReleaseEvent(e, impair)).callEvent().getEvent().isCancelled())
            return;

        switch (e.getButton()){
            case 1:
                this.mouseLeftPressed = false;
                this.mouseLeftLast = true;
                this.mouseRightLast = false;
                break;

            case 3:
                this.mouseRightPressed = false;
                this.mouseRightLast = true;
                this.mouseLeftLast = false;
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
