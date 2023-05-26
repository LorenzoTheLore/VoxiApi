package it.voxibyte.voxiapi.command;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Argumented<T extends CommandSender> {
    private final List<VoxiArgument<T>> argumentList;

    public Argumented() {
        this.argumentList = new ArrayList<>();
    }

    public void addArgument(VoxiArgument<T> voxiArgument) {
        this.argumentList.add(voxiArgument);
    }

    public Optional<VoxiArgument<T>> findArgument(String name) {
        return argumentList.stream()
                .findAny()
                .filter(argument -> argument.getName().equalsIgnoreCase(name));
    }

    public List<VoxiArgument<T>> getArguments() {
        return argumentList;
    }

    boolean tryDelegateArgument(T sender, String name, String[] args) {
        Optional<VoxiArgument<T>> argumentOptional = findArgument(name);

        if (argumentOptional.isEmpty()) {
            return false;
        }

        VoxiArgument<T> voxiArgument = argumentOptional.get();
        String[] relativeArguments = new String[args.length - 1];
        System.arraycopy(args, 1, relativeArguments, 0, args.length - 1);

        return voxiArgument.doExecute(sender, relativeArguments);
    }

    List<String> delegateTabComplete(T sender, String[] args) {
        String name = args[0];
        Optional<VoxiArgument<T>> argumentOptional = findArgument(name);

        if (argumentOptional.isEmpty()) {
            return new ArrayList<>();
        }

        VoxiArgument<T> voxiArgument = argumentOptional.get();
        String[] relativeArguments = new String[args.length - 1];
        System.arraycopy(args, 1, relativeArguments, 0, args.length - 1);

        return voxiArgument.doTabComplete(sender, relativeArguments);
    }
}
