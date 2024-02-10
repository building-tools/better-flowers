package com.uroria.betterflowers.utils;

import com.uroria.betterflowers.managers.LanguageManager;
import net.kyori.adventure.text.Component;
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

    public ItemBuilder setName(String key) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(LanguageManager.getComponent(key));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setName(String key, String replace, String replacement) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(LanguageManager.getComponent(key, replace, replacement));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(List<Component> components) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.lore(components);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(String key) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.lore(LanguageManager.getComponents(key));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }
}