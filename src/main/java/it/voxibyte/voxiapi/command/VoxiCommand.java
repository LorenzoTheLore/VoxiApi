package it.voxibyte.voxiapi.command;

import it.voxibyte.voxiapi.annotation.CommandMeta;
import it.voxibyte.voxiapi.command.abstr.ICommand;
import org.bukkit.command.CommandSender;

import java.util.Optional;

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

    @Override
    public void addArgument(VoxiArgument<T> voxiArgument) {
        super.addArgument(voxiArgument);
    }

    @Override
    public Optional<VoxiArgument<T>> findArgument(String name) {
        return super.findArgument(name);
    }
}
