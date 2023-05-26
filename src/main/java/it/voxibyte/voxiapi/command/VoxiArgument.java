package it.voxibyte.voxiapi.command;

import it.voxibyte.voxiapi.command.abstr.ICommand;
import org.bukkit.command.CommandSender;

public abstract class VoxiArgument<T extends CommandSender> extends Argumented<T> implements ICommand<T> {
    private final String name;

    protected VoxiArgument(String name) {
        this.name = name;
    }

    boolean doExecute(T sender, String[] args) {
        if(args.length == 0) {
            return execute(sender, args);
        }

        boolean executedArgument = tryDelegateArgument(sender, args[0], args);

        return executedArgument || execute(sender, args);
    }

    public String getName() {
        return name;
    }

}
