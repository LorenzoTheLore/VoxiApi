package it.voxibyte.voxiapi.command;

import it.voxibyte.voxiapi.annotation.CommandMeta;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Optional;

public class BukkitCommandActor extends Command {
    private final VoxiCommand<?> voxiCommand;
    private final CommandMeta commandMeta;

    public BukkitCommandActor(VoxiCommand<?> voxiCommand, CommandMeta commandMeta) {
        super(commandMeta.name(), commandMeta.description(), commandMeta.usage(), Arrays.asList(commandMeta.aliases()));
        this.voxiCommand = voxiCommand;
        this.commandMeta = commandMeta;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!commandMeta.permission().isEmpty() && !commandSender.hasPermission(commandMeta.permission())) {
            voxiCommand.handleNoPermission(getSender(commandSender));
            return true;
        }

        if(commandMeta.playerOnly() && !(commandSender instanceof Player)) {
            voxiCommand.handleOnlyPlayers(getSender(commandSender));
            return true;
        }

        return voxiCommand.doExecute(getSender(commandSender), strings);
    }

    public <T extends CommandSender> T getSender(CommandSender commandSender) {
        return (T) commandSender;
    }
}
