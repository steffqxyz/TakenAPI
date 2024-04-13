package xyz.steffq.takenapi.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.steffq.takenapi.utils.MiniColor;

import javax.sound.midi.MidiFileFormat;
import java.util.List;

public class ItemCreator {

    public static ItemStack of(Material material, int amount, String displayName, List<String> lore) {
        return of(material, amount, displayName, lore, false, false, null, false, 0);
    }

    public static ItemStack of(Material material, int amount, String displayName, List<String> lore, boolean glow) {
        return of(material, amount, displayName, lore, glow, false, null, false, 0);
    }

    public static ItemStack of(Material material, int amount, String displayName, List<String> lore, boolean glow, boolean unbreakable) {
        return of(material, amount, displayName, lore, glow, unbreakable, null, false, 0);
    }


    public static ItemStack of(
            Material material,
            int amount,
            String displayName,
            List<String> lore,
            boolean glow,
            boolean unbreakable,
            List<Enchantment> enchants, boolean levelRestriction, int level
    )
    {

        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(MiniColor.TEXT.deserialize(displayName));
        meta.lore(MiniColor.TEXT.deserialize(lore));

        if (glow) {
            meta.addEnchant(Enchantment.LUCK, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        if (unbreakable) {
            meta.setUnbreakable(true);
        }
        if (enchants != null) {
            for (Enchantment enchant : enchants) {
                meta.addEnchant(enchant, level, levelRestriction);
            }
        }

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack inInventory(
            Inventory inventory,
            int slot,
            Material material,
            int amount,
            String displayName,
            List<String> lore,
            boolean glow,
            boolean unbreakable,
            List<Enchantment> enchants, boolean levelRestriction, int level
    )
    {
        ItemStack item = of(material, amount, displayName, lore, glow, unbreakable, enchants, levelRestriction, level);
        inventory.setItem(slot, item);
        return item;
    }

    public static ItemStack inInventory(
            Inventory inventory,
            int slot,
            Material material,
            int amount,
            String displayName,
            List<String> lore
    )
    {
        ItemStack item = of(material, amount, displayName, lore);
        inventory.setItem(slot, item);
        return item;
    }

    public static ItemStack inInventory(
            Inventory inventory,
            int slot,
            Material material,
            int amount,
            String displayName,
            List<String> lore,
            boolean glow
    )
    {
        ItemStack item = of(material, amount, displayName, lore, glow);
        inventory.setItem(slot, item);
        return item;
    }

    public static ItemStack inInventory(
            Inventory inventory,
            int slot,
            Material material,
            int amount,
            String displayName,
            List<String> lore,
            boolean glow,
            boolean unbreakable
    )
    {
        ItemStack item = of(material, amount, displayName, lore, glow, unbreakable);
        inventory.setItem(slot, item);
        return item;
    }


}
