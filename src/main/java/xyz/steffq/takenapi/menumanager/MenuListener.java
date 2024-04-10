package xyz.steffq.takenapi.menumanager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import xyz.steffq.takenapi.TakenAPI;
import xyz.steffq.takenapi.exceptions.MenuManagerException;
import xyz.steffq.takenapi.exceptions.MenuManagerNotSetupException;
import xyz.steffq.takenapi.logger.TakenLog;
import xyz.steffq.takenapi.logger.TakenLogLevels;

public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {

        InventoryHolder holder = e.getInventory().getHolder();
        //If the inventoryholder of the inventory clicked on
        // is an instance of Menu, then gg. The reason that
        // an InventoryHolder can be a Menu is because our Menu
        // class implements InventoryHolder!!
        if (holder instanceof Menu) {
            if (e.getCurrentItem() == null) { //deal with null exceptions
                return;
            }
            //Since we know our inventoryholder is a menu, get the Menu Object representing
            // the menu we clicked on
            Menu menu = (Menu) holder;

            if (menu.cancelAllClicks()) {
                e.setCancelled(true); //prevent them from fucking with the inventory
            }

            //Call the handleMenu object which takes the event and processes it
            try {
                menu.handleMenu(e);
            } catch (MenuManagerNotSetupException ex) {
                TakenLog.log(TakenLogLevels.ERROR, "MenuManager not setup please do MenuManager.setup() in main class.");
            } catch (MenuManagerException menuManagerException) {
                menuManagerException.printStackTrace();
            }
        }

    }

}
