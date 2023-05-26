package it.voxibyte.voxiapi.command.abstr;

import it.voxibyte.voxiapi.command.VoxiArgument;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.function.Supplier;

public interface ICommand<T extends CommandSender> {
    boolean execute(T sender, String[] args);

    List<String> tabComplete(T sender, String[] args);

    default void handleNoPermission(T sender) {
        sender.sendMessage("You have not enough permissions!");
    }

    default void handleOnlyPlayers(CommandSender sender) {
        sender.sendMessage("This command is for players only");
    }
}
