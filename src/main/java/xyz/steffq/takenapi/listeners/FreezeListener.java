package xyz.steffq.takenapi.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import xyz.steffq.takenapi.Taken;
import xyz.steffq.takenapi.TakenAPI;
import xyz.steffq.takenapi.managers.FreezeManager;

public class FreezeListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onFreeze(PlayerMoveEvent e) {
        Taken.getAPI().getFreezeManager().preventMovement(e);
    }

}
