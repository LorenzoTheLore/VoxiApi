package it.voxibyte.voxiapi.configuration;


import it.voxibyte.voxiapi.VoxiPlugin;
import it.voxibyte.voxiapi.annotation.VoxiConfig;
import it.voxibyte.voxiapi.util.ReflectionUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Configurator {
    private final Map<Class<?>, BukkitConfigurationStream<?>> configurations;
    private final Class<?> rootClass;

    public Configurator(Class<?> rootClass) throws IOException {
        this.configurations = new HashMap<>();
        this.rootClass = rootClass;

        initialize();
    }

    public <T> void save(T configuration) {
        BukkitConfigurationStream<T> configurationStream = (BukkitConfigurationStream<T>) configurations.get(configuration.getClass());
        configurationStream.save(configuration);
    }

    public <T> T get(Class<T> configurationClass) {
        return (T) configurations.get(configurationClass).retrieveInstance();
    }

    private void initialize() throws IOException {
        ClassLoader classLoader = rootClass.getClassLoader();
        Set<Class<?>> configClasses = ReflectionUtil.getClassesAnnotated(classLoader, VoxiConfig.class, VoxiPlugin.getJavaPlugin().getClass().getPackageName());

        for(Class<?> configClass : configClasses) {
            this.configurations.put(configClass, new BukkitConfigurationStream<>(configClass));
        }
    }
}
