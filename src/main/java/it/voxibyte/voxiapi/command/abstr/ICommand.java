package it.voxibyte.voxiapi.command.abstr;

import it.voxibyte.voxiapi.command.VoxiArgument;
import org.bukkit.command.CommandSender;

public interface ICommand<T extends CommandSender> {
    boolean execute(T sender, String[] args);

    default void handleNoPermission(T sender) {
        sender.sendMessage("You have not enough permissions!");
    }

    default void handleOnlyPlayers(CommandSender sender) {
        sender.sendMessage("This command is for players only");
    }
}
