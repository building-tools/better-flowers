package com.uroria.betterflowers.menus;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.data.BrushData;
import com.uroria.betterflowers.data.FlowerGroupData;
import com.uroria.betterflowers.managers.FlowerManager;
import com.uroria.betterflowers.managers.LanguageManager;
import com.uroria.betterflowers.utils.BukkitPlayerInventory;
import com.uroria.betterflowers.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public final class FlowerBrushMenu extends BukkitPlayerInventory {

    private final FlowerManager flowerManager;
    private final List<Material> globalMasks;
    private final List<FlowerGroupData> flowerGroupData;
    private final Player player;
    private final boolean isEditing;
    private int radius;
    private float airRandomizer;

    public FlowerBrushMenu(BetterFlowers betterFlowers, Player player, boolean isEditing) {
        super(LanguageManager.getComponent("gui.brush.title"), 4);

        this.flowerManager = betterFlowers.getFlowerManager();
        this.isEditing = isEditing;
        this.flowerGroupData = new ArrayList<>();
        this.globalMasks = new ArrayList<>();

        this.player = player;
        this.radius = 3;
        this.airRandomizer = 0.0f;

        generateOverlay();
    }

    public FlowerBrushMenu(BetterFlowers betterFlowers, Player player, BrushData brushData, boolean isEditing) {
        super(LanguageManager.getComponent("gui.brush.title"), 4);

        this.flowerManager = betterFlowers.getFlowerManager();
        this.flowerGroupData = new ArrayList<>(brushData.flowerGroupData());
        this.globalMasks = new ArrayList<>(brushData.globalMasks());

        this.player = player;
        this.isEditing = isEditing;
        this.radius = brushData.radius();
        this.airRandomizer = brushData.airRandomizer();

        generateOverlay();
    }

    public void open() {
        this.openInventory(player);
    }

    private void generateOverlay() {

        final var rad = String.valueOf(radius);
        final var air = String.valueOf(airRandomizer);

        this.setSlot(0, new ItemBuilder(Material.ECHO_SHARD)
                .setName("gui.brush.item.display.create")
                .build(), this::onCreateBrush);

        this.setSlot(1, new ItemBuilder(Material.ENDER_EYE)
                .setName("gui.brush.item.display.radius", "%radius%", rad)
                .build(), this::onChangeRadius);

        this.setSlot(2, new ItemBuilder(Material.STRUCTURE_VOID)
                .setName("gui.brush.item.display.air", "%air%", air)
                .build(), this::onChangeAirRandomizer);

        for (int index = 18; index < 27; index++) {
            this.setSlot(index, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).
                    setName("gui.brush.item.display.mask")
                    .setLore("gui.brush.item.mask.lore")
                    .build(), this::onMaskAdd);
        }

        for (int index = 27; index < 36; index++) {
            this.setSlot(index, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                    .setName("gui.brush.item.display.flower")
                    .setLore("gui.brush.item.flower.lore")
                    .build(), this::onFlowerAdd);
        }

        fillFlowers();
    }

    private ItemStack getFlowerItem(FlowerGroupData flowerGroupData) {

        for (var entry : flowerManager.getFlowers().entrySet()) {
            if (entry.getValue() == flowerGroupData) return entry.getKey();
        }

        return new ItemBuilder(Material.BARRIER).setName(Component.text("An error occurred")).build();
    }

    private void fillFlowers() {
        if (flowerGroupData.isEmpty()) return;

        int index = 0;
        for (var data : flowerGroupData) {
            this.setSlot(27 + index, getFlowerItem(data), this::onFlowerRemove);
            index++;
        }

        index = 0;
        for (var mask : globalMasks) {
            this.setSlot(18 + index, new ItemStack(mask), this::onMaskRemove);
            index++;
        }
    }

    private void onFlowerAdd(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        final var flowerItem = inventoryClickEvent.getCursor();
        if (flowerItem.getType() == Material.AIR) return;
        if (flowerItem.getItemMeta() == null || flowerItem.getType() != Material.BLAZE_POWDER) return;
        if (!flowerManager.getFlowers().containsKey(flowerItem)) return;

        final var slot = inventoryClickEvent.getSlot();
        final var flowers = flowerManager.getFlowers().get(flowerItem);
        if (flowerGroupData.contains(flowers)) return;

        this.flowerGroupData.add(flowers);
        this.setSlot(slot, flowerItem, this::onFlowerRemove);
    }

    private void onFlowerRemove(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        final var slot = inventoryClickEvent.getSlot();
        final var flowerItem = this.inventory.getItem(slot);

        if (flowerItem == null || flowerItem.getItemMeta() == null) return;
        if (flowerItem.getItemMeta() == null || flowerItem.getType() != Material.BLAZE_POWDER) return;
        if (!flowerManager.getFlowers().containsKey(flowerItem)) return;

        final var flowers = flowerManager.getFlowers().get(flowerItem);
        this.flowerGroupData.remove(flowers);
        this.setSlot(slot, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                .setName("gui.brush.item.display.placeholder")
                .setLore("gui.brush.item.flower.lore")
                .build(), this::onFlowerAdd);
    }

    private void onChangeAirRandomizer(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        if (inventoryClickEvent.isLeftClick()) airRandomizer += 0.1f;
        if (inventoryClickEvent.isRightClick()) airRandomizer -= 0.1f;
        if (airRandomizer > 1f) airRandomizer = 1f;
        if (airRandomizer < 0f) airRandomizer = 0f;

        final var air = String.valueOf(airRandomizer);
        this.setSlot(2, new ItemBuilder(Material.STRUCTURE_VOID)
                .setName("gui.brush.item.display.air", "%air%", air)
                .build(), this::onChangeAirRandomizer);
    }

    private void onChangeRadius(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        if (inventoryClickEvent.isLeftClick()) radius += 1;
        if (inventoryClickEvent.isRightClick()) radius -= 1;
        if (radius > 20) radius = 20;
        if (radius < 1) radius = 1;

        final var rad = String.valueOf(radius);
        this.setSlot(1, new ItemBuilder(Material.ENDER_EYE)
                .setName("gui.brush.item.display.radius", "%radius%", rad)
                .build(), this::onChangeRadius);
    }

    private void onCreateBrush(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        if (flowerGroupData.isEmpty()) return;
        if (radius >= 51) return;

        if (isEditing) {
            final var placer = player.getInventory().getItemInMainHand();
            this.flowerManager.getBrushes().put(placer, new BrushData(flowerGroupData, radius, airRandomizer, globalMasks));
            LanguageManager.sendPlayerMessage(player, "gui.brush.message.edit");
            player.getInventory().close();
            return;
        }

        final var currentMil = String.valueOf(System.currentTimeMillis());
        final var brushItem = new ItemBuilder(Material.BLAZE_ROD)
                .setName("gui.brush.item.display.brush", "%id%", currentMil)
                .setLore("gui.brush.item.mask.lore")
                .build();

        this.flowerManager.getBrushes().put(brushItem, new BrushData(flowerGroupData, radius, airRandomizer, globalMasks));

        LanguageManager.sendPlayerMessage(player, "gui.brush.message.create");
        this.player.getInventory().addItem(brushItem);
        this.player.getInventory().close();
    }

    private void onMaskAdd(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        final var slot = inventoryClickEvent.getSlot();
        final var maskItem = inventoryClickEvent.getCursor();

        if (maskItem.getItemMeta() == null) return;
        if (!maskItem.getType().isBlock() || maskItem.getType() == Material.AIR) return;
        final var maskItemType = maskItem.getType();

        if (globalMasks.contains(maskItemType)) return;
        this.globalMasks.add(maskItemType);
        this.setSlot(slot, new ItemBuilder(maskItemType)
                .setName("gui.brush.item.display.mask")
                .setLore("gui.brush.item.mask.lore")
                .build(), this::onMaskRemove);
    }

    private void onMaskRemove(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        final var slot = inventoryClickEvent.getSlot();
        final var maskItem = this.inventory.getItem(slot);
        if (maskItem == null || maskItem.getType() == Material.AIR) return;

        this.globalMasks.remove(maskItem.getType());
        this.setSlot(slot, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                .setName(("gui.brush.item.display.mask"))
                .build(), this::onMaskAdd);
    }

    private Optional<FlowerGroupData> getFlowerGroupFromMask(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        final var slot = inventoryClickEvent.getSlot();
        if (slot > 26) return Optional.empty();

        final var coherentSlot = slot + 9;
        final var flowerItem = this.inventory.getItem(coherentSlot);

        if (!flowerManager.getFlowers().containsKey(flowerItem)) return Optional.empty();
        return Optional.of(flowerManager.getFlowers().get(flowerItem));
    }
}