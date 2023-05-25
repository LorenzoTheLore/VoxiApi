package it.voxibyte.voxiapi.command;

import it.voxibyte.voxiapi.annotation.CommandMeta;
import org.bukkit.command.CommandSender;

/**
 * Representation of a Bukkit command, simplified to make command handling less painful
 *
 * @param <T> type of sender, use Player only if {@link CommandMeta#playerOnly()} is set to true
 */
public interface VoxiCommand<T extends CommandSender> {
    boolean execute(T sender, String[] args);

    default void handleNoPermission(CommandSender sender) {
        sender.sendMessage("You have not enough permissions!");
    }

    default void handleOnlyPlayers(CommandSender sender) {
        sender.sendMessage("This command is for players only");
    }
}
