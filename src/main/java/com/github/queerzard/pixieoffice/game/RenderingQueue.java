package com.github.queerzard.pixieoffice.game;

import com.github.queerzard.pixieoffice.game.event.render.PreRenderingQueueEvent;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.sebyplays.jevent.JEvent;

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
        return ordered;
    }

    public static void addGameObject(AbstractGameObject gameObject) {
        if (!(new JEvent(new PreRenderingQueueEvent(gameObject)).callEvent().getEvent().isCancelled()))
            queue.add(gameObject);
    }

}
