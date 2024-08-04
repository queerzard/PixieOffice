package com.github.queerzard.pixieoffice.config;

import com.github.queerzard.pixieoffice.PixieOffice;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Properties;

/**
 * It's a class that allows you to easily read and write to a properties file
 */
public abstract class AbstractProperties implements IDefaults {
    @Getter
    private final String fileName;
    @Getter
    private final File file;
    @Getter
    private final Properties properties = new Properties();


    public AbstractProperties(String fileName) {
        this(new File(System.getProperty("user.dir") + "/properties/" + fileName + ".properties"));
    }

    public AbstractProperties(File file) {
        this.fileName = file.getName().replace(".properties", "");
        this.file = file;
        initConfig();
        initDefaults();
    }

    /**
     * If the file doesn't exist, create it and load it. If it does exist, load it
     */
    public void initConfig() {
        try {

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                properties.load(new FileInputStream(file));
            } else {
                properties.load(new FileInputStream(file));
            }
        } catch (Exception e) {
            PixieOffice.getPixieOffice().getLogger().error("({}) An error occurred: {}", "Config::initConfig();", e.getMessage());
        }
    }

    /**
     * It saves the config file if the save parameter is true, then reloads the config file
     *
     * @param save Whether or not to save the config before reloading it.
     */
    public void reloadConfig(boolean save) {
        try {
            if (save)
                save();
            properties.load(new FileInputStream(file));
        } catch (Exception e) {
            PixieOffice.getPixieOffice().getLogger().error("({}) An error occurred: {}", "Config::reloadConfig();", e.getMessage());
        }
    }

    /**
     * Get the value of the property at the given path.
     *
     * @param path The path to the property you want to get.
     * @return The value of the property at the given path.
     */
    public Object get(String path) {
        return properties.getProperty(path);
    }

    /**
     * If the property exists, return it, otherwise return the default value
     *
     * @param path         The path to the property you want to get.
     * @param defaultValue The default value to return if the path is not found.
     * @return The value of the property at the given path.
     */
    public Object get(String path, String defaultValue) {
        return properties.getProperty(path, defaultValue);
    }

    /**
     * It sets a value in the config file
     *
     * @param path  The path to the property you want to set.
     * @param value The value to set the path to.
     * @return The value of the property.
     */
    public String set(String path, String value) {
        properties.setProperty(path, value);
        save();
        return value;
    }

    public String setDefault(String path, String value) {
        if (!existing(path))
            set(path, value);
        return value;
    }

    /**
     * Save the properties to the file.
     */
    @SneakyThrows
    public void save() {
        properties.store(new FileWriter(file), null);
    }

    /**
     * If the key exists, return the value, otherwise return null.
     *
     * @param key The key to check for.
     * @return The value of the key.
     */
    public boolean existing(String key) {
        return get(key) != null;
    }

}