package xyz.steffq.takenapi.logger;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import xyz.steffq.takenapi.Taken;
import xyz.steffq.takenapi.configuration.YamlConfig;
import xyz.steffq.takenapi.utils.MiniColor;

public class TakenLog {

    private static boolean allowFormatting;
    private static boolean debugMode;
    private static String debugPrefix;
    private static String prefix;
    private static String divider;

    static {
        allowFormatting = true;
        debugMode = false;
    }

    public static void onLoad() {
        final YamlConfig config = Taken.getAPI().getConfigYml();
        allowFormatting = config.options().getBoolean("logging.formatting", true);
        debugMode = config.options().getBoolean("logging.debug-mode", false);
        debugPrefix = config.options().getString("logging.debug-prefix", "<yellow>i<reset>");
        prefix = config.options().getString("logging.prefix", "[TakenAPI] ");
        divider = config.options().getString("logging.divider", "-----------------------------------");
    }

    private static void send(@NotNull final TakenLogLevels level, final boolean debug, @NotNull final String... input) {

        if (debug) {
            if (!debugMode) {
                return;
            }

            prefix += debugPrefix;
        }

        for (final String i : input) {
            String message = prefix + level.color + i;

            if (!allowFormatting) {
                message = MiniColor.COLOR.strip(message);
            }

            final Component component = MiniColor.COLOR.deserialize(message);
            Bukkit.getConsoleSender().sendMessage(component);
        }
    }

    public static void log(@NotNull final TakenLogLevels level, @NotNull final String... input) {
        send(level, false, input);
    }

    public static void log(@NotNull final String... input) {
        send(TakenLogLevels.INFO, false, input);
    }

    public static void debug(@NotNull final TakenLogLevels level, @NotNull final String... input) {
        send(level, true, input);
    }

    public static void debug(@NotNull final String... input) {
        send(TakenLogLevels.INFO, true, input);
    }

    public static void divider(@NotNull final TakenLogLevels level, final boolean debug) {
        send(level, debug, divider);
    }

    public static void divider(@NotNull final TakenLogLevels level) {
        send(level, false, divider);
    }

    public static void divider(final boolean debug) {
        send(TakenLogLevels.INFO, debug, divider);
    }

    public static void divider() {
        send(TakenLogLevels.INFO, false, divider);
    }
}