package xyz.steffq.takenapi.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.steffq.takenapi.command.annotation.Command;
import xyz.steffq.takenapi.utils.MiniColor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public abstract class AnnotatedCommand extends org.bukkit.command.Command implements CommandExecutor {

    private final Map<String, Method> playerCommand = new HashMap<>();

    protected AnnotatedCommand(@NotNull String name) {
        super(name);

        findMethods();
    }

    private void findMethods() {
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Command.class)) {
                Command playerCommand = method.getDeclaredAnnotation(Command.class);

                int modifiers = method.getModifiers();

                if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                    throw new IllegalArgumentException("Method must be public and non-static");
                }

                this.playerCommand.put(playerCommand.name(), method);
            }
        }
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String s, @NotNull String[] args) {

        String param = args.length > 0 ? args[0] : "";

        if (playerCommand.containsKey(param)) {
            Method method = playerCommand.get(param);
            Command dcommand = method.getDeclaredAnnotation(Command.class);

            if (args.length < dcommand.minArguments() || args.length > dcommand.maxArguments()) {
                sender.sendMessage(MiniColor.ALL.deserialize("&cInvalid arguments"));
                return true;
            }

            if (!dcommand.permission().isEmpty() && !sender.hasPermission(dcommand.permission())) {
                sender.sendMessage(MiniColor.ALL.deserialize("&cYou do not have permission to execute this command"));
                return true;
            }

            if (dcommand.description().isEmpty()) return true;
            setDescription(dcommand.description());

            if (dcommand.requirePlayer() && !(sender instanceof Player)) {
                sender.sendMessage(MiniColor.ALL.deserialize("&cThis command can only be executed by a player"));
                return true;
            }

            try {
                method.invoke(this, sender, args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public void register() throws NoSuchFieldException, IllegalAccessException {
        Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        commandMapField.setAccessible(true);
        CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
        commandMap.register(getName(), this);
    }


}
