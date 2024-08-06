package com.github.queerzard.pixieoffice.game.texture;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;


public enum ETextures {

    WATER(0, new Object() {
        Texture evaluate() {
            try {
                return new Texture(ImageIO.read(getClass().getResourceAsStream("/assets/tiles/water_static.png")), null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),

    JASMIN(1,
            new Object() {
                Texture evaluate() {
                    try {
                        return new Texture(new ImageIcon(getClass().getResource("/assets/characters/Jasmin_Idle.gif")).getImage(),
                                new HashMap<>() {{
                                    put("up", ImageIO.read(getClass().getResourceAsStream("/assets/characters/DanielSprite.png")));
                                    put("down_idle", new ImageIcon(getClass().getResource("/assets/characters/Jasmin_Idle.gif")).getImage());
                                    put("down", new ImageIcon(getClass().getResource("/assets/characters/Jasmin_down.gif")).getImage());
                                    put("right", ImageIO.read(getClass().getResourceAsStream("/assets/characters/JasSprite.png")));
                                    put("left", ImageIO.read(getClass().getResourceAsStream("/assets/characters/JasSprite.png")));
                                }});
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.evaluate()),

    WALL(2, new Object() {
        Texture evaluate() {
            try {
                return new Texture(ImageIO.read(getClass().getResourceAsStream("/assets/tiles/wall.png")), null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),

    GRASS(3, new Object() {
        Texture evaluate() {
            try {
                return new Texture(ImageIO.read(getClass().getResourceAsStream("/assets/tiles/grass.png")), null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),

    WOOD_PLANK(4, new Object() {
        Texture evaluate() {
            try {
                return new Texture(ImageIO.read(getClass().getResourceAsStream("/assets/tiles/wood_plank.png")), null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }.evaluate()),

    ;

    public Texture resource;
    public int id;

    ETextures(int id, Texture resource) {
        this.resource = resource;
        this.id = id;
    }


}
