package com.github.queerzard.pixieoffice.game.loop;

import com.github.queerzard.pixieoffice.PixieOffice;
import lombok.Getter;

public abstract class GameLoop implements Runnable, ILoop {

    private boolean alive = true;
    @Getter
    private Thread thread;

    public GameLoop() {
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / (Integer.parseInt((String) PixieOffice.getPixieOffice().getGameConfig().get("framerate")));
        double nextDraw = System.nanoTime() + drawInterval;
        while (this.alive) {
            loop();
            long remainder = (long) ((nextDraw - System.nanoTime()) / 1000000);
            try {
                if (remainder < 0) remainder = 0;
                Thread.sleep(remainder);
                nextDraw += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void kill() {
        this.alive = false;
    }
}
