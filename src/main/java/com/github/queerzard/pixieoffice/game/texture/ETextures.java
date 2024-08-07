package com.github.queerzard.pixieoffice.game.texture;

import javax.imageio.ImageIO;
import javax.swing.*;
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
                        return new Texture(new ImageIcon(getClass().getResource("/assets/characters/jasmin/jasmin_down_idle.gif")).getImage(),
                                new HashMap<>() {{
                                    put("up", new ImageIcon(getClass().getResource("/assets/characters/jasmin/jasmin_up.gif")).getImage());
                                    put("up_idle", new ImageIcon(getClass().getResource("/assets/characters/jasmin/jasmin_up_idle.gif")).getImage());
                                    put("down", new ImageIcon(getClass().getResource("/assets/characters/jasmin/jasmin_down.gif")).getImage());
                                    put("down_idle", new ImageIcon(getClass().getResource("/assets/characters/jasmin/jasmin_down_idle.gif")).getImage());
                                    put("right", new ImageIcon(getClass().getResource("/assets/characters/jasmin/jasmin_right.gif")).getImage());
                                    put("right_idle", new ImageIcon(getClass().getResource("/assets/characters/jasmin/jasmin_right_idle.gif")).getImage());
                                    put("left", new ImageIcon(getClass().getResource("/assets/characters/jasmin/jasmin_left.gif")).getImage());
                                    put("left_idle", new ImageIcon(getClass().getResource("/assets/characters/jasmin/jasmin_left_idle.gif")).getImage());
                                    /* put("down_idle", new ImageIcon(getClass().getResource("/assets/characters/jasmin/Jasmin_Idle.gif")).getImage());*/
                                   /* put("down", new ImageIcon(getClass().getResource("/assets/characters/jasmin/Jasmin_down.gif")).getImage());
                                    put("right", ImageIO.read(getClass().getResourceAsStream("/assets/characters/jasmin/JasSprite.png")));
                                    put("left", ImageIO.read(getClass().getResourceAsStream("/assets/characters/jasmin/JasSprite.png")));*/
                                }});
                    } catch (Exception e) {
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
