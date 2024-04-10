package xyz.steffq.takenapi.menumanager;

import org.bukkit.Server;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import xyz.steffq.takenapi.logger.TakenLog;
import xyz.steffq.takenapi.logger.TakenLogLevels;

public class MenuManager {

    private static boolean isSetup = false;

    private static void registerMenuListener(Server server, Plugin plugin) {
        boolean isAlreadyRegistered = false;
        for (RegisteredListener rl : InventoryClickEvent.getHandlerList().getRegisteredListeners()) {
            if (rl.getListener() instanceof MenuListener) {
                isAlreadyRegistered = true;
                break;
            }
        }

        if (!isAlreadyRegistered) {
            server.getPluginManager().registerEvents(new MenuListener(), plugin);
        }
    }

    public static void setup(Server server, Plugin plugin) {
        TakenLog.log(TakenLogLevels.SUCCESS, "MenuManager setup.");

        registerMenuListener(server, plugin);
        isSetup = true;
    }



}
