package it.voxibyte.voxiapi.command;

import it.voxibyte.voxiapi.annotation.CommandMeta;
import it.voxibyte.voxiapi.exception.InitializationException;
import it.voxibyte.voxiapi.exception.VoxiException;
import it.voxibyte.voxiapi.util.ReflectionUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;

import static it.voxibyte.voxiapi.exception.Exceptions.raiseException;

public class CommandHandler {
    public void registerCommand(VoxiCommand<?> voxiCommand) {
        CommandMeta commandMeta = ReflectionUtil.getAnnotationOnClass(voxiCommand, CommandMeta.class);
        if(commandMeta == null) {
            raiseException(VoxiException.class, "invalid command class, " + voxiCommand.getClass().getName());
            return;
        }

        doRegisterCommand(voxiCommand, commandMeta);
    }

    private void doRegisterCommand(VoxiCommand<?> voxiCommand, CommandMeta commandMeta) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(commandMeta.name(), new BukkitCommandActor(voxiCommand, commandMeta));
        } catch(Exception e) {
            raiseException(InitializationException.class, "failed to initialize " + voxiCommand.getClass().getName());
        }
    }
}
