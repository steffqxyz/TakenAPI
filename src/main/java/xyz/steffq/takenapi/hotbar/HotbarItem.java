package xyz.steffq.takenapi.hotbar;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.steffq.takenapi.utils.MiniColor;

import java.util.List;

public class HotbarItem extends ItemStack {
    public void onInteract(Player player) {
        // Override to implement logic
    }

    public void onInteract(Player player, Entity target) {
        // Override to implement logic
    }

    public HotbarItem(Material material, String name, int amount, short damage, List<String> lore) {
        super(material, amount, damage);

        ItemMeta meta = getItemMeta();
        if (meta != null) {
            meta.displayName(MiniColor.ALL.deserialize(name));
            meta.lore(MiniColor.ALL.deserialize(lore));
            setItemMeta(meta);
        }
    }
}
