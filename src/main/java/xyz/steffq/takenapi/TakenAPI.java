package xyz.steffq.takenapi;

import xyz.steffq.takenapi.configuration.YamlConfig;
import xyz.steffq.takenapi.hotbar.HotbarManager;
import xyz.steffq.takenapi.managers.InventoryManager;
import xyz.steffq.takenapi.managers.FreezeManager;
import xyz.steffq.takenapi.managers.ListenerManager;

import javax.annotation.Nullable;

public interface TakenAPI {

    /*
     * Get the main config.yml file
     */
    @Nullable
    YamlConfig getConfigYml();
    /*
        * Get the FreezeManager instance
        * For now the FreezeManager has 1 method:
        * - preventMovement -> if you want to freeze a player then you will need to call this in the main class
        * @param e -> PlayerMoveEvent
     */
    @Nullable
    FreezeManager getFreezeManager();
    /*
     * Get the ListenerManager instance
     * For now the ListenerManager has 2 methods:
     * - registerMenuListener -> if you create a menu from my API then you will need to call this in the main class
     * @param server -> Bukkit.getServer()
     * @param plugin -> mainClass
     * - registerFreezeListener -> if you want to freeze a player then you will need to call this in the main class
     * @param server -> Bukkit.getServer()
     * @param plugin -> mainClass
     * 2nd option: you can make your own listener and register it and call getFreezeManager().preventMovement(e); then register it into the main class
     */
    @Nullable
    ListenerManager getListenerManager();
    /*
     * Get the HotbarManager instance
     * For now the HotbarManager has 4 methods:
     * - setHotbar -> set the hotbar for the player
     * @param player -> Player
     * @param hotbar -> make a class that extends Hotbar
     * - getHotbar -> get the hotbar for the player
     * @param player -> Player
     * - removeHotbar -> remove the hotbar for the player
     * @param player -> Player
     * - isHotbarItem -> check if the item is in the hotbar
     * @param player -> Player
     * @param slot -> int
     */
    @Nullable
    HotbarManager getHotbarManager();
    /*
     * Get the InventoryManager instance
     * For now the InventoryManager has 3 methods:
     * - savePlayerInventory -> save the player inventory onto a file
     * @param player -> Player
     * - loadPlayerInventory -> load the player inventory from a file
     * @param player -> Player
     * - deletePlayerInventory -> delete the player inventory from a file
     * @param player -> Player
     * I suggest calling loadPlayerInventory then deletePlayerInventory to prevent any issues and to clear up space
     */
    @Nullable
    InventoryManager getInventoryManager();


}
