package xyz.steffq.takenapi;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.steffq.takenapi.logger.TakenLog;

public class Taken {

    private static TakenAPI api;

    public static void onLoad(@NotNull final TakenAPI plugin) {
        Taken.api = plugin;
        TakenLog.onLoad();
    }

    public static JavaPlugin getPlugin() {
        return (JavaPlugin) api;
    }

    public static TakenAPI getAPI() {
        return api;
    }


}
