package it.voxibyte.voxiapi.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    private final ItemStack itemStack;

    public static ItemBuilder newItem(Material material) {
        return new ItemBuilder(material);
    }

    private ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder name(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(colorize(name));
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder quantity(int quantity) {
        itemStack.setAmount(quantity);

        return this;
    }

    public ItemBuilder enchantment(Enchantment enchantment, int level) {
        itemStack.addEnchantment(enchantment, level);

        return this;
    }

    public ItemBuilder lore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(colorize(lore));
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    public ItemBuilder lore(String... lore) {
        return lore(List.of(lore));
    }

    public ItemStack build() {
        return itemStack;
    }

    private String colorize(String name) {
        return ChatColor.translateAlternateColorCodes('&', name);
    }

    private List<String> colorize(List<String> input) {
        List<String> colorized = new ArrayList<>();
        for(String entry : input) {
            colorized.add(colorize(entry));
        }

        return colorized;
    }
}
