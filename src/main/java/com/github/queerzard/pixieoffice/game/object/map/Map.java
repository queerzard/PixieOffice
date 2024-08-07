package com.github.queerzard.pixieoffice.game.object.map;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.RenderingQueue;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import lombok.Getter;
import lombok.SneakyThrows;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Map {

    @Getter
    private static HashMap<String, Map> mapCache = new HashMap<>();

    @Getter
    private String mapResource;

    @Getter
    private ArrayList<AbstractGameObject> gameObjects = new ArrayList<>();

    private Map(String resourcePath) {
        initMap(resourcePath);
    }

    @SneakyThrows
    private void initMap(String resourcePath) {
        String map = new String(getClass().getResourceAsStream(resourcePath).readAllBytes(), StandardCharsets.UTF_8);
        System.out.println(map);
        String[] rows = map.split("\n");
        int y = 0;
        for (String row : rows) {
            int x = 0;
            for (String column : row.split(" ")) {
                String temp = column.trim().replaceAll("[^\\d.]", "");
                if (!temp.matches("^[0-9].*"))
                    continue;
                this.gameObjects.add(AbstractGameObject.getObject(Integer.parseInt(temp))
                        .getDeclaredConstructor(int.class, int.class, int.class).newInstance(x, y, 0));
                x += PixieOffice.getPixieOffice().getGameWindow().getRescaledTileSize();
            }
            y += PixieOffice.getPixieOffice().getGameWindow().getRescaledTileSize();
        }
    }

    public static Map loadMap(String resourcePath) {
        if (!mapCache.containsKey(resourcePath))
            return mapCache.put(resourcePath, new Map(resourcePath));
        return mapCache.get(resourcePath);
    }

    public void placeObjectAt(Class<? extends AbstractGameObject> abstractGameObject, int column, int row) {
    }

    public AbstractGameObject getObjectAt(int column, int row) {
        return null;
    }

    public void save() {
    }

    public void queueDrawing(Graphics2D graphics2D) {
        for (AbstractGameObject abstractGameObject : (ArrayList<AbstractGameObject>) gameObjects.clone())
            RenderingQueue.addGameObject(abstractGameObject);

    }

}
