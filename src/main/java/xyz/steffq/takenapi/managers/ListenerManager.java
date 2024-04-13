package xyz.steffq.takenapi.managers;

import org.bukkit.Server;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import xyz.steffq.takenapi.listeners.FreezeListener;
import xyz.steffq.takenapi.menus.listeners.MenuListener;

public class ListenerManager {

    public void registerMenuListener(Server server, Plugin plugin) {
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

    public void registerFreezeListener(Server server, Plugin plugin) {
        boolean isAlreadyRegistered = false;
        for (RegisteredListener rl : PlayerMoveEvent.getHandlerList().getRegisteredListeners()) {
            if (rl.getListener() instanceof FreezeListener) {
                isAlreadyRegistered = true;
                break;
            }
        }

        if (!isAlreadyRegistered) {
            server.getPluginManager().registerEvents(new FreezeListener(), plugin);
        }
    }

}
