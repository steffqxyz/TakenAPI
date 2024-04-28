package xyz.steffq.takenapi.command;

import org.bukkit.command.CommandSender;

import java.util.List;

@FunctionalInterface
public interface CommandList {

    /**
     * @param sender         The thing that ran the command
     * @param subCommandList A list of all the subcommands you can display
     */
    void displayCommandList(CommandSender sender, List<SubCommand> subCommandList);

}