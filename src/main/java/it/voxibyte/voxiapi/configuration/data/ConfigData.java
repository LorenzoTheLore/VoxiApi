package it.voxibyte.voxiapi.configuration.data;

import it.voxibyte.voxiapi.annotation.VoxiConfig;

public class ConfigData {
    private final String path;
    private String fileName;

    public static ConfigData of(Class<?> configClass, VoxiConfig voxiConfig, String defaultPath) {
        return new ConfigData(configClass, voxiConfig, defaultPath);
    }

    private ConfigData(Class<?> configClass, VoxiConfig voxiConfig, String defaultPath) {
        this.path = voxiConfig.path().isEmpty() ? defaultPath : voxiConfig.path();
        this.fileName = voxiConfig.fileName().isEmpty() ? configClass.getSimpleName() : voxiConfig.fileName();

        this.fileName = this.fileName.endsWith(".yml") ? this.fileName : this.fileName + ".yml";
    }

    public String getPath() {
        return path;
    }

    public String getFileName() {
        return fileName;
    }
}
