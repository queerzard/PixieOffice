package com.github.queerzard.pixieoffice.game.texture;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.HashMap;

public class Texture {

    @Getter
    @Setter
    private Image texture;
    @Getter
    @Setter
    private Image defaultTexture;
    @Getter
    @Setter
    private HashMap<String, Image> textureCache;

    public Texture(Image defaultTexture, HashMap<String, Image> textures) {
        this.defaultTexture = defaultTexture;
        this.texture = defaultTexture;
        this.textureCache = textures;
    }

    public void setActive(String key) {
        this.texture = textureCache.get(key);
    }


}
