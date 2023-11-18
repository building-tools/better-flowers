package com.uroria.betterflowers.utils;

import com.uroria.betterflowers.BetterFlowers;

import net.kyori.adventure.text.Component;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.function.Consumer;

public abstract class BukkitPlayerInventory {

    static {
        Bukkit.getPluginManager().registerEvents(new EventAdapter(), JavaPlugin.getPlugin(BetterFlowers.class));
    }

    protected static final Map<UUID, BukkitPlayerInventory> OPEN_INVENTORIES = new HashMap<>();
    protected final Map<Integer, Consumer<InventoryClickEvent>> clickActions;
    protected final Collection<Runnable> closeActions;
    protected final Inventory inventory;

    public BukkitPlayerInventory(Component title, final int rows) {
        if (title == null) title = Component.empty();

        this.clickActions = new HashMap<>();
        this.closeActions = new ArrayList<>();

        this.inventory = Bukkit.createInventory(null, rows * 9, title);
    }

    protected final void openInventory(Player player) {
        player.openInventory(inventory);
        OPEN_INVENTORIES.put(player.getUniqueId(), this);
    }

    protected final void setSlot(int slot, ItemStack itemStack) {
        this.clickActions.remove(slot);
        this.inventory.setItem(slot, itemStack);
    }

    protected final void setSlot(int slot, ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        if (consumer == null) {
            this.setSlot(slot, itemStack);
            return;
        }
        this.clickActions.put(slot, consumer);
        this.inventory.setItem(slot, itemStack);
    }

    protected final void addSlot(ItemStack itemStack) {
        this.inventory.addItem(itemStack);
        int slot = this.inventory.first(itemStack);
        this.clickActions.remove(slot);
    }

    protected final void addSlot(ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        if (consumer == null) {
            this.addSlot(itemStack);
            return;
        }
        int slot = this.inventory.first(itemStack);
        this.clickActions.put(slot, consumer);
        this.inventory.setItem(slot, itemStack);
    }

    protected final void clearSlot(int slot) {
        this.inventory.clear(slot);
        this.clickActions.remove(slot);
    }

    protected final void clearSlots() {
        this.inventory.clear();
        this.clickActions.clear();
    }

    private static final class EventAdapter implements Listener {

        @EventHandler (priority = EventPriority.LOW)
        public void onInventoryClickEvent(InventoryClickEvent inventoryClickEvent) {
            if (!(inventoryClickEvent.getWhoClicked() instanceof Player player)) return;
            if (inventoryClickEvent.getClickedInventory() == null) return;
            if (inventoryClickEvent.getCurrentItem() == null) return;
            if (!OPEN_INVENTORIES.containsKey(player.getUniqueId())) return;

            final BukkitPlayerInventory currentInventory = OPEN_INVENTORIES.get(player.getUniqueId());
            final int slot = inventoryClickEvent.getSlot();

            if (!inventoryClickEvent.getClickedInventory().equals(currentInventory.inventory)) return;
            if (!currentInventory.clickActions.containsKey(slot)) return;

            currentInventory.clickActions.get(slot).accept(inventoryClickEvent);
            player.playSound(player, Sound.UI_BUTTON_CLICK, 1, 1);
        }

        @EventHandler
        public void onInventoryCloseEvent(InventoryCloseEvent inventoryCloseEvent) {
            if (!(inventoryCloseEvent.getPlayer() instanceof Player player)) return;
            if (!(OPEN_INVENTORIES.containsKey(player.getUniqueId()))) return;

            final BukkitPlayerInventory currentInventory = OPEN_INVENTORIES.get(player.getUniqueId());
            if(!inventoryCloseEvent.getInventory().equals(currentInventory.inventory)) return;
            currentInventory.closeActions.forEach(Runnable::run);

            OPEN_INVENTORIES.remove(player.getUniqueId());
        }
    }
}