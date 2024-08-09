package com.github.queerzard.pixieoffice.game.texture;

import com.github.queerzard.pixieoffice.PixieOffice;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;

public class TextureCache {

    @Getter private HashMap<ETextures, Texture> textures;

    public TextureCache() {
        this.textures = new HashMap<>();

    }


    private void initializeTexture(ETextures texture) {
        this.textures.put(texture, texture.resource);
    }

    public Texture getTexture(int id) {
        ETextures textureFetch = Arrays.stream(ETextures.values()).filter(textures1 -> textures1.id == id).findFirst()
                .orElse(null);
        PixieOffice.getPixieOffice().getLogger().info("Fetched Texture: {}", textureFetch.toString());
        if (textureFetch == null)
            return null;
        return this.textures.get(textureFetch);
    }

    public Texture getTexture(ETextures texture) {
        if (!textures.containsKey(texture))
            initializeTexture(texture);
        return textures.get(texture);
    }

}
