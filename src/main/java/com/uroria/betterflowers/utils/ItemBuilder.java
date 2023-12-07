package com.uroria.betterflowers.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public final class ItemBuilder {

    private final ItemStack itemStack;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder setName(Component component) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(component);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(List<String> strings) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.lore(strings.stream().map(string -> MiniMessage.miniMessage().deserialize(string)).toList());
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }
}