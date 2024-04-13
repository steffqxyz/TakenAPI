package xyz.steffq.takenapi.menus;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import xyz.steffq.takenapi.menus.items.MenuItem;
import xyz.steffq.takenapi.utils.MiniColor;

public class Menu implements InventoryHolder {
    private Inventory inventory;
    private Map<Integer, MenuItem> items = new HashMap<>();

    public Menu(String title, int rows) {
        this.inventory = Bukkit.createInventory(this, 9 * rows, MiniColor.ALL.deserialize(title));
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void openInventory(Player player) {
        player.openInventory(inventory);
    }

    public void setItem(int slot, MenuItem item) {
        item.setMenu(this, slot);
        inventory.setItem(slot, item);
        items.put(slot, item);
    }

    public MenuItem getItem(int slot) {
        return items.getOrDefault(slot, null);
    }

    public int getSize() {
        return inventory.getSize();
    }

    public void setBackground(Material material, short data, String displayName, String... lore) {
        MenuItem item = new MenuItem(material, 1, data, displayName, lore);
        for (int i = 0; i < getSize(); i++) {
            if (getItem(i) == null) {
                setItem(i, item);
            }
        }
    }
}
