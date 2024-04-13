package xyz.steffq.takenapi.menus.items;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.steffq.takenapi.menus.Menu;
import xyz.steffq.takenapi.utils.MiniColor;

public class MenuItem extends ItemStack {
    private Menu menu;
    private int slot;

    public MenuItem(Material material, int amount, short damage, String displayName, String... lore) {
        super(material, amount);
        ItemMeta meta = this.getItemMeta();
        if (meta != null) {
            if (displayName != null) {
                meta.displayName(MiniColor.TEXT.deserialize(displayName));
            }
            if (lore != null && lore.length > 0) {
                meta.lore(MiniColor.TEXT.deserialize(Arrays.asList(lore)));
            }

            setItemMeta(meta);
        }
        setDurability(damage);
    }

    public MenuItem(Material material) {
        this(material, 1, (short) 0, null);
    }

    public MenuItem(Material material, int amount, short damage) {
        this(material, amount, damage, null);
    }

    public MenuItem(Material material, String displayName, String... lore) {
        this(material, 1, (short) 0, displayName, lore);
    }

    public MenuItem(ItemStack stack) {
        this(stack.getType(), stack.getAmount(), stack.getDurability(), null);
        ItemMeta meta = stack.getItemMeta();
        if (meta != null) {
            setItemMeta(meta);
        }
    }

    public void onClick() {
        // Override to implement logic
    }

    public void onClick(int slot) {
        // Override to implement logic
    }

    public void setMenu(Menu menu, int slot) {
        this.menu = menu;
        this.slot = slot;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getSlot() {
        return slot;
    }

    public void setLore(String... lore) {
        ItemMeta meta = this.getItemMeta();
        if (meta != null) {
            meta.lore(MiniColor.TEXT.deserialize(Arrays.asList(lore)));
            setItemMeta(meta);
        }
        update();
    }

    public void update() {
        getMenu().setItem(getSlot(), this);
    }
}
