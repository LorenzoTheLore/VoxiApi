package it.voxibyte.voxiapi;

import it.voxibyte.voxiapi.command.VoxiCommand;
import it.voxibyte.voxiapi.configuration.Configurator;
import it.voxibyte.voxiapi.exception.VoxiException;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static it.voxibyte.voxiapi.exception.Exceptions.raiseException;

/**
 * Provides a wrapped version of JavaPlugin with common utilities and handles correctly all the logics for VoxiApi
 * <p>
 * <strong>Note:</strong> This plugin does not required a plugin.yml file
 */
public abstract class VoxiPlugin extends JavaPlugin {
    private static JavaPlugin javaPlugin;

    private PluginWrapper pluginWrapper;
    private PluginManager pluginManager;

    public static JavaPlugin getJavaPlugin() {
        if(javaPlugin == null) raiseException(VoxiException.class, "plugin not loaded yet");
        return javaPlugin;
    }

    @Override
    @Deprecated(since = "0.1-SNAPSHOT")
    public final void onLoad() {
        javaPlugin = this;
        this.pluginWrapper = new PluginWrapper(javaPlugin);
        this.pluginManager = Bukkit.getPluginManager();
    }

    /**
     * Default entry point of spigot plugins
     *
     * @deprecated use the {@link it.voxibyte.voxiapi.annotation.EntryPoint EntryPoint} annotation on a method of your
     * choice instead.
     */
    @Override
    @Deprecated(since = "0.1-SNAPSHOT")
    public final void onEnable() {
        this.pluginWrapper.enablePlugin();
    }

    /**
     * Default exit point of spigot plugins
     *
     * @deprecated use the {@link it.voxibyte.voxiapi.annotation.ExitPoint ExitPoint} annotation on a method of your
     * choice instead.
     */
    @Override
    @Deprecated(since = "0.1-SNAPSHOT")
    public void onDisable() {
        this.pluginWrapper.disablePlugin();
    }

    /**
     * Default command registration for spigot API
     *
     * @deprecated usage of {@link VoxiPlugin#registerCommand(VoxiCommand)} is recommended
     */
    @Override
    @Deprecated(since = "0.1-SNAPSHOT")
    public PluginCommand getCommand(String name) {
        return super.getCommand(name);
    }

    /**
     * Register a custom implementation of Command, it will be automatically registered for Bukkit
     * <strong>Note:</strong> It is not necessary to register this command
     *
     * @param voxiCommand command to be registered
     */
    public void registerCommand(VoxiCommand<?> voxiCommand) {
        pluginWrapper.registerCommand(voxiCommand);
    }

    /**
     * Setup a Bukkit {@link Listener Listener} on the current plugin instance
     *
     * @param listener Listener to be registered
     */
    public void registerListener(Listener listener) {
        pluginManager.registerEvents(listener, javaPlugin);
    }

    /**
     * Provides access to {@link Configurator Configurator} instance for enchanced configuration handling
     *
     * @return Configurator instance ready to use
     */
    public Configurator getConfigurator() {
        return pluginWrapper.getConfigurator();
    }

    @Override
    @Deprecated(since = "0.1-SNAPSHOT")
    public FileConfiguration getConfig() {
        return super.getConfig();
    }
}
