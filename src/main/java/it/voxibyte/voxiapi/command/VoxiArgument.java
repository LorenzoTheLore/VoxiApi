package it.voxibyte.voxiapi.command;

import it.voxibyte.voxiapi.command.abstr.ICommand;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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

    public String getName() {
        return name;
    }

}
