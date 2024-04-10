package xyz.steffq.takenapi.menumanager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.steffq.takenapi.exceptions.MenuManagerException;
import xyz.steffq.takenapi.exceptions.MenuManagerNotSetupException;
import xyz.steffq.takenapi.items.ItemCreator;
import xyz.steffq.takenapi.utils.MiniColor;

public abstract class Menu implements InventoryHolder {

    protected Player p;
    protected Inventory inventory;
    protected ItemStack FILLER_GLASS = ItemCreator.of(Material.GRAY_STAINED_GLASS_PANE, 1, "", null);

    public Menu(Player p) {
        this.p = p;
    }

    public abstract String menuName();
    public abstract int menuSize();
    public abstract boolean cancelAllClicks();
    public abstract void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException;
    public abstract void menuItems();

    public void open() {
        inventory = Bukkit.createInventory(this, menuSize(), MiniColor.ALL.deserialize(menuName()));
        this.menuItems();
        p.openInventory(inventory);
    }

    protected void reloadItems() {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, null);
        }
        menuItems();
    }


    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public void setFillerGlass() {
        for (int i = 0; i < menuSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, FILLER_GLASS);
            }
        }
    }

    /**
     * @param itemStack Placed into every empty slot when ran
     */
    public void setFillerGlass(ItemStack itemStack) {
        for (int i = 0; i < menuSize(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, itemStack);
            }
        }
    }


}
