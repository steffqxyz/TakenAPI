package xyz.steffq.takenapi.commandmanager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.steffq.takenapi.Taken;
import xyz.steffq.takenapi.utils.MiniColor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class TakenCommand extends BukkitCommand implements CommandExecutor {

    private List<String> delayedPlayers = null;
    private int delay = 0;
    private final int  minArguments;
    private final int maxArguments;
    private final boolean playerOnly;

    public TakenCommand(String command) {
        this(command, 0);
    }

    public TakenCommand(String command, boolean playerOnly) {
        this(command, 0, playerOnly);
    }

    public TakenCommand(String command, int requiredArguments) {
        this(command, requiredArguments, requiredArguments);
    }

    public TakenCommand(String command, int minArguments, int maxArguments) {
        this(command, minArguments, maxArguments, false);
    }

    public TakenCommand(String command, int requiredArguments, boolean playerOnly) {
        this(command, requiredArguments, requiredArguments, playerOnly);
    }

    public TakenCommand(String command, int minArguments, int maxArguments, boolean playerOnly) {
        super(command);

        this.minArguments = minArguments;
        this.maxArguments = maxArguments;
        this.playerOnly = playerOnly;
        CommandMap commandMap = getCommandMap();

        if (commandMap != null) {
            commandMap.register(command, this);
        }
    }

    public CommandMap getCommandMap() {
        CommandMap commandMap = null;
        try {
            Object pluginManager = Bukkit.getPluginManager();
            Field commandMapField = pluginManager.getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            commandMap = (CommandMap) commandMapField.get(pluginManager);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return commandMap;
    }

    public TakenCommand enableDelay(int delay) {
        this.delay = delay;
        this.delayedPlayers = new ArrayList<>();
        return this;
    }

    public void removeDelay(Player player) {
        this.delayedPlayers.remove(player.getName());
    }

    public void sendUsage(CommandSender sender) {
        MiniColor.ALL.deserialize(getUsage());
    }

    public boolean execute(CommandSender sender, String alias, String[] args) {

        if (args.length < minArguments || (args.length > maxArguments && maxArguments != -1)) {
            sendUsage(sender);
            return true;
        }

        if (playerOnly && !(sender instanceof Player)) {

            sender.sendMessage(MiniColor.ALL.deserialize("<red>Only players can execute this command."));

            return true;
        }

        String permission = getPermission();

        if (permission != null && !sender.hasPermission(permission)) {
            sender.sendMessage(MiniColor.ALL.deserialize("<dark_red>You do not have permission to execute this command."));
            return true;
        }
        if (delayedPlayers != null && sender instanceof Player) {
            Player player = (Player) sender;

            if (delayedPlayers.contains(player.getName())) {
                player.sendMessage(MiniColor.ALL.deserialize("<green>Please wait before using this command again."));
                return true;
            }

            delayedPlayers.add(player.getName());
            Bukkit.getScheduler().scheduleSyncDelayedTask(Taken.getPlugin(), () -> {
                delayedPlayers.remove(player.getName());
            }, 20L * delay);

        }

        if (!onCommand(sender, args)) {
            sendUsage(sender);
        }


        return true;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        return this.onCommand(sender, args);
    }

    public abstract boolean onCommand(CommandSender sender, String[] args);

    public abstract String getUsage();
    public abstract List<String> getAliases();
    public abstract String getDescription();



}
