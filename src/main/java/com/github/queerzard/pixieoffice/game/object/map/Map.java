package com.github.queerzard.pixieoffice.game.object.map;

import com.github.queerzard.pixieoffice.PixieOffice;
import com.github.queerzard.pixieoffice.game.GameWindow;
import com.github.queerzard.pixieoffice.game.RenderingQueue;
import com.github.queerzard.pixieoffice.game.entity.player.PlayerEntity;
import com.github.queerzard.pixieoffice.game.object.AbstractGameObject;
import com.github.queerzard.pixieoffice.game.object.objects.SpawnObject;
import com.github.queerzard.pixieoffice.utils.Utils;
import lombok.Getter;
import lombok.SneakyThrows;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Map implements Serializable {

    @Getter
    private static HashMap<String, Map> mapCache = new HashMap<>();

    @Getter
    private String mapResource;
    @Getter
    private UUID uuid;

    @Getter
    private int[] spawnpoint = new int[2];

    @Getter
    private ArrayList<AbstractGameObject> gameObjects = new ArrayList<>();

    private Map(String resourcePath) {
        this.mapResource = resourcePath;
        this.uuid = UUID.randomUUID();
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
                parseCells(column, x, y);
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

    @SneakyThrows
    public static Map loadMap(File file) {
        return (Map) Utils.parseObject(Files.readAllBytes(file.toPath()));
    }

    private void parseCells(String column, int x, int y) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Pattern to match the content inside the brackets
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(column);

        if (matcher.find()) {
            String blocks = matcher.group(1); // Get the content inside the brackets
            String[] blockDefinitions = blocks.split(",\\s*"); // Split by comma

            for (String blockDefinition : blockDefinitions) {
                // Match the block ID and z-index
                Pattern blockPattern = Pattern.compile("(\\d+)\\((\\d+)\\)");
                Matcher blockMatcher = blockPattern.matcher(blockDefinition);

                if (blockMatcher.find()) {
                    int blockId = Integer.parseInt(blockMatcher.group(1));
                    int zIndex = Integer.parseInt(blockMatcher.group(2));

                    // Instantiate the game object with x, y, and z
                    AbstractGameObject ago;
                    gameObjects.add(ago = AbstractGameObject.getObject(blockId)
                            .getDeclaredConstructor(Map.class, int.class, int.class, int.class)
                            .newInstance(this, x, y, zIndex));
                    if (ago instanceof SpawnObject) {
                        this.spawnpoint[0] = x;
                        this.spawnpoint[1] = y;
                    }
                }
            }
        }
    }

    public void placeObjectAt(Class<? extends AbstractGameObject> abstractGameObject, int column, int row, int z) {

    }

    public AbstractGameObject getObjectAt(int column, int row, int z) {

        return null;
    }

    @SneakyThrows
    public void save() {
        save(null);
    }

    public void save(String name) throws IOException {
        File file = new File(System.getProperty("user.dir") + "/saves/",
                (name != null ? name : uuid.toString()) + ".bin");
        file.getParentFile().mkdirs();
        file.createNewFile();
        Files.write(file.toPath(), Utils.objectToBytes(this));
    }

    public void queueDrawing(Graphics2D graphics2D) {
        for (AbstractGameObject abstractGameObject : (ArrayList<AbstractGameObject>) gameObjects.clone())
            RenderingQueue.addGameObject(abstractGameObject);

    }

    public void queueDrawing2(Graphics2D graphics2D) {
        GameWindow gameWindow = PixieOffice.getPixieOffice().getGameWindow();
        int tileSize = gameWindow.getRescaledTileSize();

        // Assuming you have a player object with posX, posY (world coordinates) properties
        PlayerEntity player = PixieOffice.getPixieOffice().getGamePlayer();
        int playerWorldX = player.getPosX();
        int playerWorldY = player.getPosY();
        int playerScreenX = gameWindow.getDisplayWidth() / 2 - tileSize / 2;
        int playerScreenY = gameWindow.getDisplayHeight() / 2 - tileSize / 2;

        for (AbstractGameObject abstractGameObject : (ArrayList<AbstractGameObject>) gameObjects.clone()) {
            int worldX = abstractGameObject.getPosX();
            int worldY = abstractGameObject.getPosY();

            // Check if the tile is within the visible screen area
            if (worldX + tileSize > playerWorldX - playerScreenX &&
                    worldX - tileSize < playerWorldX + playerScreenX &&
                    worldY + tileSize > playerWorldY - playerScreenY &&
                    worldY - tileSize < playerWorldY + playerScreenY) {

                // Add to rendering queue
                RenderingQueue.addGameObject(abstractGameObject);
            }
        }
    }

    public void queueDrawing3(Graphics2D graphics2D) {
        GameWindow gameWindow = PixieOffice.getPixieOffice().getGameWindow();
        PlayerEntity player = PixieOffice.getPixieOffice().getGamePlayer();
        int tileSize = gameWindow.getRescaledTileSize();

        // Calculate the screen bounds
        int screenLeft = player.getPosX() - gameWindow.getDisplayWidth() / 2;
        int screenTop = player.getPosY() - gameWindow.getDisplayHeight() / 2;
        int screenRight = screenLeft + gameWindow.getDisplayWidth();
        int screenBottom = screenTop + gameWindow.getDisplayHeight();


        // Iterate through game objects and check if they are within the screen bounds
        for (AbstractGameObject obj : gameObjects) {
            int objX = obj.getPosX();
            int objY = obj.getPosY();

            if (objX + tileSize > screenLeft &&
                    objX < screenRight &&
                    objY + tileSize > screenTop &&
                    objY < screenBottom) {

                // Add to rendering queue
                RenderingQueue.addGameObject(obj);
            }
        }
    }

}
