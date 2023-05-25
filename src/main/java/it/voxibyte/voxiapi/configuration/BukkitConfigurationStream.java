package it.voxibyte.voxiapi.configuration;


import it.voxibyte.voxiapi.VoxiPlugin;
import it.voxibyte.voxiapi.annotation.VoxiConfig;
import it.voxibyte.voxiapi.configuration.data.ConfigData;
import it.voxibyte.voxiapi.configuration.io.ConfigReader;
import it.voxibyte.voxiapi.configuration.io.ConfigWriter;
import it.voxibyte.voxiapi.util.FileUtil;
import it.voxibyte.voxiapi.util.ReflectionUtil;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

public class BukkitConfigurationStream<T> {
    private final Class<T> configurationClass;

    private T cachedInstance;
    private ConfigReader configReader;
    private ConfigWriter configWriter;

    public BukkitConfigurationStream(Class<T> configurationClass) {
        this.configurationClass = configurationClass;

        verifyConfig(configurationClass);
    }

    public T retrieveInstance() {
        if(cachedInstance == null) {
            try {
                this.cachedInstance = configReader.read(configurationClass);
            } catch (FileNotFoundException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return cachedInstance;
    }

    public void save(T instance) {
        try {
            configWriter.write(instance);
            this.cachedInstance = instance;
        } catch (IllegalAccessException | FileNotFoundException e) {
            throw new RuntimeException("Failed to save " + configurationClass.getSimpleName());
        }
    }

    private void verifyConfig(Class<?> configurationClass) {
        Optional<VoxiConfig> voxiConfig = ReflectionUtil.getAnnotatedClass(configurationClass, VoxiConfig.class);
        if(voxiConfig.isEmpty()) {
            throw new UnsupportedOperationException(configurationClass.getSimpleName() + " is not a valid me.lorenzo.voxi.config");
        }

        ConfigData configData = ConfigData.of(configurationClass, voxiConfig.get(), VoxiPlugin.getJavaPlugin().getDataFolder().getAbsolutePath());
        File configurationFile = new File(configData.getPath(), configData.getFileName());

        boolean isNew = FileUtil.checkFile(configurationFile);

        this.configReader = new ConfigReader(configurationFile);
        this.configWriter = new ConfigWriter(configurationFile);

        if(isNew) {
            writeDefaults();
        } else {
            readConfig();
        }
    }

    private void readConfig() {
        retrieveInstance();
    }

    private void writeDefaults() {
        save(ReflectionUtil.obtainInstance(configurationClass));
    }
}
