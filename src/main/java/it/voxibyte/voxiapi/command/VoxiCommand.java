package it.voxibyte.voxiapi.command;

import it.voxibyte.voxiapi.annotation.CommandMeta;
import it.voxibyte.voxiapi.command.abstr.ICommand;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Representation of a Bukkit command, simplified to make command handling less painful
 *
 * @param <T> type of sender, use Player only if {@link CommandMeta#playerOnly()} is set to true
 */
public abstract class VoxiCommand<T extends CommandSender> extends Argumented<T> implements ICommand<T> {

    boolean doExecute(T sender, String[] args) {
        if(args.length == 0) {
            return execute(sender, args);
        }

        boolean executedArgument = tryDelegateArgument(sender, args[0], args);

        return executedArgument || execute(sender, args);
    }

    List<String> doTabComplete(T sender, String[] args) {
        if(args.length == 1) {
            return tabComplete(sender, args);
        }

        return delegateTabComplete(sender, args);
    }


    @Override
    public List<String> tabComplete(T sender, String[] args) {
        return getArguments().stream().map(VoxiArgument::getName).collect(Collectors.toList());
    }
}
