package it.voxibyte.voxiapi;

import it.voxibyte.voxiapi.annotation.EntryPoint;
import it.voxibyte.voxiapi.annotation.ExitPoint;
import it.voxibyte.voxiapi.command.CommandHandler;
import it.voxibyte.voxiapi.command.VoxiCommand;
import it.voxibyte.voxiapi.configuration.Configurator;
import it.voxibyte.voxiapi.exception.InitializationException;
import it.voxibyte.voxiapi.exception.VoxiException;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static it.voxibyte.voxiapi.exception.Exceptions.raiseException;
import static it.voxibyte.voxiapi.util.ReflectionUtil.getMethodAnnotated;

/**
 * Internal wrapper for {@link JavaPlugin JavaPlugin} handle
 */
public class PluginWrapper {
    private final JavaPlugin javaPlugin;
    private final CommandHandler commandHandler;

    private Configurator configurator;

    public PluginWrapper(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.commandHandler = new CommandHandler();
        try {
            this.configurator = new Configurator(javaPlugin.getClass());
        } catch (IOException e) {
            raiseException(InitializationException.class, "Failed to initialize configuration system");
        }
    }

    public void enablePlugin() {
        doEnable();
    }

    public void disablePlugin() {
        doDisable();
    }

    public void registerCommand(VoxiCommand<?> voxiCommand) {
        this.commandHandler.registerCommand(voxiCommand);
    }

    public Configurator getConfigurator() {
        return configurator;
    }

    private void doEnable() {
        Method enableMethod = getMethodAnnotated(javaPlugin, EntryPoint.class);
        try {
            if(enableMethod == null) {
                return;
            }

            enableMethod.invoke(javaPlugin);
        } catch (IllegalAccessException | InvocationTargetException e) {
            raiseException(VoxiException.class, "invalid EntryPoint declared");
        }
    }

    private void doDisable() {
        Method disableMethod = getMethodAnnotated(javaPlugin, ExitPoint.class);
        try {
            if(disableMethod == null) {
                return;
            }

            disableMethod.invoke(javaPlugin);
        } catch (IllegalAccessException | InvocationTargetException e) {
            raiseException(VoxiException.class, "invalid ExitPoint declared");
        }
    }
}
