package com.github.queerzard.pixieoffice.game.object;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Texture {

    @Getter
    @Setter
    private BufferedImage texture;
    @Getter
    @Setter
    private BufferedImage defaultTexture;
    @Getter
    @Setter
    private HashMap<String, BufferedImage> textureCache;

    public Texture(BufferedImage defaultTexture, HashMap<String, BufferedImage> textures) {
        this.defaultTexture = defaultTexture;
        this.texture = defaultTexture;
        this.textureCache = textures;
    }

    public void setActive(String key) {
        this.texture = textureCache.get(key);
    }


}
