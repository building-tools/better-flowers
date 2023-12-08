package com.uroria.betterflowers.menus;

import com.uroria.betterflowers.BetterFlowers;
import com.uroria.betterflowers.data.FlowerGroupData;
import com.uroria.betterflowers.flowers.SingleFlower;
import com.uroria.betterflowers.flowers.placable.FlowerGroup;
import com.uroria.betterflowers.managers.LanguageManager;
import com.uroria.betterflowers.utils.BukkitPlayerInventory;
import com.uroria.betterflowers.utils.FlowerCollection;
import com.uroria.betterflowers.data.FlowerData;
import com.uroria.betterflowers.utils.ItemBuilder;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class FlowerCreationMenu extends BukkitPlayerInventory {

    private final LanguageManager languageManager;
    private final List<FlowerData> personalFlower;
    private final List<Boolean> isGroup;
    private final List<Boolean> randomizer;
    private final Player player;
    private final ItemStack active;
    private final ItemStack notActive;
    private final ItemStack wholeCategoryRan;
    private final ItemStack wholeCategory;
    private final BetterFlowers betterFlowers;

    public FlowerCreationMenu(Player player, BetterFlowers betterFlowers) {
        super(betterFlowers.getLanguageManager().getComponent("gui.flower.title"), 6);

        this.player = player;
        this.betterFlowers = betterFlowers;
        this.languageManager = betterFlowers.getLanguageManager();

        this.personalFlower = new ArrayList<>();
        this.randomizer = new ArrayList<>();
        this.isGroup = new ArrayList<>();

        this.active = new ItemBuilder(Material.LIME_STAINED_GLASS_PANE).setName(languageManager.getComponent("gui.flower.item.display.randomizer.yes.no")).build();
        this.notActive = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName(languageManager.getComponent("gui.flower.item.display.group.no.no")).build();
        this.wholeCategoryRan = new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName(languageManager.getComponent("gui.flower.item.display.randomizer.yes.yes")).build();
        this.wholeCategory = new ItemBuilder(Material.MAGENTA_STAINED_GLASS_PANE).setName(languageManager.getComponent("gui.flower.item.display.randomizer.no.yes")).build();
    }

    public void open() {

        this.closeActions.add(() -> {
            personalFlower.clear();
            randomizer.clear();
            isGroup.clear();
        });

        generateCategories();
        openInventory(player);
    }

    private void generateFlowerOverlay() {

        //generates placeholder
        for (var index = 27; index < 54; index++) {
            if (index >= 36 && index <= 44) continue;
            this.setSlot(index, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                    .setName(languageManager.getComponent("gui.flower.item.display.placeholder"))
                    .build(), this::cancelClick);
        }

        //generates the display for the randomizer
        for (var index = 0; index < 9; index++) {
            if (index >= randomizer.size() || index >= isGroup.size()) break;
            if (isGroup.get(index) && randomizer.get(index)) setSlot(45 + index, wholeCategoryRan, this::cancelClick);
            if (isGroup.get(index) && !randomizer.get(index)) setSlot(45 + index, wholeCategory, this::cancelClick);
            if (!isGroup.get(index) && randomizer.get(index)) setSlot((45 + index), active, this::cancelClick);
            if (!isGroup.get(index) && !randomizer.get(index)) setSlot((45 + index), notActive, this::cancelClick);
        }

        //generates the chosen list of flowers to display the current flower list
        for (var index = 0; index < personalFlower.size(); index++) {
            final var singleFlower = personalFlower.get(index);

            setSlot((36 + index), new ItemBuilder(singleFlower.getDisplay())
                    .setName(languageManager.getComponent("gui.flower.item.display.flower", "%flower%",singleFlower.getName() + " ID " + index))
                    .build(), this::cancelClick);
        }

        setSlot(29, new ItemBuilder(Material.ECHO_SHARD)
                .setName(languageManager.getComponent("gui.flower.item.display.create"))
                .build(), this::onCreateClick);

        setSlot(30, new ItemBuilder(Material.STRUCTURE_VOID)
                .setName(languageManager.getComponent("gui.flower.item.display.back"))
                .build(), this::onBackClick);

        setSlot(32, new ItemBuilder(Material.BARRIER)
                .setName(languageManager.getComponent("gui.flower.item.display.delete"))
                .build(), this::onDeleteClick);

        setSlot(33, new ItemBuilder(Material.REDSTONE)
                .setName(languageManager.getComponent("gui.flower.item.display.remove"))
                .build(), this::onRemoveClick);
    }

    private void generateCategories() {

        clearSlots();
        generateFlowerOverlay();

        List<FlowerCollection> flowers = List.copyOf(Arrays.stream(FlowerCollection.values()).toList());

        for (int index = 0; index < 28; index++) {

            if (index >= flowers.size()) break;

            final var currentFlowers = flowers.get(index).getFlowerGroup();

            setSlot(index, new ItemBuilder(currentFlowers.getDisplay())
                            .setName(languageManager.getComponent("gui.flower.item.display.flower", "%flower%", currentFlowers.getDisplayName()))
                            .setLore(languageManager.getComponents("gui.flower.item.lore.flowers")).build(),
                    inventoryClickEvent -> onCategoryClick(inventoryClickEvent, currentFlowers)
            );
        }
    }

    private void generateSubCategories(FlowerGroup flowerGroup) {

        clearSlots();
        generateFlowerOverlay();

        for (int index = 0; index < 53; index++) {

            if (index >= flowerGroup.getFlowers().size()) break;

            final var singleFlower = flowerGroup.getFlowers().get(index);

            setSlot(index, new ItemBuilder(singleFlower.getDisplay())
                            .setName(languageManager.getComponent("gui.flower.item.display.flower", "%flower%", singleFlower.getDisplayName()))
                            .setLore(languageManager.getComponents("gui.flower.item.lore.flower")).build(),
                    inventoryClickEvent -> onSubCategoryClick(inventoryClickEvent, singleFlower)
            );
        }
    }

    private void cancelClick(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);
    }

    private void onCreateClick(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        if (personalFlower.isEmpty()) {
            player.getInventory().close();
            return;
        }

        final var currentMil = String.valueOf(System.currentTimeMillis());
        final var description = new ArrayList<>(List.copyOf(languageManager.getComponents("gui.flower.item.lore.description")));

        for (var singleFlower : personalFlower) {
            final var lore = languageManager.getComponent("gui.flower.item.lore.placeholder", "%flower%", singleFlower.getName());
            description.add(lore);
        }

        //just takes the current system-time as a display name
        final var placer = new ItemBuilder(Material.BLAZE_POWDER)
                .setName(languageManager.getComponent("gui.flower.item.display.name", "%id%", currentMil))
                .setLore(description).build();

        player.getInventory().addItem(placer);
        languageManager.sendPlayerMessage(player, "gui.flower.message.create");

        final var flowerGroupData = new FlowerGroupData(List.copyOf(personalFlower));
        betterFlowers.getFlowerManager().getFlowers().put(placer, flowerGroupData);
        betterFlowers.getFlowerManager().getFlowerRandomizer().put(flowerGroupData, List.copyOf(randomizer));

        player.getInventory().close();
    }

    private void onBackClick(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);
        generateCategories();
    }

    private void onRemoveClick(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        if (!personalFlower.isEmpty() && !randomizer.isEmpty() && !isGroup.isEmpty()) {
            personalFlower.remove(personalFlower.size() - 1);
            randomizer.remove(randomizer.size() - 1);
            isGroup.remove(isGroup.size() - 1);
        }

        player.playSound(player.getLocation(), Sound.BLOCK_COMPOSTER_EMPTY, 1, 0);
        generateCategories();
    }

    private void onDeleteClick(InventoryClickEvent inventoryClickEvent) {
        inventoryClickEvent.setCancelled(true);

        personalFlower.clear();
        randomizer.clear();
        generateCategories();
    }

    private void onCategoryClick(InventoryClickEvent inventoryClickEvent, FlowerGroup flowerGroup) {
        final var currentData = new FlowerData(flowerGroup.getFlowers(), flowerGroup.getDisplayName(), flowerGroup.getDisplay());

        inventoryClickEvent.setCancelled(true);

        if (!inventoryClickEvent.isShiftClick()) {
            generateSubCategories(flowerGroup);
            return;
        }

        if (personalFlower.size() > 8) {
            languageManager.sendPlayerMessage(player, "gui.flower.message.limit");
            return;
        }

        if (inventoryClickEvent.isRightClick()) randomizer.add(true);
        else randomizer.add(false);
        isGroup.add(true);

        personalFlower.add(currentData);
        player.playSound(player.getLocation(), Sound.BLOCK_CAVE_VINES_PLACE, 1, 0);
        generateCategories();
    }

    private void onSubCategoryClick(InventoryClickEvent inventoryClickEvent, SingleFlower singleFlower) {
        inventoryClickEvent.setCancelled(true);

        if (personalFlower.size() > 8) {
            languageManager.sendPlayerMessage(player, "gui.flower.message.limit");
            return;
        }

        if (inventoryClickEvent.isRightClick()) randomizer.add(true);
        else randomizer.add(false);
        isGroup.add(false);

        personalFlower.add(new FlowerData(singleFlower));
        player.playSound(player.getLocation(), Sound.BLOCK_CAVE_VINES_PLACE, 1, 0);
        generateCategories();
    }
}