package com.github.queerzard.pixieoffice.utils;

import com.github.queerzard.pixieoffice.game.event.entity.player.PlayerKeyPressEvent;
import com.github.queerzard.pixieoffice.game.event.entity.player.PlayerKeyReleaseEvent;
import com.github.sebyplays.jevent.JEvent;
import lombok.Getter;
import lombok.Setter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlsHandler implements KeyListener {

    @Getter
    private boolean upPressed, downPressed, rightPressed, leftPressed;
    @Getter
    private boolean upLast, downLast, rightLast, leftLast;

    @Getter
    @Setter
    private boolean impair = false;

    @Override
    public void keyPressed(KeyEvent e) {

        new JEvent(new PlayerKeyPressEvent(e, impair)).callEvent();

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
        new JEvent(new PlayerKeyReleaseEvent(e, impair)).callEvent();

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

    }
}
