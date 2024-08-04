package com.github.queerzard.pixieoffice.config;

public class GameConfig extends AbstractProperties {
    public GameConfig() {
        super(GameConfig.class.getSimpleName());
    }

    @Override
    public void initDefaults() {
        setDefault("framerate", "60");
    }
}
